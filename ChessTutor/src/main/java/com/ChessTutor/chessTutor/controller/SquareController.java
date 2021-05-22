package com.ChessTutor.chessTutor.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import com.ChessTutor.chessTutor.dto.FenDTO;
import com.ChessTutor.chessTutor.dto.SquareDTO;
import com.ChessTutor.chessTutor.dto.SquareNameDTO;
import com.ChessTutor.chessTutor.model.Piece;
import com.ChessTutor.chessTutor.model.PieceModel;
import com.ChessTutor.chessTutor.model.Square;
import com.ChessTutor.chessTutor.repository.PieceRepository;
import com.ChessTutor.chessTutor.repository.SquareRepository;
import com.ChessTutor.chessTutor.service.PieceService;
import com.ChessTutor.chessTutor.util.FenParser;
import com.ChessTutor.chessTutor.util.PieceFactory;

@RestController
@RequestMapping(value="api/square")
public class SquareController {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	SquareRepository squareRepo;
	
	@Autowired
	PieceRepository pieceRepo;
	
	@Autowired
	PieceService pieceService;
	
	PieceFactory pieceFactory = new PieceFactory();
	
	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Square>> getAllSquares() {
        //System.out.println("USAO");
        //QueryGenerator.createAllSquares(squareRepo);
		//FenParser.parse("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq c6 0 2", squareRepo);
		
		List<Square> squares = squareRepo.findAll();
		//System.out.println(squares);
        return new ResponseEntity<>(squares, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/flask", method = RequestMethod.GET)
    public ResponseEntity<String> getFlaskResponse() {
   
		String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq c6 0 2";
		//String response = restTemplate.getForObject("http://localhost:5000/", String.class);
		
		String response = webClientBuilder.build()
		.get()
		.uri("http://localhost:5000/")
		.retrieve()
		.bodyToMono(String.class)
		.block();
		
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@PostMapping(value = "/reset-board")
    public ResponseEntity<List<Square>> resetBoard(@RequestBody FenDTO fen) {
		pieceRepo.deleteAll();
		FenParser.parse(fen.getFenString(), squareRepo);
		pieceService.createVisionSquaresForAll();
		List<Square> squares = squareRepo.findAll();
		
		List<PieceModel> pieces = new ArrayList<PieceModel>();
		
		List<Piece> allPieces = pieceRepo.findAll();
		
		for(Piece p : allPieces) {
			
			PieceModel pieceModel = pieceFactory.create(p, squareRepo, pieceRepo);
			pieces.add(pieceModel);
			
			
		}
		//System.out.println(squares);
        return new ResponseEntity<>(squares, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<List<PieceModel>> testMethod() {
		
		List<PieceModel> pieces = new ArrayList<PieceModel>();
		
		List<Piece> allPieces = pieceRepo.findAll();
		
		for(Piece p : allPieces) {
			
			PieceModel pieceModel = pieceFactory.create(p, squareRepo, pieceRepo);
			pieces.add(pieceModel);
			
			
		}
		
		//Piece piece = pieceRepo.findById(112L).orElse(null);
		
        return new ResponseEntity<>(pieces, HttpStatus.OK);
    }
	
	@RequestMapping(value="/piece/{id}",method = RequestMethod.GET)
    public ResponseEntity<Piece> findPieceById(@PathVariable Long id) {
		Piece piece = pieceRepo.findById(id).orElse(null);
        return new ResponseEntity<>(piece, HttpStatus.OK);
    }
	
	@PostMapping("/by-square-name")
    public ResponseEntity<SquareDTO> getSquareByName(@RequestBody SquareNameDTO squareName) throws Exception {
		//Square square = squareRepo.findSquareByGraphName(squareName.getGraphName());
		Square square = squareRepo.findByNameForGraph(squareName.getGraphName());
		SquareDTO squareDTO = new SquareDTO(square.getId(),square.getNameForGraph(),square.getPiece().getId());
        return new ResponseEntity<>(squareDTO, HttpStatus.CREATED);
    }
	
	@RequestMapping(value="/vision-square/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<SquareDTO>> findVisionSquaresForPiece(@PathVariable Long id) {
        List<SquareDTO> visionSquares = new ArrayList<SquareDTO>();
		List<Square> squares = squareRepo.findVisionSquaresForPiece(id);
		
		for(Square square : squares) {
			System.out.println(square.getNameForGraph());
			if(square.getPiece() != null) {
				visionSquares.add(new SquareDTO(square.getId(), square.getNameForGraph(), square.getPiece().getId()));
			}
			else {
				visionSquares.add(new SquareDTO(square.getId(), square.getNameForGraph(), -1L));
			}
			
		}
		for(SquareDTO s : visionSquares) {
			System.out.println(s.getName());
		}
        return new ResponseEntity<>(visionSquares, HttpStatus.OK);
    }
}

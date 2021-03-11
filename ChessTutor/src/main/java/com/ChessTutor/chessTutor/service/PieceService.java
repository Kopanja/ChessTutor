package com.ChessTutor.chessTutor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ChessTutor.chessTutor.repository.PieceRepository;

@Service
public class PieceService {

	@Autowired
	PieceRepository pieceRepo;
	
	public void createVisionSquaresForAll() {
		this.createVisionSquaresBishop();
		this.createVisionSquaresRook();
		this.createVisionSquaresQueen();
		this.createVisionSquaresKing();

	}
	
	public void resetBoard() {
		
	}
	
	
	public void createVisionSquaresRook() {
		pieceRepo.createVisionUP("Rook");
		pieceRepo.createVisionDOWN("Rook");
		pieceRepo.createVisionLEFT("Rook");
		pieceRepo.createVisionRIGHT("Rook");
	}
	
	public void createVisionSquaresBishop() {
		pieceRepo.createVisionDiagRightUP("Bishop");
		pieceRepo.createVisionDiagRightDOWN("Bishop");
		pieceRepo.createVisionDiagLeftDown("Bishop");
		pieceRepo.createVisionDiagLeftUP("Bishop");
	}
	
	public void createVisionSquaresQueen() {
		pieceRepo.createVisionUP("Queen");
		pieceRepo.createVisionDOWN("Queen");
		pieceRepo.createVisionLEFT("Queen");
		pieceRepo.createVisionRIGHT("Queen");
		
		pieceRepo.createVisionDiagRightUP("Queen");
		pieceRepo.createVisionDiagRightDOWN("Queen");
		pieceRepo.createVisionDiagLeftDown("Queen");
		pieceRepo.createVisionDiagLeftUP("Queen");
	}
	
	public void createVisionSquaresKing() {
		pieceRepo.createVisionSquareForKing("UP");
		pieceRepo.createVisionSquareForKing("DOWN");
		pieceRepo.createVisionSquareForKing("LEFT");
		pieceRepo.createVisionSquareForKing("RIGHT");
		pieceRepo.createVisionSquareForKing("DIAGONAL_RIGHT_UP");
		pieceRepo.createVisionSquareForKing("DIAGONAL_RIGHT_DOWN");
		pieceRepo.createVisionSquareForKing("DIAGONAL_LEFT_UP");
		pieceRepo.createVisionSquareForKing("DIAGONAL_LEFT_DOWN");
	}
	
	
	
}

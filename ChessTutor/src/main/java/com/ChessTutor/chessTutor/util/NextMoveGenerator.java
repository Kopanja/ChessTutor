package com.ChessTutor.chessTutor.util;

import java.util.List;

import com.ChessTutor.chessTutor.model.Piece;
import com.ChessTutor.chessTutor.model.Square;
import com.ChessTutor.chessTutor.repository.SquareRepository;

public class NextMoveGenerator {
	
	
	
	public static List<Square> bishopVision(Piece piece, SquareRepository squareRepo){
		Square[][] board;
		List<Square> visionSquares = null;
		
		board = FenParser.buildBoard(squareRepo.findAll());
		Square currentSquare = squareRepo.findSquareByPiece(piece.getId());
		//int[] rankAndFileInt = FenParser.getRankAndFileInt(currentSquare);
		//int currentSquareRank = rankAndFileInt[0];
		//int currentSquareFile = rankAndFileInt[1];
		
		
		return visionSquares;
	}

}

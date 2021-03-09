package com.ChessTutor.chessTutor.util;



import com.ChessTutor.chessTutor.model.Square;
import com.ChessTutor.chessTutor.repository.SquareRepository;

public class QueryGenerator {


	public static void createAllSquares(SquareRepository squareRepo) {
		String[] files = {"A", "B", "C", "D", "E", "F", "G", "H"};
		for(int rank = 0; rank < 8; rank++) {
			for(int file = 0; file < 8; file++) {
				Square square = new Square();
				square.setFile(file);
				square.setRank(rank);
				square.setNameForGraph(FenParser.getFileString(square) + rank);
				squareRepo.save(square);
			}
		}
		
	}

}

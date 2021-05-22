package com.ChessTutor.chessTutor.util;

import java.util.List;

import com.ChessTutor.chessTutor.model.Piece;
import com.ChessTutor.chessTutor.model.Square;
import com.ChessTutor.chessTutor.repository.SquareRepository;




public class FenParser {
	
	public static void parse(String fen, SquareRepository squareRepo) {
		String filledFen = fillEmptySquaresInFen(fen);
		String[] ranks = filledFen.split("/");
		List<Square> allSquares = squareRepo.findAll();
		Square[][] board = buildBoard(allSquares);
		for(int rank = 0; rank < ranks.length; rank++) {
			String[] files = ranks[rank].split("");
			for(int file = 0; file<files.length; file++) {
				//System.out.println(this.board.getSquares()[rank][file]);
				board[7-rank][file].setPiece(createPieceFromFen(files[file]));
				//update
				squareRepo.save(board[7-rank][file]);
			}
			
		}
	}
	
	public static void fixSquareName(SquareRepository squareRepo) {
		List<Square> allSquares = squareRepo.findAll();
		for(Square square : allSquares) {
			String file = getFileString(square);
			square.setNameForGraph(file + (square.getRank()+1));
			squareRepo.save(square);
		}
	}
	
	public static Square[][] buildBoard(List<Square> allSquares){
		Square[][] board = new Square[8][8];
		for(Square square : allSquares) {
			board[square.getRank()][square.getFile()] = square;
		}
		return board;
	}
	
	private static String fillEmptySquaresInFen(String fen) {
		String retString = "";
		String[] list1 = fen.split(" ");
		String[] ranks = list1[0].split("/");
		
		for(int rank = 0; rank < ranks.length; rank++) {
					
					String[] files = ranks[rank].split("");
					
					for(int file = 0; file < files.length; file++) {
						if(isNumeric(files[file])) {
							int num = Integer.parseInt(files[file]);
							for(int i = 0; i < num; i++) {
								retString += "#";
							}
						}else {
							retString += files[file];
						}
					}
					retString += "/";
				}
		retString = retString.substring(0,retString.length() - 1);
		return retString;
	}
	
	private static boolean isNumeric(String strNum) {
		if (strNum == null) {
	        return false;
	    }
	    try {
	        int i = Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}

	public static String getFileString(Square square) {
		String file = "";
		switch(square.getFile()) {
		  case 0:
		    file = "A";
		    break;
		  case 1:
			  file = "B";
		    break;
		  case 2:
			  file = "C";
			break;
		  case 3:
			  file = "D";
			break;
		  case 4:
			  file = "E";
			break;
		  case 5:
			  file = "F";
			break;
		  case 6:
			  file = "G";
			break;
		  case 7:
			  file = "H";
			break;
		
		}
		return file;
	}
	
	private static Piece createPieceFromFen(String fen) {
		
		String type = "";
		String color = "";
		int value = 0;
		switch(fen) {
		  case "P":
		    type = "Pawn";
		    color = "white";
		    value = 1;
		    break;
		  case "p":
			  type = "Pawn";
			  color = "black";
			  value = 1;
		    break;
		  case "N":
			  type = "Knight";
			  color = "white";
			  value = 3;
			break;
		  case "n":
			  type = "Knight";
			  color = "black";
			  value = 3;
			break;
		  case "B":
			  type = "Bishop";
			  color = "white";
			  value = 3;
			break;
		  case "b":
			  type = "Bishop";
			  color = "black";
			  value = 3;
			break;
		  case "R":
			  type = "Rook";
			  color = "white";
			  value = 5;
			break;
		  case "r":
			  type = "Rook";
			  color = "black";
			  value = 5;
			break;
		  case "Q":
			  type = "Queen";
			  color = "white";
			  value = 9;
			break;
		  case "q":
			  type = "Queen";
			  color = "black";
			  value = 9;
			break;
		  case "K":
			  type = "King";
			  color = "white";
			  value = 100;
		    break;
		  case "k":
			  type = "King";
			  color = "black";
			  value = 100;
		    break;
		}
		if(type.equals("") || color.equals("")) {
			return null;
		}else {
			return new Piece(color,type, value);
			//return new Piece(color,type);
		}
	}

}

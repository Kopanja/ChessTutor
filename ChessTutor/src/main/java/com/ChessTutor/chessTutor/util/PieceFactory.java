package com.ChessTutor.chessTutor.util;

import java.util.ArrayList;
import java.util.List;

import com.ChessTutor.chessTutor.model.Path;
import com.ChessTutor.chessTutor.model.Piece;
import com.ChessTutor.chessTutor.model.PieceModel;
import com.ChessTutor.chessTutor.model.Square;
import com.ChessTutor.chessTutor.repository.PieceRepository;
import com.ChessTutor.chessTutor.repository.SquareRepository;

public class PieceFactory {

	
	
	public PieceModel create(Piece p, SquareRepository squareRepo, PieceRepository pieceRepo) {
		PieceModel piece = null;
		Square isOnSquare = squareRepo.findSquareByPiece(p.getId());
		List<Path> paths = new ArrayList<Path>();
		switch(p.getType()) {
		
		case "Bishop":
			
			Path diag1 = new Path(isOnSquare,squareRepo.findDiagLeftDownPath(isOnSquare.getId()));
			Path diag2 = new Path(isOnSquare,squareRepo.findDiagLeftUpPath(isOnSquare.getId()));
			Path diag3 = new Path(isOnSquare,squareRepo.findDiagRightDownPath(isOnSquare.getId()));
			Path diag4 = new Path(isOnSquare,squareRepo.findDiagRightUpPath(isOnSquare.getId()));
			
			paths.add(diag1);
			paths.add(diag2);
			paths.add(diag3);
			paths.add(diag4);
			
			piece = new PieceModel(p,isOnSquare,paths);
			piece.setNextMoves(this.createNextMovesPaths(piece, pieceRepo));
			//piece.setIsAttacking(this.createIsAttackingList(piece, pieceRepo));
			//piece.setIsDefending(this.createIsDefendingList(piece, pieceRepo));
			break;
		case "Rook":
			Path upPath = new Path(isOnSquare, squareRepo.findUpPath(isOnSquare.getId()));
			Path downPath = new Path(isOnSquare, squareRepo.findDownPath(isOnSquare.getId()));
			Path leftPath = new Path(isOnSquare, squareRepo.findLeftPath(isOnSquare.getId()));
			Path rightPath = new Path(isOnSquare, squareRepo.findRightPath(isOnSquare.getId()));
			
			paths.add(upPath);
			paths.add(downPath);
			paths.add(leftPath);
			paths.add(rightPath);
			
			piece = new PieceModel(p, isOnSquare, paths);
			piece.setNextMoves(this.createNextMovesPaths(piece, pieceRepo));
			//piece.setIsAttacking(this.createIsAttackingList(piece, pieceRepo));
			//piece.setIsDefending(this.createIsDefendingList(piece, pieceRepo));
			break;
		case"Queen":
			Path diagonal1 = new Path(isOnSquare,squareRepo.findDiagLeftDownPath(isOnSquare.getId()));
			Path diagonal2 = new Path(isOnSquare,squareRepo.findDiagLeftUpPath(isOnSquare.getId()));
			Path diagonal3 = new Path(isOnSquare,squareRepo.findDiagRightDownPath(isOnSquare.getId()));
			Path diagonal4 = new Path(isOnSquare,squareRepo.findDiagRightUpPath(isOnSquare.getId()));
			
			Path pathUp = new Path(isOnSquare, squareRepo.findUpPath(isOnSquare.getId()));
			Path pathDown = new Path(isOnSquare, squareRepo.findDownPath(isOnSquare.getId()));
			Path pathLeft = new Path(isOnSquare, squareRepo.findLeftPath(isOnSquare.getId()));
			Path pathRight = new Path(isOnSquare, squareRepo.findRightPath(isOnSquare.getId()));
			
			paths.add(diagonal1);
			paths.add(diagonal2);
			paths.add(diagonal3);
			paths.add(diagonal4);
			
			paths.add(pathUp);
			paths.add(pathDown);
			paths.add(pathLeft);
			paths.add(pathRight);
			
			piece = new PieceModel(p, isOnSquare, paths);
			piece.setNextMoves(this.createNextMovesPaths(piece, pieceRepo));
			//piece.setIsAttacking(this.createIsAttackingList(piece, pieceRepo));
			//piece.setIsDefending(this.createIsDefendingList(piece, pieceRepo));
			break;
		case "King":
			piece = this.createKing(p, isOnSquare, squareRepo, pieceRepo);
			break;
		case "Knight":
			piece = this.createKnight(p, isOnSquare, squareRepo, pieceRepo);
			break;
		}
		
		return piece;
	}
	
	private List<Path> createNextMovesPaths(PieceModel pieceModel, PieceRepository pieceRepo) {
		
		List<Path> nextMoves = new ArrayList<Path>();
		int count;
		for(Path p : pieceModel.getPaths()) {
			Path nextMovesPath = new Path();
			count = 0;
			for(Square square : p.getPath()) {
				Piece piece = pieceRepo.findPieceBySquare(square.getId());
				if(count == 0) {
					if(piece == null) {
						nextMovesPath.getPath().add(square);
						pieceRepo.createCanMoveRelationship(pieceModel.getPiece().getId(), square.getId());
					}
					else {
						if(pieceModel.getPiece().getColor().equals(piece.getColor())) {
							pieceRepo.createIsDefendingRelationship(pieceModel.getPiece().getId(), piece.getId());
						}
						else {
							pieceRepo.createIsAttackingRelationship(pieceModel.getPiece().getId(), piece.getId());
						}
						count++;
					}
					
				}else {
					if(piece != null) {
						pieceRepo.createIsXRayingRelationship(pieceModel.getPiece().getId(), piece.getId());
					}
				}
				
			}
			nextMoves.add(nextMovesPath);
		}
		return nextMoves;
	}
	
	private List<Piece> createIsAttackingList(PieceModel pieceModel,  PieceRepository pieceRepo){
		List<Piece> isAttacking = new ArrayList<Piece>();
		for(Path p : pieceModel.getPaths()) {
			for(Square square : p.getPath()) {
				Piece piece = pieceRepo.findPieceBySquare(square.getId());
				if(piece != null && (!piece.getColor().equals(pieceModel.getPiece().getColor()))) {
					isAttacking.add(piece);
					pieceRepo.createIsAttackingRelationship(pieceModel.getPiece().getId(), piece.getId());
					break;
				}
			}
		}
		
		
		return isAttacking;
	}
	
	private List<Piece> createIsDefendingList(PieceModel pieceModel,  PieceRepository pieceRepo){
		List<Piece> isDefending = new ArrayList<Piece>();
		for(Path p : pieceModel.getPaths()) {
			for(Square square : p.getPath()) {
				Piece piece = pieceRepo.findPieceBySquare(square.getId());
				if(piece != null && (piece.getColor().equals(pieceModel.getPiece().getColor()))) {
					isDefending.add(piece);
					pieceRepo.createIsDefendingRelationship(pieceModel.getPiece().getId(), piece.getId());
					break;
				}
			}
		}
		
		
		return isDefending;
	}
	
	private PieceModel createKing(Piece p, Square isOnSquare, SquareRepository squareRepo,  PieceRepository pieceRepo) {
		
		
		List<Path> nextMoves = new ArrayList<Path>();
		List<Piece> isAttacking = new ArrayList<Piece>();
		List<Piece> isDefending = new ArrayList<Piece>();
		
		List<Square> squares = squareRepo.findKingSquares(isOnSquare.getId());
		
		for(Square s : squares) {
			Piece piece = pieceRepo.findPieceBySquare(s.getId());
			if(piece == null) {
				List<Square> list = new ArrayList<Square>();
				list.add(s);
				nextMoves.add(new Path(null, list));
				pieceRepo.createCanMoveRelationship(p.getId(), s.getId());
			}else {
				if(piece.getColor().equals(p.getColor())) {
					isDefending.add(piece);
					pieceRepo.createIsDefendingRelationship(p.getId(), piece.getId());
				}else {
					isAttacking.add(piece);
					pieceRepo.createIsAttackingRelationship(p.getId(), piece.getId());
				}
			}
		}
		PieceModel king = new PieceModel(p,isOnSquare,null);
		king.setNextMoves(nextMoves);
		king.setIsAttacking(isAttacking);
		king.setIsDefending(isDefending);
		return king;
	}
	
	private PieceModel createKnight(Piece p, Square isOnSquare, SquareRepository squareRepo, PieceRepository pieceRepo) {
		List<Path> nextMoves = new ArrayList<Path>();
		List<Piece> isAttacking = new ArrayList<Piece>();
		List<Piece> isDefending = new ArrayList<Piece>();
		
		List<Square> upSquares = squareRepo.findKnightSquaresUP(isOnSquare.getId());
		List<Square> downSquares = squareRepo.findKnightSquaresDOWN(isOnSquare.getId());
		List<Square> leftSquares = squareRepo.findKnightSquaresLEFT(isOnSquare.getId());
		List<Square> rightSquares = squareRepo.findKnightSquaresRIGHT(isOnSquare.getId());

		for(Square s : upSquares) {
			Piece piece = pieceRepo.findPieceBySquare(s.getId());
			if(piece == null) {
				List<Square> list = new ArrayList<Square>();
				list.add(s);
				nextMoves.add(new Path(null, list));
				pieceRepo.createCanMoveRelationship(p.getId(), s.getId());
			}else {
				if(piece.getColor().equals(p.getColor())) {
					isDefending.add(piece);
					pieceRepo.createIsDefendingRelationship(p.getId(), piece.getId());
				}else {
					isAttacking.add(piece);
					pieceRepo.createIsAttackingRelationship(p.getId(), piece.getId());
				}
			}
		}
		
		for(Square s : downSquares) {
			Piece piece = pieceRepo.findPieceBySquare(s.getId());
			if(piece == null) {
				List<Square> list = new ArrayList<Square>();
				list.add(s);
				nextMoves.add(new Path(null, list));
				pieceRepo.createCanMoveRelationship(p.getId(), s.getId());
			}else {
				if(piece.getColor().equals(p.getColor())) {
					isDefending.add(piece);
					pieceRepo.createIsDefendingRelationship(p.getId(), piece.getId());
				}else {
					isAttacking.add(piece);
					pieceRepo.createIsAttackingRelationship(p.getId(), piece.getId());
				}
			}
		}
		
		for(Square s : leftSquares) {
			Piece piece = pieceRepo.findPieceBySquare(s.getId());
			if(piece == null) {
				List<Square> list = new ArrayList<Square>();
				list.add(s);
				nextMoves.add(new Path(null, list));
				pieceRepo.createCanMoveRelationship(p.getId(), s.getId());
			}else {
				if(piece.getColor().equals(p.getColor())) {
					isDefending.add(piece);
					pieceRepo.createIsDefendingRelationship(p.getId(), piece.getId());
				}else {
					isAttacking.add(piece);
					pieceRepo.createIsAttackingRelationship(p.getId(), piece.getId());
				}
			}
		}
		
		for(Square s : rightSquares) {
			Piece piece = pieceRepo.findPieceBySquare(s.getId());
			if(piece == null) {
				List<Square> list = new ArrayList<Square>();
				list.add(s);
				nextMoves.add(new Path(null, list));
				pieceRepo.createCanMoveRelationship(p.getId(), s.getId());
			}else {
				if(piece.getColor().equals(p.getColor())) {
					isDefending.add(piece);
					pieceRepo.createIsDefendingRelationship(p.getId(), piece.getId());
				}else {
					isAttacking.add(piece);
					pieceRepo.createIsAttackingRelationship(p.getId(), piece.getId());
				}
			}
		}
		
		PieceModel knight = new PieceModel(p,isOnSquare,null);
		knight.setNextMoves(nextMoves);
		knight.setIsAttacking(isAttacking);
		knight.setIsDefending(isDefending);
		return knight;
	}
	
	private PieceModel createPawn(Piece p, Square isOnSquare, SquareRepository squareRepo, PieceRepository pieceRepo) {
		PieceModel pawn = null;
		
		return pawn;
	}
}

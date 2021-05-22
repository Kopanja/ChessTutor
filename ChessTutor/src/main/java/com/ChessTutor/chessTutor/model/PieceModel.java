package com.ChessTutor.chessTutor.model;

import java.util.ArrayList;
import java.util.List;

public class PieceModel {

	Piece piece;
	
	Square isOnSquare;
	
	List<Path> paths;
	
	List<Path> nextMoves;
	
	List<Piece> isAttacking;
	
	List<Piece> isDefending;

	
	public PieceModel() {
		super();
	}


	public PieceModel(Piece piece, Square isOnSquare, List<Path> paths) {
		super();
		this.piece = piece;
		this.isOnSquare = isOnSquare;
		this.paths = paths;
		this.nextMoves = new ArrayList<Path>();
		this.isAttacking = new ArrayList<Piece>();
		this.isDefending = new ArrayList<Piece>();
	}


	public Piece getPiece() {
		return piece;
	}


	public void setPiece(Piece piece) {
		this.piece = piece;
	}


	public Square getIsOnSquare() {
		return isOnSquare;
	}


	public void setIsOnSquare(Square isOnSquare) {
		this.isOnSquare = isOnSquare;
	}


	public List<Path> getPaths() {
		return paths;
	}


	public void setPaths(List<Path> paths) {
		this.paths = paths;
	}


	public List<Piece> getIsAttacking() {
		return isAttacking;
	}


	public void setIsAttacking(List<Piece> isAttacking) {
		this.isAttacking = isAttacking;
	}


	public List<Piece> getIsDefending() {
		return isDefending;
	}


	public void setIsDefending(List<Piece> isDefending) {
		this.isDefending = isDefending;
	}


	public List<Path> getNextMoves() {
		return nextMoves;
	}


	public void setNextMoves(List<Path> nextMoves) {
		this.nextMoves = nextMoves;
	}
	
	
	
}

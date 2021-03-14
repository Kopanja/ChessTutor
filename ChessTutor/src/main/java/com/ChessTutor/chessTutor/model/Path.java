package com.ChessTutor.chessTutor.model;

import java.util.List;

public class Path {

	Square startSquare;
	
	List<Square> path;

	public Path() {
		super();
	}

	public Path(Square startSquare, List<Square> path) {
		super();
		this.startSquare = startSquare;
		this.path = path;
	}

	public Square getStartSquare() {
		return startSquare;
	}

	public void setStartSquare(Square startSquare) {
		this.startSquare = startSquare;
	}

	public List<Square> getPath() {
		return path;
	}

	public void setPath(List<Square> path) {
		this.path = path;
	}
	
	
}

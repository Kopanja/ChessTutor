package com.ChessTutor.chessTutor.model;

import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

@Node
public class Square {

	@Id @GeneratedValue
	private Long id;

	private int rank;

	private int file;
	
	private String nameForGraph;
	
	@Relationship(type = "IsOn", direction = Direction.INCOMING)
	public Piece piece;
	
	public Square() {
		
	}
	public Square(int rank, int file, Piece piece) {
		this.id = null;
		this.rank = rank;
		this.file = file;
		//this.nameForGraph = file + rank;
		this.piece = piece;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getFile() {
		return file;
	}
	public void setFile(int file) {
		this.file = file;
	}
	public Piece getPiece() {
		return piece;
	}
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	public String getNameForGraph() {
		return nameForGraph;
	}
	public void setNameForGraph(String nameForGraph) {
		this.nameForGraph = nameForGraph;
	}
	
	
	
}

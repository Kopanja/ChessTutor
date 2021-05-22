package com.ChessTutor.chessTutor.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node
public class Piece {
	@Id @GeneratedValue
	private Long id;
	
	private String color;
	
	private String type;
	
	private int value;
/*
	public Piece(String color, String type) {
		super();
		this.id = null;
		this.color = color;
		this.type = type;
	}
	*/
	public Piece(String color, String type, int value) {
		super();
		this.id = null;
		this.color = color;
		this.type = type;
		this.value = value;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	
}

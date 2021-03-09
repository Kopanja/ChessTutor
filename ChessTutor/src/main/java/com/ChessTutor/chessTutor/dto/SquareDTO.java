package com.ChessTutor.chessTutor.dto;

public class SquareDTO {
	
	private Long id;
	private String name;
	private Long pieceId;
	
	
	
	public SquareDTO() {
		super();
	}



	public SquareDTO(Long id, String name, Long pieceId) {
		super();
		this.id = id;
		this.name = name;
		this.pieceId = pieceId;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Long getPieceId() {
		return pieceId;
	}



	public void setPieceId(Long pieceId) {
		this.pieceId = pieceId;
	}
	
	
	

}

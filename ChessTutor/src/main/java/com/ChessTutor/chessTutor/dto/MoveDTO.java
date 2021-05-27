package com.ChessTutor.chessTutor.dto;

public class MoveDTO {

	String color;
	String flags;
	String from;
	String to;
	String piece;
	String san;
	
	
	
	
	public MoveDTO() {
		super();
	}



	public MoveDTO(String color, String flags, String from, String to, String piece, String san) {
		super();
		this.color = color;
		this.flags = flags;
		this.from = from;
		this.to = to;
		this.piece = piece;
		this.san = san;
	}



	public String getColor() {
		return color;
	}



	public void setColor(String color) {
		this.color = color;
	}



	public String getFlags() {
		return flags;
	}



	public void setFlags(String flags) {
		this.flags = flags;
	}



	public String getFrom() {
		return from;
	}



	public void setFrom(String from) {
		this.from = from;
	}



	public String getTo() {
		return to;
	}



	public void setTo(String to) {
		this.to = to;
	}



	public String getPiece() {
		return piece;
	}



	public void setPiece(String piece) {
		this.piece = piece;
	}



	public String getSan() {
		return san;
	}



	public void setSan(String san) {
		this.san = san;
	}



	@Override
	public String toString() {
		return "MoveDTO [color=" + color + ", flags=" + flags + ", from=" + from + ", to=" + to + ", piece=" + piece
				+ ", san=" + san + "]";
	}
	
	
	
}

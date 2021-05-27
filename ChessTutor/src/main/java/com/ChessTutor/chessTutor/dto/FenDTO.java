package com.ChessTutor.chessTutor.dto;

public class FenDTO {

	String fenString;
	
	
	FenDTO(){
		
	}


	public FenDTO(String fenString) {
		super();
		this.fenString = fenString;
	}


	public String getFenString() {
		return fenString;
	}


	public void setFenString(String fenString) {
		this.fenString = fenString;
	}


	@Override
	public String toString() {
		return "FenDTO [fenString=" + fenString + "]";
	}
	
	
}

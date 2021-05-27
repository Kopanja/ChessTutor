package com.ChessTutor.chessTutor.dto;

public class NewPositionDTO {
	
	MoveDTO move;
	FenDTO newFen;
	FenDTO oldFen;
	
	
	public NewPositionDTO() {
	}


	public NewPositionDTO(MoveDTO move, FenDTO newFen, FenDTO oldFen) {
		super();
		this.move = move;
		this.newFen = newFen;
		this.oldFen = oldFen;
	}


	public MoveDTO getMove() {
		return move;
	}


	public void setMove(MoveDTO move) {
		this.move = move;
	}


	public FenDTO getNewFen() {
		return newFen;
	}


	public void setNewFen(FenDTO newFen) {
		this.newFen = newFen;
	}


	public FenDTO getOldFen() {
		return oldFen;
	}


	public void setOldFen(FenDTO oldFen) {
		this.oldFen = oldFen;
	}


	@Override
	public String toString() {
		return "NewPositionDTO [move=" + move + ", newFen=" + newFen + ", oldFen=" + oldFen + "]";
	}


	

	

}

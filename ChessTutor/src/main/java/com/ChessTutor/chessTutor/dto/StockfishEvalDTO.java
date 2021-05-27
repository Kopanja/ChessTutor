package com.ChessTutor.chessTutor.dto;

public class StockfishEvalDTO {

	private double oldFenEval;
	private double newFenEval;
	private String bestMove;
	private double bestMoveFenEval;
	
	
	
	public StockfishEvalDTO() {
		super();
	}



	public StockfishEvalDTO(double oldFenEval, double newFenEval, String bestMove, double bestMoveFenEval) {
		super();
		this.oldFenEval = oldFenEval;
		this.newFenEval = newFenEval;
		this.bestMove = bestMove;
		this.bestMoveFenEval = bestMoveFenEval;
	}



	public double getOldFenEval() {
		return oldFenEval;
	}



	public void setOldFenEval(double oldFenEval) {
		this.oldFenEval = oldFenEval;
	}



	public double getNewFenEval() {
		return newFenEval;
	}



	public void setNewFenEval(double newFenEval) {
		this.newFenEval = newFenEval;
	}



	public String getBestMove() {
		return bestMove;
	}



	public void setBestMove(String bestMove) {
		this.bestMove = bestMove;
	}



	public double getBestMoveFenEval() {
		return bestMoveFenEval;
	}



	public void setBestMoveFenEval(double bestMoveFenEval) {
		this.bestMoveFenEval = bestMoveFenEval;
	}



	@Override
	public String toString() {
		return "StockfishEvalDTO [oldFenEval=" + oldFenEval + ", newFenEval=" + newFenEval + ", bestMove=" + bestMove
				+ ", bestMoveFenEval=" + bestMoveFenEval + "]";
	}
	
	
	
}

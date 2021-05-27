from stockfish import *
import chess
from flask import Flask
from flask import request
from flask import json
#print("A")
sto = Stockfish(r"C:\Users\kopan\Desktop\stockfish_13_win_x64_bmi2\stockfish_13_win_x64_bmi2.exe")

board = chess.Board()
#board.set_fen(fen)
#board.push_uci("f3f4")
#print(board.fen())

class StockfishEval:
     def __init__(self, oldFenEval, newFenEval, bestMove, bestMoveFenEval):
         self.oldFenEval = oldFenEval
         self.newFenEval = newFenEval
         self.bestMove = bestMove
         self.bestMoveFenEval = bestMoveFenEval

app = Flask(__name__)

@app.route('/position-eval', methods = ['POST'])
def position_eval():

    print(request.json)
    move_uci = request.json["move"]["from"] + request.json["move"]["to"]
    print("Move: " + str(move_uci))

    new_fen = request.json["newFen"]["fenString"]
    new_fen_eval = get_fen_eval(new_fen)
    print("New Fen Eval: " + str(new_fen_eval))

    old_fen = request.json["oldFen"]["fenString"]
    old_fen_eval = get_fen_eval(old_fen)
    print("Old Fen Eval: " + str(old_fen_eval))

    best_move = sto.get_best_move()
    print("Best move: " + str(best_move))

    best_move_eval = get_fen_eval_after_best_move(old_fen)
    print("Eval after best move: " + str(best_move_eval))

    stockfish_eval = {"oldFenEval": old_fen_eval["value"], "newFenEval": new_fen_eval["value"], "bestMove": best_move, "bestMoveFenEval": best_move_eval["value"]}
    return stockfish_eval

def get_fen_eval(fen):
    sto.set_fen_position(fen)
    return sto.get_evaluation()

def get_fen_eval_after_best_move(fen):
    sto.set_fen_position(fen)
    best_move = sto.get_best_move()
    board.set_fen(fen)
    board.push_uci(best_move)
    sto.set_fen_position(board.fen())
    return sto.get_evaluation()


@app.route('/')
def ok():
    return "oki"



if __name__ == "__main__":
    app.run(debug=True)
from stockfish import *
import chess

def fen_to_array(fen):
    board = chess.Board()
    board.set_fen(fen)
    board.push_uci("f3f4")
    #board.push(chess.Move.from_uci("f3f4"))
    print(board.fen())



sto = Stockfish(r"C:\Users\kopan\Desktop\stockfish_13_win_x64_bmi2\stockfish_13_win_x64_bmi2.exe")
fen = "r3k2r/pp1n1p1p/2p1pp2/2b1q3/4P3/2NB1PQ1/PPP4P/2KR3R w kq - 1 15"
sto.set_fen_position(fen)
print(sto.get_board_visual())

fen_to_array(fen)


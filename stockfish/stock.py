from stockfish import *
from flask import Flask
from flask import request

#print("A")
#sto = Stockfish(r"C:\Users\kopan\Desktop\stockfish_13_win_x64_bmi2\stockfish_13_win_x64_bmi2.exe")
#sto.set_fen_position("rnb1kbnr/ppp2ppp/4p3/3p2q1/3PP3/8/PPP2PPP/RNBQKBNR w KQkq - 0 1")
#print(sto.get_board_visual())
#print(sto.get_evaluation())

app = Flask(__name__)

@app.route('/test', methods = ['POST'])
def home():
    return request.json["email"]

@app.route('/')
def ok():
    return "oki"

if __name__ == "__main__":
    app.run(debug=True)
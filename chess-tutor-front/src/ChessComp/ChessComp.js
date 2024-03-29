import React, { Component } from 'react';
import './ChessComp.css';
import Chessboard from "chessboardjsx";
import * as Chess from "chess.js";
import PropTypes from "prop-types";

class ChessComp extends Component {
  static propTypes = { children: PropTypes.func };

  
  constructor(props){
    super(props);

    this.state = {
      fen: "start",
      // square styles for active drop square
      dropSquareStyle: {},
      // custom square styles
      squareStyles: {},
      // square with the currently clicked piece
      pieceSquare: "",
      // currently clicked square
      square: "",
      // array of past game moves
      history: [],
      squares : []
    };
    this.fenChanged = this.fenChanged.bind(this);
    this.sendFen = this.sendFen.bind(this);
    
  }

  // componentWillMount(){}
  componentDidMount() {
    this.game = new Chess();
    //fetch("http://localhost:8080/api/square")
    //.then(res => res.json())
    //.then((result)=>{
    //    console.log(result)
    //    this.setState({squares:result.items})
    //})
  }
  highlightSquare = (sourceSquare, squaresToHighlight) => {
    const highlightStyles = [sourceSquare, ...squaresToHighlight].reduce(
      (a, c) => {
        return {
          ...a,
          ...{
            [c]: {
              background:
                "radial-gradient(circle, #fffc00 36%, transparent 40%)",
              borderRadius: "50%"
            }
          },
          ...squareStyling({
            history: this.state.history,
            pieceSquare: this.state.pieceSquare
          })
        };
      },
      {}
    );

    //this.setState(({ squareStyles }) => ({
    //  squareStyles: { ...squareStyles, ...highlightStyles }
    //}));
  };

  sendFen(event){
    console.log(this.state.fen);
    event.preventDefault();
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ fenString: this.state.fen })
  };
    fetch('http://localhost:8080/api/square/reset-board/', requestOptions);
  };

  fenChanged(event){
    this.setState({fen : event.target.value});
    
  };

  onDrop = ({ sourceSquare, targetSquare }) => {
    // see if the move is legal
    let oldFen = this.game.fen();
    let move = this.game.move({
      from: sourceSquare,
      to: targetSquare,
      promotion: "q" // always promote to a queen for example simplicity
    });
    console.log(move)
    // illegal move
    if (move === null) return;
    this.setState(({ history, pieceSquare }) => ({
      fen: this.game.fen(),
      history: this.game.history({ verbose: true }),
      squareStyles: squareStyling({ pieceSquare, history })
    }));
    
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({move : move, newFen : this.game.fen(), oldFen : oldFen})
  };
  fetch('http://localhost:8080/api/square/post-move', requestOptions)
  .then(response => response.json())
  .then(data => console.log(data))
  
  };

  onSquareClick = square => {
    
    let squareObj = {id : 0, name : "", pieceId : 0}
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ graphName: square.toUpperCase() })
  };
  /*
  fetch('http://localhost:8080/api/square/by-square-name', requestOptions)
      .then(response => response.json())
      .then(data => {
        squareObj = {id : data.id, name : data.name, pieceId: data.pieceId}; 
        console.log(squareObj)
        
        let path = "http://localhost:8080/api/square/vision-square/" + squareObj.pieceId;
        let visionSquares = [];
        console.log(path);
        fetch(path)
        .then(res => res.json())
        .then((result)=>{
          visionSquares = result;
          let s = {};
          let  name = "";
          console.log(squareObj.name.toLowerCase());
          for (var i = 0; i < visionSquares.length; i++) {
            name = visionSquares[i].name.toLowerCase();
            console.log(name);
            s[name] = { backgroundColor: "orange" };
            
          }
          this.setState({
            squareStyles: s
          });
          console.log(s);
          //this.highlightSquare(squareObj.name.toLowerCase(), s);
          
        

    })
      
      });
        
        */
    
    
  };
  // componentWillUnmount(){}

  // componentWillReceiveProps(){}
  // shouldComponentUpdate(){}
  // componentWillUpdate(){}
  // componentDidUpdate(){}
 
  
  render() {
    const {squares} = this.state; 
    return (
      <div>
        <Chessboard position={this.state.fen} onSquareClick = {this.onSquareClick}
        squareStyles={this.state.squareStyles} onDrop = {this.onDrop}/>
        <form  onSubmit={this.sendFen}>
          <label>FEN:</label>
          <input type ="text" value={this.state.fen} onChange={this.fenChanged}></input>
          <input type="submit" value="Submit"></input>
        </form>

      </div>
    );
  }
}

export default ChessComp;

const squareStyling = ({ pieceSquare, history }) => {
  const sourceSquare = history.length && history[history.length - 1].from;
  const targetSquare = history.length && history[history.length - 1].to;

  return {
    [pieceSquare]: { backgroundColor: "rgba(255, 255, 0, 0.4)" },
    ...(history.length && {
      [sourceSquare]: {
        backgroundColor: "rgba(255, 255, 0, 0.4)"
      }
    }),
    ...(history.length && {
      [targetSquare]: {
        backgroundColor: "rgba(255, 255, 0, 0.4)"
      }
    })
  };
};
package chess;
import chess.pieces.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Class that represents the current state of the game.  Basically, what pieces are in which positions on the
 * board.
 */
public class GameState {

	/**
	 * The current player
	 */
	private Player currentPlayer = Player.White;

	/**
	 * A map of board positions to pieces at that position
	 */
	private Map<Position, Piece> positionToPieceMap;

	/**
	 * Create the game state.
	 */
	public GameState() {
		positionToPieceMap = new HashMap<Position, Piece>();
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	//@Amit - This function will change the current player 
	public void changePlayer(){
		this.currentPlayer = (this.getCurrentPlayer() == Player.White) ? Player.Black : Player.White;		
	}

	/**
	 * Call to initialize the game state into the starting positions
	 */
	public void reset() {
		// White Pieces
		placePiece(new Rook(Player.White), new Position("a1"));
		placePiece(new Knight(Player.White), new Position("b1"));
		placePiece(new Bishop(Player.White), new Position("c1"));
		placePiece(new Queen(Player.White), new Position("d1"));
		placePiece(new King(Player.White), new Position("e1"));
		placePiece(new Bishop(Player.White), new Position("f1"));
		placePiece(new Knight(Player.White), new Position("g1"));
		placePiece(new Rook(Player.White), new Position("h1"));
		placePiece(new Pawn(Player.White), new Position("a2"));
		placePiece(new Pawn(Player.White), new Position("b2"));
		placePiece(new Pawn(Player.White), new Position("c2"));
		placePiece(new Pawn(Player.White), new Position("d2"));
		placePiece(new Pawn(Player.White), new Position("e2"));
		placePiece(new Pawn(Player.White), new Position("f2"));
		placePiece(new Pawn(Player.White), new Position("g2"));
		placePiece(new Pawn(Player.White), new Position("h2"));

		// Black Pieces
		placePiece(new Rook(Player.Black), new Position("a8"));
		placePiece(new Knight(Player.Black), new Position("b8"));
		placePiece(new Bishop(Player.Black), new Position("c8"));
		placePiece(new Queen(Player.Black), new Position("d8"));
		placePiece(new King(Player.Black), new Position("e8"));
		placePiece(new Bishop(Player.Black), new Position("f8"));
		placePiece(new Knight(Player.Black), new Position("g8"));
		placePiece(new Rook(Player.Black), new Position("h8"));
		placePiece(new Pawn(Player.Black), new Position("a7"));
		placePiece(new Pawn(Player.Black), new Position("b7"));
		placePiece(new Pawn(Player.Black), new Position("c7"));
		placePiece(new Pawn(Player.Black), new Position("d7"));
		placePiece(new Pawn(Player.Black), new Position("e7"));
		placePiece(new Pawn(Player.Black), new Position("f7"));
		placePiece(new Pawn(Player.Black), new Position("g7"));
		placePiece(new Pawn(Player.Black), new Position("h7"));
	}

	/**
	 * Get the piece at the position specified by the String
	 * @param colrow The string indication of position; i.e. "d5"
	 * @return The piece at that position, or null if it does not exist.
	 */
	public Piece getPieceAt(String colrow) {
		Position position = new Position(colrow);
		return getPieceAt(position);
	}

	/**
	 * Get the piece at a given position on the board
	 * @param position The position to inquire about.
	 * @return The piece at that position, or null if it does not exist.
	 */
	public Piece getPieceAt(Position position) {
		return positionToPieceMap.get(position);
	}

	/**
	 * Get the hashMap containing all the pieces and their corresponding pieces
	 * @return The HashMap containing all position-piece pairs.
	 */
	public Map<Position, Piece> getAllPiecesOnBoard() {
		return positionToPieceMap;
	}

	/**
	 * Method to place a piece at a given position
	 * @param piece The piece to place
	 * @param position The position
	 */
	// @Amit - made the method public to use it during moving pieces 
	public void placePiece(Piece piece, Position position) {
		positionToPieceMap.put(position, piece);
	}

	/**
	 * Method to remove a piece from a given position
	 * @param piece The piece to place
	 * @param position The position
	 */
	public void removePiece(Piece piece, Position position) {
		positionToPieceMap.remove(position);
	}

	/**@Amit
	 * Method to remove a piece from a given position
	 * @param position The position
	 */
	//OVERLOADED METHOD
	public void removePiece(Position position) {
		positionToPieceMap.remove(position);
	}

	/**@Amit
	 * Method to move a piece from <fromPosition> position to <toPosition> 
	 * @param fromPosition The current position of the piece
	 * @param position The destination position for the piece
	 */
	public void movePiece(Position fromPosition, Position toPosition) {

		Piece p = positionToPieceMap.remove(fromPosition);
		positionToPieceMap.put(toPosition, p);
	}

	/**@author Amit
	 * Returns True if Current Player's King is under Threat, else returns False.
	 */
	public boolean isKingUnderThreat(){
		Position currentKingPosition = (this.getCurrentPlayer() == Player.White) ? 
				getKingPosition(Player.White) : getKingPosition(Player.Black);
				for(Entry<Position,Piece> e : this.getAllPiecesOnBoard().entrySet()){
					if(e.getValue().getOwner() != this.getCurrentPlayer()){
						Piece piece = e.getValue();    					
						if(piece.getValidMoves(this, e.getKey()).contains(currentKingPosition)){
							return true;
						}
					}
				}    	
				return false;
	}
	
	/**@author Amit
	 * Returns the position of the King for current player 
	 * @param player - current Player
	 */
	public Position getKingPosition(Player player){
		for(Entry<Position,Piece> e : this.getAllPiecesOnBoard().entrySet()){
			Piece piece =  e.getValue();
			if( (piece instanceof King) && piece.getOwner() == this.getCurrentPlayer()){
				return e.getKey();
			}
		}
		return null;
	}
}

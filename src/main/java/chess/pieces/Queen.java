package chess.pieces;


import java.util.HashSet;
import java.util.Set;

import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * The Queen
 */
public class Queen extends Piece{
	public Queen(Player owner) {
		super(owner);
	}

	@Override
	protected char getIdentifyingCharacter() {
		return 'q';
	}
	//@Amit - This function will return a set of all possible moves for a Queen at <currentPosition>
	@Override
	public Set<Position> getValidMoves(GameState currentState, Position currentPosition) {
		// TODO Auto-generated method stub
		//This set would contain a list of all valid positions
		Set<Position> allValidPositions = new HashSet<Position>();
		Bishop dummyBishop = new Bishop(currentState.getCurrentPlayer());
		Rook dummyRook = new Rook(currentState.getCurrentPlayer());
		allValidPositions.addAll(dummyBishop.getValidMoves(currentState, currentPosition));
		allValidPositions.addAll(dummyRook.getValidMoves(currentState, currentPosition));    	
		return allValidPositions;
	}
}

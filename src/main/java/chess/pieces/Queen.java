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

	/**@author Amit
	 * Return a set of all valid moves for a Queen
	 * @param currentState - The current state of the game
	 * @param currentPosition - The current position of the Queen
	 * @return Set of valid positions
	 */
	@Override
	public Set<Position> getValidMoves(GameState currentState, Position currentPosition) {
		// TODO Auto-generated method stub
		//This set would contain a list of all valid positions
		Set<Position> allValidPositions = new HashSet<Position>();
		/* A queen can move like a Bishop as well as Move like a Rook. But it cannot move like a Knight 
		 * Hence, I have created a dummy bishop and a dummy rook and get the valid positions 
		 * from the location of the Queen*/
		Bishop dummyBishop = new Bishop(currentState.getCurrentPlayer());
		Rook dummyRook = new Rook(currentState.getCurrentPlayer());
		allValidPositions.addAll(dummyBishop.getValidMoves(currentState, currentPosition));
		allValidPositions.addAll(dummyRook.getValidMoves(currentState, currentPosition));    	
		return allValidPositions;
	}
}

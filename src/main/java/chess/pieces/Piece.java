package chess.pieces;
import java.util.HashSet;
import java.util.Set;

import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * A base class for chess pieces
 */
public abstract class Piece {
	private final Player owner;

	protected Piece(Player owner) {
		this.owner = owner;
	}

	public char getIdentifier() {
		char id = getIdentifyingCharacter();
		if (owner.equals(Player.White)) {
			return Character.toLowerCase(id);
		} else {
			return Character.toUpperCase(id);
		}
	}

	public Player getOwner() {
		return owner;
	}

	protected abstract char getIdentifyingCharacter();
	
	/**@author Amit
	 * Return a set of all valid moves for a Piece
	 * @param currentState - The current state of the game
	 * @param origin - The current position of the Piece
	 * @return Set of valid positions
	 */
	public abstract Set<Position> getValidMoves(GameState currentState, Position currentPosition);

	/*@Amit - This method will take a set of logically valid moves for a piece 
	and remove the moves that would out the king at Check*/
	/**@author Amit
	 * Return a set of all valid moves after removing the moves that put a king in Check
	 * @param validPositions - set of positionally valid moves
	 * @param currentState - The current state of the game
	 * @param currentPosition - The current position of the Piece
	 * @return Set of valid positions
	 */
	public Set<Position> removeCheckPositions(Set<Position> validPositions,	GameState currentState, Position currentPosition){	
		Set<Position> positionsToRemove = new HashSet<Position>();
		for(Position targetPosition : validPositions){
			// Try a move and check if the king is under threat
			Piece backUpPiece = currentState.getPieceAt(targetPosition);
			currentState.movePiece(currentPosition, targetPosition);
			if(currentState.isKingUnderThreat()==true){
				positionsToRemove.add(targetPosition);
			}			
			//Undo the move and place the back up piece at its original position
			currentState.movePiece(targetPosition, currentPosition);
			if(backUpPiece != null){
				currentState.placePiece(backUpPiece, targetPosition);
			}				
		}
		// Remove all those positions that put the king under check
		for(Position p : positionsToRemove){
			validPositions.remove(p);
		}
		return validPositions;
	}
}

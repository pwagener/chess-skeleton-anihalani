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
  //@Amit - This function will be implemented by each piece to get their respective valid moves
    public abstract Set<Position> getValidMoves(GameState currentState, Position currentPosition);
    
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

		for(Position p : positionsToRemove){
			validPositions.remove(p);
		}
		return validPositions;
	}
}

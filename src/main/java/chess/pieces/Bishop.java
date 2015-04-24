package chess.pieces;

import java.util.HashSet;
import java.util.Set;

import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * The 'Bishop' class
 */
public class Bishop extends Piece {
	public Bishop(Player owner) {
		super(owner);
	}

	@Override
	protected char getIdentifyingCharacter() {
		return 'b';
	}

	/**@author Amit
	 * Return a set of all possible moves for a Bishop
	 * @param currentState The current state of the game
	 * @param currentPosition The current position of the Bishop
	 * @return Set of valid positions
	 */
	@Override
	public Set<Position> getValidMoves(GameState currentState, Position currentPosition) {
		//This set would contain a list of all valid positions
		Set<Position> allValidPositions = new HashSet<Position>();

		// These variables help us to identify when to stop searching in a particular direction.
		// i.e. if the position is out of board or if there is a collision with opposite color player
		boolean canMoveNorthEast = true;
		boolean canMoveNorthWest = true;
		boolean canMoveSouthEast = true;
		boolean canMoveSouthWest = true;

		for(int i=1; i<=8; i++){    		
			// Valid locations in North east
			if( Position.isOnBoard((char)((int)currentPosition.getColumn()+i), currentPosition.getRow()+i) && canMoveNorthEast){	
				if((currentState.getPieceAt(new Position((char)((int)currentPosition.getColumn()+i), currentPosition.getRow()+i)) == null )){
					Position newPosition = new Position((char)((int)currentPosition.getColumn()+i), currentPosition.getRow()+i);
					allValidPositions.add(newPosition);									
				}else if(currentState.getPieceAt(new Position((char)((int)currentPosition.getColumn()+i), currentPosition.getRow()+i)).getOwner() != currentState.getPieceAt(currentPosition).getOwner()){
					Position newPosition = new Position((char)((int)currentPosition.getColumn()+i), currentPosition.getRow()+i);
					allValidPositions.add(newPosition);
					canMoveNorthEast = false;
				}else
					canMoveNorthEast = false;			
			}else{
				canMoveNorthEast = false;
			}
			// Valid locations in North west
			if( Position.isOnBoard((char)((int)currentPosition.getColumn()-i), currentPosition.getRow()+i) && canMoveNorthWest){	
				if((currentState.getPieceAt(new Position((char)((int)currentPosition.getColumn()-i), currentPosition.getRow()+i)) == null)){
					Position newPosition = new Position((char)((int)currentPosition.getColumn()-i), currentPosition.getRow()+i);
					allValidPositions.add(newPosition);									
				}else if(currentState.getPieceAt(new Position((char)((int)currentPosition.getColumn()-i), currentPosition.getRow()+i)).getOwner() != currentState.getPieceAt(currentPosition).getOwner()){
					Position newPosition = new Position((char)((int)currentPosition.getColumn()-i), currentPosition.getRow()+i);
					allValidPositions.add(newPosition);
					canMoveNorthWest = false;
				}else
					canMoveNorthWest = false;			
			}else{
				canMoveNorthWest = false;
			}
			// Valid locations in South east
			if( Position.isOnBoard((char)((int)currentPosition.getColumn()+i), currentPosition.getRow()-i) && canMoveSouthEast){	
				if((currentState.getPieceAt(new Position((char)((int)currentPosition.getColumn()+i), currentPosition.getRow()-i)) == null)){
					Position newPosition = new Position((char)((int)currentPosition.getColumn()+i), currentPosition.getRow()-i);
					allValidPositions.add(newPosition);									
				}else if(currentState.getPieceAt(new Position((char)((int)currentPosition.getColumn()+i), currentPosition.getRow()-i)).getOwner() != currentState.getPieceAt(currentPosition).getOwner()){
					Position newPosition = new Position((char)((int)currentPosition.getColumn()+i), currentPosition.getRow()-i);
					allValidPositions.add(newPosition);
					canMoveSouthEast = false;
				}else
					canMoveSouthEast = false;			
			}else{
				canMoveSouthEast = false;
			}
			// Valid locations in South west
			if( Position.isOnBoard((char)((int)currentPosition.getColumn()-i), currentPosition.getRow()-i) && canMoveSouthWest){	
				if((currentState.getPieceAt(new Position((char)((int)currentPosition.getColumn()-i), currentPosition.getRow()-i)) == null)){
					Position newPosition = new Position((char)((int)currentPosition.getColumn()-i), currentPosition.getRow()-i);
					allValidPositions.add(newPosition);									
				}else if(currentState.getPieceAt(new Position((char)((int)currentPosition.getColumn()-i), currentPosition.getRow()-i)).getOwner() != currentState.getPieceAt(currentPosition).getOwner()){
					Position newPosition = new Position((char)((int)currentPosition.getColumn()-i), currentPosition.getRow()-i);
					allValidPositions.add(newPosition);
					canMoveSouthWest = false;
				}else
					canMoveSouthWest = false;			
			}else{
				canMoveSouthWest = false;
			}

			if(canMoveNorthEast == false && canMoveNorthWest == false && canMoveSouthEast == false && canMoveSouthWest == false){
				break;
			}
		}	

		return allValidPositions;	
	}	
}

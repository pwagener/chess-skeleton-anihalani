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

	//@Amit - This function will return a set of all possible moves for a Bishop at <currentPosition>
	@Override
	public Set<Position> getValidMoves(GameState currentState, Position origin) {
		//This set would contain a list of all valid positions
		Set<Position> allValidPositions = new HashSet<Position>();

		// These variables help us to identify when to stop searching in a particular direction.
		// i.e. if the position is out of board or if there is a collision with opposite color player
		boolean checkNorthEast = true;
		boolean checkNorthWest = true;
		boolean checkSouthEast = true;
		boolean checkSouthWest = true;

		for(int i=1; i<=8; i++){    		
			// Valid locations in North east
			if( Position.isOnBoard((char)((int)origin.getColumn()+i), origin.getRow()+i) && checkNorthEast){	
				if((currentState.getPieceAt(new Position((char)((int)origin.getColumn()+i), origin.getRow()+i)) == null )){
					Position newPosition = new Position((char)((int)origin.getColumn()+i), origin.getRow()+i);
					allValidPositions.add(newPosition);									
				}else if(currentState.getPieceAt(new Position((char)((int)origin.getColumn()+i), origin.getRow()+i)).getOwner() != currentState.getPieceAt(origin).getOwner()){
					Position newPosition = new Position((char)((int)origin.getColumn()+i), origin.getRow()+i);
					allValidPositions.add(newPosition);
					checkNorthEast = false;
				}else
					checkNorthEast = false;			
			}else{
				checkNorthEast = false;
			}
			// Valid locations in North west
			if( Position.isOnBoard((char)((int)origin.getColumn()-i), origin.getRow()+i) && checkNorthWest){	
				if((currentState.getPieceAt(new Position((char)((int)origin.getColumn()-i), origin.getRow()+i)) == null)){
					Position newPosition = new Position((char)((int)origin.getColumn()-i), origin.getRow()+i);
					allValidPositions.add(newPosition);									
				}else if(currentState.getPieceAt(new Position((char)((int)origin.getColumn()-i), origin.getRow()+i)).getOwner() != currentState.getPieceAt(origin).getOwner()){
					Position newPosition = new Position((char)((int)origin.getColumn()-i), origin.getRow()+i);
					allValidPositions.add(newPosition);
					checkNorthWest = false;
				}else
					checkNorthWest = false;			
			}else{
				checkNorthWest = false;
			}
			// Valid locations in South east
			if( Position.isOnBoard((char)((int)origin.getColumn()+i), origin.getRow()-i) && checkSouthEast){	
				if((currentState.getPieceAt(new Position((char)((int)origin.getColumn()+i), origin.getRow()-i)) == null)){
					Position newPosition = new Position((char)((int)origin.getColumn()+i), origin.getRow()-i);
					allValidPositions.add(newPosition);									
				}else if(currentState.getPieceAt(new Position((char)((int)origin.getColumn()+i), origin.getRow()-i)).getOwner() != currentState.getPieceAt(origin).getOwner()){
					Position newPosition = new Position((char)((int)origin.getColumn()+i), origin.getRow()-i);
					allValidPositions.add(newPosition);
					checkSouthEast = false;
				}else
					checkSouthEast = false;			
			}else{
				checkSouthEast = false;
			}
			// Valid locations in South west
			if( Position.isOnBoard((char)((int)origin.getColumn()-i), origin.getRow()-i) && checkSouthWest){	
				if((currentState.getPieceAt(new Position((char)((int)origin.getColumn()-i), origin.getRow()-i)) == null)){
					Position newPosition = new Position((char)((int)origin.getColumn()-i), origin.getRow()-i);
					allValidPositions.add(newPosition);									
				}else if(currentState.getPieceAt(new Position((char)((int)origin.getColumn()-i), origin.getRow()-i)).getOwner() != currentState.getPieceAt(origin).getOwner()){
					Position newPosition = new Position((char)((int)origin.getColumn()-i), origin.getRow()-i);
					allValidPositions.add(newPosition);
					checkSouthWest = false;
				}else
					checkSouthWest = false;			
			}else{
				checkSouthWest = false;
			}

			if(checkNorthEast == false && checkNorthWest == false && checkSouthEast == false && checkSouthWest == false){
				break;
			}
		}	
		return allValidPositions;
	}
}

package chess.pieces;

import java.util.HashSet;
import java.util.Set;

import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * The 'Rook' class
 */
public class Rook extends Piece {

	public Rook(Player owner) {
		super(owner);
	}

	@Override
	protected char getIdentifyingCharacter() {
		return 'r';
	}

	/**@author Amit
	 * Return a set of all valid moves for a Rook
	 * @param currentState - The current state of the game
	 * @param currentPosition - The current position of the Rook
	 * @return Set of valid positions
	 */
	@Override
	public Set<Position> getValidMoves(GameState currentState, Position currentPosition) {
		//This set would contain a list of all valid positions
		Set<Position> allValidPositions = new HashSet<Position>();    	
		//Creating flag variables for each direction of a Rook
		boolean moveUp, moveDown, moveLeft, moveRight;
		moveUp = moveDown = moveLeft = moveRight = true;    	
		for(int i=1; i<=8; i++){    		
			// possible moves for Rook to go UP
			if(Position.isOnBoard((char)((int)currentPosition.getColumn()), currentPosition.getRow()+i) && (moveUp == true)){
				if((currentState.getPieceAt(new Position((char)((int)currentPosition.getColumn()), currentPosition.getRow()+i)) == null )){
					Position newPosition = new Position((char)((int)currentPosition.getColumn()), currentPosition.getRow()+i);
					allValidPositions.add(newPosition);
				}else if(currentState.getPieceAt(new Position((char)((int)currentPosition.getColumn()), currentPosition.getRow()+i)).getOwner() 
						!= currentState.getPieceAt(currentPosition).getOwner()){
					Position newPosition = new Position((char)((int)currentPosition.getColumn()), currentPosition.getRow()+i);
					allValidPositions.add(newPosition);
					moveUp = false; 
				}else
					moveUp = false;
			}
			else
				moveUp = false;

			// possible moves for Rook to go DOWN
			if(Position.isOnBoard(currentPosition.getColumn(), currentPosition.getRow()-i) && (moveDown == true)){
				if((currentState.getPieceAt(new Position(currentPosition.getColumn(), currentPosition.getRow()-i)) == null )){
					Position newPosition = new Position(currentPosition.getColumn(), currentPosition.getRow()-i);
					allValidPositions.add(newPosition);
				}else if(currentState.getPieceAt(new Position(currentPosition.getColumn(), currentPosition.getRow()-i)).getOwner() 
						!= currentState.getPieceAt(currentPosition).getOwner()){
					Position newPosition = new Position(currentPosition.getColumn(), currentPosition.getRow()-i);
					allValidPositions.add(newPosition);
					moveDown = false; 
				}else
					moveDown = false;
			}
			else
				moveDown = false;

			// possible moves for Rook to go LEFT
			if(Position.isOnBoard((char)((int)currentPosition.getColumn()-i), currentPosition.getRow()) && (moveLeft == true)){
				if((currentState.getPieceAt(new Position((char)((int)currentPosition.getColumn()-i), currentPosition.getRow())) == null )){
					Position newPosition = new Position((char)((int)currentPosition.getColumn()-i), currentPosition.getRow());
					allValidPositions.add(newPosition);
				}else if(currentState.getPieceAt(new Position((char)((int)currentPosition.getColumn()-i), currentPosition.getRow())).getOwner() 
						!= currentState.getPieceAt(currentPosition).getOwner()){
					Position newPosition = new Position((char)((int)currentPosition.getColumn()-i), currentPosition.getRow());
					allValidPositions.add(newPosition);
					moveLeft = false; 
				}else
					moveLeft = false;
			}
			else
				moveLeft = false;

			// possible moves for Rook to go RIGHT
			if(Position.isOnBoard((char)((int)currentPosition.getColumn()+i), currentPosition.getRow()) && (moveRight == true)){
				if((currentState.getPieceAt(new Position((char)((int)currentPosition.getColumn()+i), currentPosition.getRow())) == null )){
					Position newPosition = new Position((char)((int)currentPosition.getColumn()+i), currentPosition.getRow());
					allValidPositions.add(newPosition);
				}else if(currentState.getPieceAt(new Position((char)((int)currentPosition.getColumn()+i), currentPosition.getRow())).getOwner() 
						!= currentState.getPieceAt(currentPosition).getOwner()){
					Position newPosition = new Position((char)((int)currentPosition.getColumn()+i), currentPosition.getRow());
					allValidPositions.add(newPosition);
					moveLeft = false; 
				}else
					moveLeft = false;
			}
			else
				moveLeft = false;

			if((moveLeft == false) && (moveRight == false) && (moveUp == false) && (moveDown == false))
				break;
		}    	
		return allValidPositions;
	}
}

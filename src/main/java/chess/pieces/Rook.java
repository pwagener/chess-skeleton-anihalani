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

	boolean moveUp, moveDown, moveLeft, moveRight;

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
		// Set all Directions to True
		setDirectionsTrue();

		for(int i=1; i<=8; i++){    		
			// possible moves for Rook to go UP
			allValidPositions.addAll(getMovesForOffset(currentState, currentPosition, 0, i));
			// possible moves for Rook to go DOWN
			allValidPositions.addAll(getMovesForOffset(currentState, currentPosition, 0, -i));
			// possible moves for Rook to go LEFT
			allValidPositions.addAll(getMovesForOffset(currentState, currentPosition, -i, 0));
			// possible moves for Rook to go RIGHT
			allValidPositions.addAll(getMovesForOffset(currentState, currentPosition, i, 0));

			if((moveLeft == false) && (moveRight == false) && (moveUp == false) && (moveDown == false))
				break;
		}    	
		return allValidPositions;
	}

	private Set<Position> getMovesForOffset(GameState currentState, Position currentPosition, int xOffset, int yOffset){
		Set<Position> validOffsetPositions = new HashSet<Position>();

		if( Position.isOnBoard((char)((int)currentPosition.getColumn()+xOffset), currentPosition.getRow()+yOffset) 
				&& canMoveInDirection(xOffset, yOffset)){	
			if((currentState.getPieceAt(new Position((char)((int)currentPosition.getColumn()+xOffset), currentPosition.getRow()+yOffset)) == null )){
				Position newPosition = new Position((char)((int)currentPosition.getColumn()+xOffset), currentPosition.getRow()+yOffset);
				validOffsetPositions.add(newPosition);									
			}else if(currentState.getPieceAt(new Position((char)((int)currentPosition.getColumn()+xOffset), currentPosition.getRow()+yOffset)).getOwner() != currentState.getPieceAt(currentPosition).getOwner()){
				Position newPosition = new Position((char)((int)currentPosition.getColumn()+xOffset), currentPosition.getRow()+yOffset);
				validOffsetPositions.add(newPosition);
				setDirectionFalse(xOffset, yOffset);
			}else
				setDirectionFalse(xOffset, yOffset);			
		}else{
			setDirectionFalse(xOffset, yOffset);
		}
		return validOffsetPositions;
	}

	/**@author Amit
	 * Set the value for a particular direction depending on Offset
	 * @param xOffset - movement on X Axis
	 * @param YOffset - movement on  Axis
	 */
	private void setDirectionFalse(int xOffset, int yOffset){

		if(Math.signum(xOffset) == 0){
			//UP
			if(Math.signum(yOffset) == 1){
				this.moveUp = false;
			}else{ //Down
				this.moveDown = false;
			}
		}else{		
			//RIGHT
			if(Math.signum(xOffset) == 1){
				this.moveRight = false;
			}else{ //LEFT
				this.moveLeft = false;
			}			

		}
	}

	/**@author Amit
	 * Check if the Bishop can move in a direction defined by offsets
	 * @param xOffset - movement on X Axis
	 * @param YOffset - movement on Y Axis
	 * @return True if it can move in the given direction else return False
	 */
	private boolean canMoveInDirection(int xOffset, int yOffset){

		if(Math.signum(xOffset) == 0){
			//UP
			if(Math.signum(yOffset) == 1){
				return this.moveUp;
			}else{ //Down
				return this.moveDown;
			}
		}else{		
			//RIGHT
			if(Math.signum(xOffset) == 1){
				return this.moveRight;
			}else{ //LEFT
				return this.moveLeft;
			}			

		}
	}

	/**@author Amit
	 * Set all the directions that bishop can move to true.
	 */
	private void setDirectionsTrue(){
		this.moveDown = true;
		this.moveLeft = true;
		this.moveRight = true;
		this.moveUp = true;
	}

}

package chess.pieces;
import java.util.HashSet;
import java.util.Set;
import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * The Pawn
 */
public class Pawn extends Piece {
	public Pawn(Player owner) {
		super(owner);
	}

	@Override
	protected char getIdentifyingCharacter() {
		return 'p';
	}

	/**@author Amit
	 * Return a set of all valid moves for a Pawn
	 * @param currentState - The current state of the game
	 * @param currentPosition - The current position of the Pawn
	 * @return Set of valid positions
	 */
	@Override
	public Set<Position> getValidMoves(GameState currentState, Position currentPosition) {
		//This set would contain a list of all valid positions
		Set<Position> allValidPositions = new HashSet<Position>();
		// Pawn can move one block forward. It can move 2 blocks forward only if it is at its original place
		//For white king
		if(currentState.getCurrentPlayer() == Player.White){
			allValidPositions.addAll(getPositionsForPawn(currentState, currentPosition, 1));
		}else{
			allValidPositions.addAll(getPositionsForPawn(currentState, currentPosition, -1));
		}		
		return allValidPositions;
	}

	private Set<Position> getPositionsForPawn(GameState currentState, Position currentPosition, int offset){
		Set<Position> allValidPositions = new HashSet<Position>();

		// Normal move - offset block forward
		if(Position.isOnBoard(currentPosition.getColumn(), currentPosition.getRow()+offset)){    					
			if((currentState.getPieceAt(new Position(currentPosition.getColumn(), currentPosition.getRow()+offset)) == null )){
				Position newPosition = new Position(currentPosition.getColumn(), currentPosition.getRow()+offset);
				allValidPositions.add(newPosition);
			} 
		}  
		// (2*offset) block move forward from the Original Position
		if(this.checkIfStartingPosition(currentState, currentPosition) && Position.isOnBoard(currentPosition.getColumn(), currentPosition.getRow()+(2*offset))){    					
			if((currentState.getPieceAt(new Position(currentPosition.getColumn(), currentPosition.getRow()+(2*offset))) == null ) 
					&& (currentState.getPieceAt(new Position(currentPosition.getColumn(), currentPosition.getRow()+offset)) == null )){
				Position newPosition = new Position(currentPosition.getColumn(), currentPosition.getRow()+(2*offset));
				allValidPositions.add(newPosition);
			} 
		}
		// diagonal moves for kill
		// right diagonal
		if(Position.isOnBoard((char)((int)currentPosition.getColumn()+offset), currentPosition.getRow()+offset)){
			if(currentState.getPieceAt(new Position((char)((int)currentPosition.getColumn()+offset), currentPosition.getRow()+offset)) != null) {
				if(currentState.getPieceAt(new Position((char)((int)currentPosition.getColumn()+offset), currentPosition.getRow()+offset)).getOwner()
						!= currentState.getPieceAt(currentPosition).getOwner()){
					Position newPosition = new Position((char)((int)currentPosition.getColumn()+offset), currentPosition.getRow()+offset);
					allValidPositions.add(newPosition);
				}
			}
		}
		//left diagonal
		if(Position.isOnBoard((char)((int)currentPosition.getColumn()-offset), currentPosition.getRow()+offset)){
			if(currentState.getPieceAt(new Position((char)((int)currentPosition.getColumn()-offset), currentPosition.getRow()+offset)) != null 
					&& currentState.getPieceAt(new Position((char)((int)currentPosition.getColumn()-offset), currentPosition.getRow()+offset)).getOwner()
					!= currentState.getPieceAt(currentPosition).getOwner()){
				Position newPosition = new Position((char)((int)currentPosition.getColumn()-offset), currentPosition.getRow()+offset);
				allValidPositions.add(newPosition);
			}
		}	
		return allValidPositions;
	}

	private boolean checkIfStartingPosition(GameState currentState, Position position){
		if((currentState.getCurrentPlayer() == Player.White) && position.getRow() == 2)
			return true;
		else if((currentState.getCurrentPlayer() == Player.Black) && position.getRow() == 7)
			return true;

		return false;
	}
}

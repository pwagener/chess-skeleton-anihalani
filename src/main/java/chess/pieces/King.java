package chess.pieces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * The King class
 */
public class King extends Piece {
	public King(Player owner) {
		super(owner);
	}

	@Override
	protected char getIdentifyingCharacter() {
		return 'k';
	}

	//@Amit - This function will return a set of all possible moves for a King at <currentPosition>
	@Override
	public Set<Position> getValidMoves(GameState currentState, Position currentPosition) {
		//This set would contain a list of all valid positions
		Set<Position> allValidPositions = new HashSet<Position>();
		/* This list will contain all the possible co-ordinate changes 
    			that need to be made to acquire possible positions for a king */
		ArrayList<List<Integer>> possibleCoordinateChanges = new ArrayList<List<Integer>>();
		// UP and DOWN
		possibleCoordinateChanges.add(Arrays.asList(0,1));
		possibleCoordinateChanges.add(Arrays.asList(0,-1));
		// Left and Right
		possibleCoordinateChanges.add(Arrays.asList(-1,0));
		possibleCoordinateChanges.add(Arrays.asList(1,0));    			
		// North East and North West
		possibleCoordinateChanges.add(Arrays.asList(1,1));
		possibleCoordinateChanges.add(Arrays.asList(-1,1));
		// South East and South West
		possibleCoordinateChanges.add(Arrays.asList(1,-1));
		possibleCoordinateChanges.add(Arrays.asList(-1,-1));
		
		for(List<Integer> l : possibleCoordinateChanges){
			if(Position.isOnBoard((char)((int)currentPosition.getColumn()+l.get(0)), currentPosition.getRow()+l.get(1))){
				if((currentState.getPieceAt(new Position((char)((int)currentPosition.getColumn()+l.get(0)), currentPosition.getRow()+l.get(1))) == null )){
					Position newPosition = new Position((char)((int)currentPosition.getColumn()+l.get(0)), currentPosition.getRow()+l.get(1));
					allValidPositions.add(newPosition);
				}else if(currentState.getPieceAt(new Position((char)((int)currentPosition.getColumn()+l.get(0)), currentPosition.getRow()+l.get(1))).getOwner() 
						!= currentState.getPieceAt(currentPosition).getOwner()){
					Position newPosition = new Position((char)((int)currentPosition.getColumn()+l.get(0)), currentPosition.getRow()+l.get(1));
					allValidPositions.add(newPosition);
				}
			}
		}
		return allValidPositions;	
	}
}

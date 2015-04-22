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
 * The Knight class
 */
public class Knight extends Piece {
	public Knight(Player owner) {
		super(owner);
	}

	@Override
	protected char getIdentifyingCharacter() {
		return 'n';
	}
	//@Amit - This function will return a set of all possible moves for a Knight at <currentPosition>
	@Override
	public Set<Position> getValidMoves(GameState currentState, Position currentPosition) {

		//This set would contain a list of all valid positions
		Set<Position> allValidPositions = new HashSet<Position>();

		/* This list will contain all the possible co-ordinate changes 
		that need to be made to acquire possible positions for a knight */
		ArrayList<List<Integer>> possibleCoordinatesForKnight = new ArrayList<List<Integer>>();
		// North East direction: x+1,y+2 and x+2,y-1
		possibleCoordinatesForKnight.add(Arrays.asList(1,2));
		possibleCoordinatesForKnight.add(Arrays.asList(2,1));
		// North West direction: x-1,y+2 and x-2,y+1
		possibleCoordinatesForKnight.add(Arrays.asList(-1,2));
		possibleCoordinatesForKnight.add(Arrays.asList(-2,1));
		// South East direction: x+2,y-1 and x+1,y-2
		possibleCoordinatesForKnight.add(Arrays.asList(2,-1));
		possibleCoordinatesForKnight.add(Arrays.asList(1,-2));
		// South West direction: x-1,y-2 and x-2,y-1
		possibleCoordinatesForKnight.add(Arrays.asList(-1,-2));
		possibleCoordinatesForKnight.add(Arrays.asList(-2,-1));

		for(List<Integer> l : possibleCoordinatesForKnight){
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

package chess.pieces;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import chess.GameState;
import chess.Player;
import chess.Position;

public class KingTest {

	private King king;
	private Position start;
	private GameState currentState;

	@Before
	public void setUp() {
		king = new King(Player.White);
		start = new Position("e1");
		currentState = new GameState();
		currentState.reset();	
	}

	@Test
	public void testIfListIsEmptyForInitialBoard(){
		Set<Position> positions = new HashSet<Position>();    	
		//Set<String> expectedResults = populateExpectedResults();    	

		// getting the set of positions for the queen at position d4 from the logic we have implemented
		positions = king.getValidMoves(currentState, start);

		// checking if null is not returned
		assertNotNull(positions);

		//checking that an empty list is returned for original/un moved chess board
		assertTrue(positions.isEmpty());
	}

	@Test
	public void testIfListIsValid(){
		Set<Position> positions = new HashSet<Position>();    	
    	Set<String> expectedResults = populateExpectedResults();  
		// Inserting a dummy king at d4  and a few dummy pieces for testing purpose					
		currentState.placePiece(new King(currentState.getCurrentPlayer()), new Position("d4"));
		currentState.placePiece(new King(Player.Black), new Position("e5"));
		currentState.placePiece(new King(Player.White), new Position("c3"));
		

		// getting the set of positions for the queen at position d4 from the logic we have implemented
		positions = king.getValidMoves(currentState, new Position("d4"));
		
		// checking if all values in result are present in expected result
    	for(Position p : positions ){
    		assertTrue(expectedResults.contains(p.toString()));    		
    	}
    	
    	//checking if count of both sets are equal to assure same # of positions are returned
    	assertTrue(positions.size() == expectedResults.size());  
	}
	
	// populating a list for expected results
    public Set<String> populateExpectedResults(){
    	Set<String> expectedResults = new HashSet<String>();
    	
    	//UP and DOWN
    	expectedResults.add("d5");
    	expectedResults.add("d3");
    	//Left and Right
       	expectedResults.add("c4");
    	expectedResults.add("e4");
    	// North Diagonals
       	expectedResults.add("c5");
       	expectedResults.add("e5");
       	// South Diagonals
    	expectedResults.add("e3");
    
    	return expectedResults;
    }


}

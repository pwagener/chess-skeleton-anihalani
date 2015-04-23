/**
 * 
 */
package chess.pieces;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * @author Amit
 *
 */
public class QueenTest {

	private Queen queen;
	private Position start;
	private GameState currentState;

	@Before
	public void setUp() {
		queen = new Queen(Player.White);
		start = new Position("d4");
		currentState = new GameState();
		currentState.reset();
		
		// Removing Queen from original position and inserting at d4
		currentState.removePiece(new Position("d1"));
		currentState.placePiece(new Queen(currentState.getCurrentPlayer()), new Position("d4"));
	}
	
	/**
	 * Test method for {@link chess.pieces.Queen#getValidMoves(chess.GameState, chess.Position)}.
	 * Test for position d4
	 */	

	@Test
    public void testIfListIsValid(){
    	Set<Position> positions = new HashSet<Position>();    	
    	Set<String> expectedResults = populateExpectedResults();    	
    	    	
    	// getting the set of positions for the queen at position d4 from the logic we have implemented
    	positions = queen.getValidMoves(currentState, start);
    	
    	// checking if null is not returned
    	assertNotNull(positions);    	
    	
    	// checking if all values in result are present in expected result
    	for(Position p : positions ){
    		assertTrue(expectedResults.contains(p.toString()));    		
    	}
    	
    	//checking if count of both sets are equal to assure same # of positions are returned
    	assertTrue(positions.size() == expectedResults.size());    	
    	
    }
	
	@After
	public void resetState() {
		queen = null;
		start = null;
		currentState.reset();
	}
	
	
	
	/**@author Amit
	 * Populates a list for expected results 
	 */
    public Set<String> populateExpectedResults(){
    	Set<String> expectedResults = new HashSet<String>();
    	
    	//UP    	
    	expectedResults.add("d5");
    	expectedResults.add("d6");
    	expectedResults.add("d7");
    	
    	//Down
    	expectedResults.add("d3");
    	
    	//Right
    	expectedResults.add("e4");
    	expectedResults.add("f4");
    	expectedResults.add("g4");
    	expectedResults.add("h4");
    	
    	//Left
    	expectedResults.add("c4");
    	expectedResults.add("b4");
    	expectedResults.add("a4");
    	
    	//North East
    	expectedResults.add("e5");
    	expectedResults.add("f6");
    	expectedResults.add("g7");
    	   	
    	//North West
    	expectedResults.add("c5");
    	expectedResults.add("b6");
    	expectedResults.add("a7");
    	
    	//South East
    	expectedResults.add("e3");   	
    	
    	//South West
    	expectedResults.add("c3");
    	return expectedResults;
    }
}

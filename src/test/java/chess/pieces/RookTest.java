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

public class RookTest {

	private Rook rook;
	private Position start;
	private GameState currentState;

	@Before
	public void setUp() {
		rook = new Rook(Player.White);
		start = new Position("a1");
		currentState = new GameState();
		currentState.reset();
		
		// deleting pawn in front of rook for testing purpose
		currentState.removePiece(new Position("a2"));
		currentState.removePiece(new Position("b1"));
	}
	
	/**
	 * Test method for {@link chess.pieces.Rook#getValidMoves(chess.GameState, chess.Position)}.
	 * Test for position a1
	 */	

	@Test
    public void testIfListIsValid(){
    	Set<Position> positions = new HashSet<Position>();    	
    	Set<String> expectedResults = populateExpectedResults();    	
    	    	
    	// getting the set of positions for the original knight positions b1 and g1 from the logic we have implemented
    	positions = rook.getValidMoves(currentState, start);
    	    	
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
		rook = null;
		start = null;
		currentState.reset();
	}
	
	
	
	// populating a list for expected results
    public Set<String> populateExpectedResults(){
    	Set<String> expectedResults = new HashSet<String>();
    
    	//UP
    	expectedResults.add("a2");
    	expectedResults.add("a3");
    	expectedResults.add("a4");
    	expectedResults.add("a5");
    	expectedResults.add("a6");
    	expectedResults.add("a7");
    	
    	//Right
    	expectedResults.add("b1");
    	   	
    	return expectedResults;
    }

}

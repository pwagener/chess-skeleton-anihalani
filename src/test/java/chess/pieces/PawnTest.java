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

public class PawnTest {

	private Pawn pawn;
	private Position start;
	private GameState currentState;

	@Before
	public void setUp() {
		pawn = new Pawn(Player.White);
		start = new Position("a2");
		currentState = new GameState();
		currentState.reset();
		
		// inserting a dummy piece at b3 for testing purposes
		currentState.placePiece(new Knight(Player.Black), new Position("b3"));
	}
	
	/**
	 * Test method for {@link chess.pieces.Knight#getValidMoves(chess.GameState, chess.Position)}.
	 * Test for position b1
	 */	

	@Test
    public void testIfListIsValid(){
    	Set<Position> positions = new HashSet<Position>();    	
    	Set<String> expectedResults = populateExpectedResults();    	
    	    	
    	// getting the set of positions for the original knight positions b1 and g1 from the logic we have implemented
    	positions = pawn.getValidMoves(currentState, start);
    	
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
		pawn = null;
		start = null;
		currentState.reset();
	}
	
	
	
	// populating a list for expected results
    public Set<String> populateExpectedResults(){
    	Set<String> expectedResults = new HashSet<String>();
    	// single block, double block and diagonal move for kill    	
    	expectedResults.add("a3");
    	expectedResults.add("a4");
    	expectedResults.add("b3");
    	
    	return expectedResults;
    }


}

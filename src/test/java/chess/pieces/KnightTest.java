/**
 * 
 */
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

/**
 * @author Amit
 *
 */
public class KnightTest {

	private Knight knight;
	private Position start;
	private GameState currentState;

	@Before
	public void setUp() {
		knight = new Knight(Player.White);
		start = new Position("b1");
		currentState = new GameState();
		currentState.reset();
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
    	positions = knight.getValidMoves(currentState, start);
    	positions.addAll(knight.getValidMoves(currentState, new Position("g1")));
    	
    	// checking if null is not returned
    	assertNotNull(positions);    	
    	
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
    
    	//North east
    	expectedResults.add("c3");
    	expectedResults.add("h3");
 	
    	//North West
    	expectedResults.add("a3");
    	expectedResults.add("f3");
    	// South East
    	
    	
    	//South West
    	
    	
    	return expectedResults;
    }


}

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

import chess.pieces.Bishop;
import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * @author Amit
 *
 */
public class BishopTest {

	private Bishop bishop;
	private Position start;
	private GameState currentState;

	@Before
	public void setUp() {
		bishop = new Bishop(Player.White);
		start = new Position("c1");
		currentState = new GameState();
		currentState.reset();
	}	

	/**
	 * Test method for {@link chess.pieces.Bishop#getValidMoves(chess.GameState, chess.Position)}.
	 * Test for position c1, b2
	 * for c1, an empty list should return
	 */	

	@Test
	public void testIfListIsValid(){
		Set<Position> positions = new HashSet<Position>();    	
		Set<String> expectedResults = populateExpectedResults();

		// getting the set of positions for c1 from the logic we have implemented
		positions = bishop.getValidMoves(currentState, start);
		// checking if null is not returned
		assertNotNull(positions);
		assertTrue(positions.isEmpty());
		start = new Position("b2");
		// getting the set of positions from the logic we have implemented
		positions = bishop.getValidMoves(currentState, start);
		// checking if null is not returned
		assertNotNull(positions);    	

		// checking if all values in result are present in expected result
		for(Position p : positions ){
			assertTrue(expectedResults.contains(p.toString()));    		
		}    	
		//checking if count of both sets are equal to assure same # of positions are returned
		assertTrue(positions.size() == expectedResults.size());    	    	
	}

	/**@author Amit
	 * Populates a list for expected results for valid moves
	 */
	public Set<String> populateExpectedResults(){
		Set<String> expectedResults = new HashSet<String>();
		//North east
		expectedResults.add("c3");
		expectedResults.add("d4");
		expectedResults.add("e5");
		expectedResults.add("f6");
		expectedResults.add("g7");    	
		//North West
		expectedResults.add("a3");
		// South East has no valid positions
		//South West has no valid positions
		return expectedResults;
	}

	/**@author Amit
	 * Populates a list for expected results after removing moves that cause a check
	 */
	public Set<String> populateFinalExpectedResults(){
		Set<String> expectedResults = new HashSet<String>();

		expectedResults.add("g3");
		expectedResults.add("h4");
		return expectedResults;
	}

	/**@author Amit
	 * Tests if the positions that put king at check are removed 
	 */
	@Test
	public void testRemoveCheckPositions(){
		Set<Position> positions = new HashSet<Position>();    	
		Set<String> expectedResults = populateFinalExpectedResults();

		currentState.movePiece(new Position("d8"), new Position("h4"));
		currentState.movePiece(new Position("c1"), new Position("f2"));

		// getting the set of positions from the logic we have implemented
		positions = bishop.getValidMoves(currentState, new Position("f2"));
		Set<Position> finalSet = bishop.removeCheckPositions(positions, currentState, new Position("f2"));

		// checking if all values in result are present in expected result
		for(Position p : finalSet ){
			assertTrue(expectedResults.contains(p.toString()));    		
		}    	
		//checking if count of both sets are equal to assure same # of positions are returned
		assertTrue(finalSet.size() == expectedResults.size());    
	}

}

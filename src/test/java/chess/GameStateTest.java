package chess;

import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Basic unit tests for the GameState class
 */
public class GameStateTest {

    private GameState state;

    @Before
    public void setUp() {
        state = new GameState();
    }

    @Test
    public void testStartsEmpty() {
        // Make sure all the positions are empty
        for (char col = Position.MIN_COLUMN; col <= Position.MAX_COLUMN; col++) {
            for (int row = Position.MIN_ROW; row <= Position.MAX_ROW; row++) {
                assertNull("All pieces should be empty", state.getPieceAt(String.valueOf(col) + row));
            }
        }
    }

    @Test
    public void testInitialGame() {
        // Start the game
        state.reset();

        // White should be the first player
        Player current = state.getCurrentPlayer();
        assertEquals("The initial player should be White", Player.White, current);

        // Spot check a few pieces
        Piece whiteRook = state.getPieceAt("a1");
        assertTrue("A rook should be at a1", whiteRook instanceof Rook);
        assertEquals("The rook at a1 should be owned by White", Player.White, whiteRook.getOwner());


        Piece blackQueen = state.getPieceAt("d8");
        assertTrue("A queen should be at d8", blackQueen instanceof Queen);
        assertEquals("The queen at d8 should be owned by Black", Player.Black, blackQueen.getOwner());
    }
    
    @Test
    public void testCheckMate() {
        // Start the game
        state.reset();
        CLI cli = new CLI(System.in, System.out);
        cli.setGameState(state);

        // White should be the first player
        Player current = state.getCurrentPlayer();
        assertEquals("The initial player should be White", Player.White, current);

        // Move a few pieces for unit testing purpose
        state.movePiece(new Position("g2"), new Position("g4"));
        state.movePiece(new Position("f2"), new Position("f4"));
        state.movePiece(new Position("e7"), new Position("e5"));
        state.movePiece(new Position("d8"), new Position("h4"));
        
        assertTrue("It is a check and mate. The Black Player has Won.",state.isKingUnderThreat() && cli.getAllPossibleMoves().isEmpty());
    }
}

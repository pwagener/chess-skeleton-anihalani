package chess;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Basic unit tests for the CLITest
 */
@RunWith(MockitoJUnitRunner.class)
public class CLITest {

    @Mock
    private PrintStream testOut;

    @Mock
    private InputStream testIn;

    /**
     * Make sure the CLI initially prints a welcome message
     */
    @Test
    public void testCLIWritesWelcomeMessage() {
        new CLI(testIn, testOut);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(testOut, times(1)).println(captor.capture());

        String message = captor.getValue();

        assertTrue("The CLI should initially print a welcome message", message.startsWith("Welcome"));
    }

    /**
     * Test that the CLI can initially accept input
     */
    @Test
    public void testHelpCommand() throws Exception {
        runCliWithInput("help");

        List<String> output = captureOutput();
        assertEquals("Should have 13 output calls", 13, output.size());
    }

    @Test
    public void testNewCommand() throws Exception {
        runCliWithInput("new");
        List<String> output = captureOutput();

        assertEquals("Should have had 6 calls to print strings", 6, output.size());
        assertEquals("It should have printed the board first", 721, output.get(2).length());
        assertEquals("It should have printed the board again", 721, output.get(4).length());
    }

    @Test
    public void testBoardCommand() throws Exception {
        runCliWithInput("new", "board");
        List<String> output = captureOutput();

        assertEquals("Should have had 9 output calls", 9, output.size());
        assertEquals("It should have printed the board three times", output.get(2), output.get(4));
    }

    private List<String> captureOutput() {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        // 9 times means we printed Welcome, the input prompt twice, and the 'help' screen
        verify(testOut, atLeastOnce()).println(captor.capture());

        return captor.getAllValues();
    }
    
    //@Amit - Unit Test for Move command
    @Test
    public void testMoveCommand() throws Exception {
        runCliWithInput("move a2 a4");
        List<String> output = captureOutput();

        assertEquals("Should have had 7 output calls", 7, output.size());
        assertEquals("It should have printed the board first", 721, output.get(2).length());
        assertEquals("It should have printed the board again after move", 721, output.get(5).length());
    }
    
    //@Amit - Unit Test for List command
    @Test
    public void testListCommand() throws Exception {
        runCliWithInput("list");
        List<String> output = captureOutput();

        assertEquals("Should have had 26 output calls", 26, output.size());
        assertEquals("It should have printed the board first", 721, output.get(2).length());
        assertTrue("The output contains possible moves like a2 a4, b2 b3, b1 c3", output.contains("a2 a4") 
        		&& output.contains("b2 b3") && output.contains("b1 c3"));
        assertEquals("It should have printed the board again after the list", 721, output.get(24).length());
    }

    private CLI runCliWithInput(String... inputLines) {
        StringBuilder builder = new StringBuilder();
        for (String line : inputLines) {
            builder.append(line).append(System.getProperty("line.separator"));
        }

        ByteArrayInputStream in = new ByteArrayInputStream(builder.toString().getBytes());
        CLI cli = new CLI(in, testOut);
        cli.startEventLoop();

        return cli;
    }
}

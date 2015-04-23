package chess;

import chess.pieces.Piece;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * This class provides the basic CLI interface to the Chess game.
 */
public class CLI {
	private static final String NEWLINE = System.getProperty("line.separator");

	private final BufferedReader inReader;
	private final PrintStream outStream;

	private GameState gameState = null;

	public CLI(InputStream inputStream, PrintStream outStream) {
		this.inReader = new BufferedReader(new InputStreamReader(inputStream));
		this.outStream = outStream;
		writeOutput("Welcome to Chess!");
	}

	/**
	 * Write the string to the output
	 * @param str The string to write
	 */
	private void writeOutput(String str) {
		this.outStream.println(str);
	}

	/**
	 * Retrieve a string from the console, returning after the user hits the 'Return' key.
	 * @return The input from the user, or an empty-length string if they did not type anything.
	 */
	private String getInput() {
		try {
			this.outStream.print("> ");
			return inReader.readLine();
		} catch (IOException e) {
			throw new RuntimeException("Failed to read from input: ", e);
		}
	}

	void startEventLoop() {
		writeOutput("Type 'help' for a list of commands.");
		doNewGame();

		while (true) {
			showBoard();
			if(gameState.isKingUnderThreat() && getAllPossibleMoves().isEmpty()){
				writeOutput("The game is over."+gameState.getCurrentPlayer()+" lost. \n");
				startEventLoop();
			}

			writeOutput(gameState.getCurrentPlayer() + "'s Move");

			if(gameState.isKingUnderThreat()){
				writeOutput("You have a check ! Your king is under threat !");
			}				

			String input = getInput();
			if (input == null) {
				break; // No more input possible; this is the only way to exit the event loop
			} else if (input.length() > 0) {
				if (input.equals("help")) {
					showCommands();
				} else if (input.equals("new")) {
					doNewGame();
				} else if (input.equals("quit")) {
					writeOutput("Goodbye!");
					System.exit(0);
				} else if (input.equals("board")) {
					writeOutput("Current Game:");
				} else if (input.equals("list")) {                    
					// @Amit - 	the list command will display all the possible moves for the current player
					displayPossibleMoves();
				} else if (input.startsWith("move")) {
					//writeOutput("====> Move Is Not Implemented (yet) <====");
					String fromPosition = input.split(" ")[1];
					String toPosition = input.split(" ")[2];
					move(fromPosition, toPosition);					
				} else {
					writeOutput("I didn't understand that.  Type 'help' for a list of commands.");
				}
			}
		}
	}

	private void doNewGame() {
		gameState = new GameState();
		gameState.reset();
	}

	private void showBoard() {
		writeOutput(getBoardAsString());
	}

	private void showCommands() {
		writeOutput("Possible commands: ");
		writeOutput("    'help'                       Show this menu");
		writeOutput("    'quit'                       Quit Chess");
		writeOutput("    'new'                        Create a new game");
		writeOutput("    'board'                      Show the chess board");
		writeOutput("    'list'                       List all possible moves");
		writeOutput("    'move <colrow> <colrow>'     Make a move");
	}

	/**
	 * Display the board for the user(s)
	 */
	String getBoardAsString() {
		StringBuilder builder = new StringBuilder();
		builder.append(NEWLINE);

		printColumnLabels(builder);
		for (int i = Position.MAX_ROW; i >= Position.MIN_ROW; i--) {
			printSeparator(builder);
			printSquares(i, builder);
		}

		printSeparator(builder);
		printColumnLabels(builder);

		return builder.toString();
	}


	private void printSquares(int rowLabel, StringBuilder builder) {
		builder.append(rowLabel);

		for (char c = Position.MIN_COLUMN; c <= Position.MAX_COLUMN; c++) {
			Piece piece = gameState.getPieceAt(String.valueOf(c) + rowLabel);
			char pieceChar = piece == null ? ' ' : piece.getIdentifier();
			builder.append(" | ").append(pieceChar);
		}
		builder.append(" | ").append(rowLabel).append(NEWLINE);
	}

	private void printSeparator(StringBuilder builder) {
		builder.append("  +---+---+---+---+---+---+---+---+").append(NEWLINE);
	}

	private void printColumnLabels(StringBuilder builder) {
		builder.append("   ");
		for (char c = Position.MIN_COLUMN; c <= Position.MAX_COLUMN; c++) {
			builder.append(" ").append(c).append("  ");
		}

		builder.append(NEWLINE);
	}

	//@Amit - This function will create a list of all valid and possible moves
	private void displayPossibleMoves(){
		List<String> allPossibleMoves = getAllPossibleMoves();
		for(String  s : allPossibleMoves){
			writeOutput(s);    		 
		}
	}

	//@Amit - This function will return all Possible moves
	private List<String> getAllPossibleMoves(){
		List<String> allPossibleMoves = new ArrayList<String>();
		Position[] allPositions = gameState.getAllPiecesOnBoard().keySet().toArray(new Position[0]);
		for(Position p : allPositions){
			Piece tempPiece = gameState.getAllPiecesOnBoard().get(p);
			if(tempPiece.getOwner() == gameState.getCurrentPlayer()){
				allPossibleMoves.addAll(getValidMovesForPiece(gameState,tempPiece, p));				
			}
		}   
		return allPossibleMoves;
	}

	//@Amit - This function will covert a set of possible positions for a piece at position p to a list of strings 
	private List<String> getValidMovesForPiece(GameState gameState, Piece piece, Position p){
		Set<Position> logicallyValidPositions = piece.getValidMoves(gameState, p);
		Position[] validPositions = piece.removeCheckPositions(logicallyValidPositions, gameState, p).toArray(new Position[0]);
		List<String> fromAndToMoves = new ArrayList<String>();    	
		for(int i =0; i < validPositions.length; i++){
			fromAndToMoves.add(p+" "+validPositions[i]);
		}
		return fromAndToMoves;    	
	}

	private boolean move(String fromPos, String toPosition){	
		Position fromPosition = new Position(fromPos);		
		Piece piece = gameState.getPieceAt(fromPosition);
		if((piece==null) || (gameState.getCurrentPlayer() != piece.getOwner())){
			writeOutput("The <FROM> position you provided is not correct. "
					+ "There is no piece on that location OR the the location is not on the board");
		}else{

			Set<Position> logicallyValidMoves = piece.getValidMoves(gameState, fromPosition);
			//@Amit -  Make a move only if the <To> position is Valid
			if(piece.removeCheckPositions(logicallyValidMoves, gameState, fromPosition).toString().contains(toPosition) ){
				gameState.movePiece(fromPosition, new Position(toPosition));
				writeOutput("Move from " +fromPosition + " to " +toPosition + " is successful!");				
				gameState.changePlayer();
				return true;
			}else{
				writeOutput("This is not a valid move");
				return false;				
			}
		}

		return false;
	}


	public static void main(String[] args) {
		CLI cli = new CLI(System.in, System.out);
		cli.startEventLoop();
	}
}
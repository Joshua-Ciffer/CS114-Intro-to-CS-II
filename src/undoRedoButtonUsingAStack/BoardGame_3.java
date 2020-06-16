package undoRedoButtonUsingAStack;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

@SuppressWarnings({"javadoc", "unused"})
public class BoardGame_3 extends Application {

	// Turn codes
	private static final int TURN_BLACK = 0;
	private static final int TURN_WHITE = 1;

	// Game image objects
	Image emptyImage = new Image("file:D:\\Programming\\Projects\\CS114 - Intro to CS II\\src\\undoRedoButtonUsingAStack\\emptySquare80.gif");
	Image blackImage = new Image("file:D:\\Programming\\Projects\\CS114 - Intro to CS II\\src\\undoRedoButtonUsingAStack\\blackPiece80.gif");
	Image whiteImage = new Image("file:D:\\Programming\\Projects\\CS114 - Intro to CS II\\src\\undoRedoButtonUsingAStack\\whitePiece80.gif");
	Image blackMoveImage = new Image("file:D:\\Programming\\Projects\\CS114 - Intro to CS II\\src\\undoRedoButtonUsingAStack\\blackMoveSquare80.gif");
	Image whiteMoveImage = new Image("file:D:\\Programming\\Projects\\CS114 - Intro to CS II\\src\\undoRedoButtonUsingAStack\\whiteMoveSquare80.gif");

	// Game image objects
	double imageWidth = emptyImage.getWidth();
	double imageHeight = emptyImage.getHeight();

	// Current player turn
	private int turn = TURN_BLACK;

	// Categorized images
	private Image moveImages[] = {blackImage, whiteImage};
	private Image selectImages[] = {blackMoveImage, whiteMoveImage};

	// Specified image getters
	Image currentTurnImage = moveImages[turn];
	Image previousTurnImage = moveImages[1 - turn];

	// Game canvas
	Canvas canvas = new Canvas(imageWidth * NUM_ROWS, imageHeight * NUM_COLS);
	GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

	// Entry point
	public static void main(String[] args) {
		launch(args);
	}

	// Game screen size
	private static final int NUM_ROWS = 8;
	private static final int NUM_COLS = 8;

	// Game board
	private Image board[][] = new Image[NUM_ROWS][NUM_COLS];
	private final Group group = new Group(canvas);
	private final Scene scene = new Scene(group, imageWidth * NUM_COLS, imageHeight * NUM_ROWS);

	// Returns image at specified board location
	private Image getBoardContent(int row, int col) {
		if (row < 0 || row > 7)
			return null;
		if (col < 0 || col > 7)
			return null;
		return board[row][col];
	}

	// Inits the GUI
	@Override
	public void start(Stage stage) throws Exception {

		for (int row = 0; row < NUM_ROWS; row++) {
			for (int col = 0; col < NUM_COLS; col++) {
				graphicsContext.drawImage(emptyImage, col * imageWidth, row * imageHeight);
				board[row][col] = emptyImage;
			}
		}
		// Put code here to setup initial position
		graphicsContext.drawImage(whiteImage, 3 * imageWidth, 4 * imageHeight);
		graphicsContext.drawImage(blackImage, 4 * imageWidth, 4 * imageHeight);
		graphicsContext.drawImage(blackImage, 3 * imageWidth, 3 * imageHeight);
		graphicsContext.drawImage(whiteImage, 4 * imageWidth, 3 * imageHeight);
		
		stage.setScene(scene);
		stage.setTitle("Board Game 3");
		stage.show();

		// Handle Mouse Events
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				int row = (int)(e.getY() / imageHeight);
				int col = (int)(e.getX() / imageWidth);
				// --------------------------------------------------------------------
				// Check clicked square is empty.
				// Compute flips. Not a valid move if no flips.
				//
				graphicsContext.drawImage(currentTurnImage, col * imageWidth, row * imageHeight);
				board[row][col] = currentTurnImage;
				turn = 1 - turn;
				currentTurnImage = moveImages[turn];
				previousTurnImage = moveImages[1 - turn];
				System.out.println((int)(e.getY() / imageHeight) + ", " + (int)(e.getX() / imageWidth));
			}
		};

		scene.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
	}

	private class Move {
		int turn;
		int row_move;
		int col_move;
		int flip_count;
		int row_flip[];
		int col_flip[];
	}

}
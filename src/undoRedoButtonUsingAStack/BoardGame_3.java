package undoRedoButtonUsingAStack;

import java.util.ArrayList;

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
	final Image emptyImage = new Image("file:D:\\Programming\\Projects\\CS114 - Intro to CS II\\src\\undoRedoButtonUsingAStack\\emptySquare80.gif");
	final Image blackImage = new Image("file:D:\\Programming\\Projects\\CS114 - Intro to CS II\\src\\undoRedoButtonUsingAStack\\blackPiece80.gif");
	final Image whiteImage = new Image("file:D:\\Programming\\Projects\\CS114 - Intro to CS II\\src\\undoRedoButtonUsingAStack\\whitePiece80.gif");
	final Image blackMoveImage = new Image("file:D:\\Programming\\Projects\\CS114 - Intro to CS II\\src\\undoRedoButtonUsingAStack\\blackMoveSquare80.gif");
	final Image whiteMoveImage = new Image("file:D:\\Programming\\Projects\\CS114 - Intro to CS II\\src\\undoRedoButtonUsingAStack\\whiteMoveSquare80.gif");

	// Game image objects
	final double imageWidth = emptyImage.getWidth();
	final double imageHeight = emptyImage.getHeight();

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
	private final Scene scene = new Scene(group, imageWidth * NUM_ROWS, imageHeight * NUM_COLS);

	// Returns image at specified board location
	private Image getBoardContent(int row, int col) {
		if (row < 0 || row > 7)
			return null;
		if (col < 0 || col > 7)
			return null;
		return board[row][col];
	}

	// Flips color of game piece
	private void flip(int row, int col) {
		if (getBoardContent(row, col) == whiteImage) {
			board[row][col] = blackImage;
			graphicsContext.drawImage(blackImage, row * imageWidth, col * imageHeight);
		} else if (getBoardContent(row, col) == blackImage) {
			board[row][col] = whiteImage;
			graphicsContext.drawImage(whiteImage, row * imageWidth, col * imageHeight);
		}
	}

	// Inits the GUI
	@Override
	public void start(Stage stage) throws Exception {

		for (int row = 0; row < NUM_ROWS; row++) {
			for (int col = 0; col < NUM_COLS; col++) {
				graphicsContext.drawImage(emptyImage, row * imageWidth, col * imageHeight);
				board[row][col] = emptyImage;
			}
		}
		// Put code here to setup initial position
		graphicsContext.drawImage(whiteImage, ((NUM_ROWS / 2) - 1) * imageWidth, (NUM_COLS / 2) * imageHeight);
		graphicsContext.drawImage(blackImage, (NUM_ROWS / 2) * imageWidth, (NUM_COLS / 2) * imageHeight);
		graphicsContext.drawImage(blackImage, ((NUM_ROWS / 2) - 1) * imageWidth, ((NUM_COLS / 2) - 1) * imageHeight);
		graphicsContext.drawImage(whiteImage, (NUM_ROWS / 2) * imageWidth, ((NUM_COLS / 2) - 1) * imageHeight);

		stage.setScene(scene);
		stage.setTitle("Board Game 3");
		stage.show();

		// Handle Mouse Events
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				int row = (int)(e.getX() / imageHeight);
				int col = (int)(e.getY() / imageWidth);
				// Check clicked square is empty
				if (getBoardContent(row, col) == emptyImage) {
					// Compute flips. Not a valid move if no flips.
					ArrayList<Integer> flipRow = new ArrayList<Integer>(0);
					ArrayList<Integer> flipCol = new ArrayList<Integer>(0);
					final int[] dX = {-1, 0, 1, -1, 1, -1, 0, 1};
					final int[] dY = {-1, -1, -1, 0, 0, 1, 1, 1};
					int x = row, y = col;
					boolean validMove = false;
					for (int i = 0; i < dX.length; i++) {
						boolean valid = false;
						for (int j = 0; j < NUM_ROWS; j++) {
							x += dX[i];
							y += dY[i];
							if ((x < 0) || (x >= NUM_ROWS) || (y < 0) || (y >= NUM_COLS)) {
								break;
							}
							if (board[x][y] == emptyImage) {
								break;
							} else if (board[x][y] != currentTurnImage) {
								valid = true;
							} else if (valid) {
								validMove = true;
							} else {
								break;
							}
						}
						validMove = false;
					}
					if (validMove) {
						// Draw object
						graphicsContext.drawImage(currentTurnImage, row * imageWidth, col * imageHeight);
						board[row][col] = currentTurnImage;
						// new Move(row, col, turn);
						for (int i = 0; i < dX.length; i++) {
							int x1 = row, y1 = col;
							for (int j = 0; j < NUM_ROWS; j++) {
								x1 += dX[i];
								y1 += dY[i];
								if ((x < 0) || (x >= NUM_ROWS) || (y < 0) || (y >= NUM_COLS)) {
									break;
								}
								if (board[x1][y1] == emptyImage) {
									break;
								} else if (board[x1][y1] != currentTurnImage) {
									flipRow.add(x1);
									flipCol.add(y1);
								} else {
									for (int k = 0; k < flipRow.size(); k++) {
										graphicsContext.drawImage(currentTurnImage, flipRow.get(k) * imageWidth, flipCol.get(k) * imageHeight);
										board[flipRow.get(k)][flipCol.get(k)] = currentTurnImage;
									}
								}
								flipRow.clear();
								flipCol.clear();
							}
						}
						// Reset for next turn
						turn = 1 - turn;
						currentTurnImage = moveImages[turn];
						previousTurnImage = moveImages[1 - turn];
						System.out.println(row + ", " + col);
					}

				}

			}
		};

		scene.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
	}

	private class Move {

		int turn;
		int moveRow;
		int moveCol;
		int numFlips;
		ArrayList<Integer> flipRow;
		ArrayList<Integer> flipCol;

		private Move(int turn, int moveRow, int moveCol, ArrayList<Integer> flipRow, ArrayList<Integer> flipCol) {
			this.turn = turn;
			this.moveRow = moveRow;
			this.moveCol = moveCol;
			this.flipRow = flipRow;
			this.flipCol = flipCol;
		}

	}

}
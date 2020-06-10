import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;  
import javafx.stage.Stage;  
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.animation.TranslateTransition; 
import javafx.animation.Animation; 
import javafx.util.Duration;  
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent; 
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;

//=========================================================================
// Main Class
//=========================================================================
public class BoardGame_2 extends Application 
{  
    private final int BLACK = 0;
    private final int WHITE = 1;

    Image im_empty = new Image( "emptySquare80.gif" );
    Image im_black = new Image( "blackPiece80.gif" );
    Image im_white = new Image( "whitePiece80.gif" );
    Image im_move_black = new Image( "blackMoveSquare80.gif" );
    Image im_move_white = new Image( "whiteMoveSquare80.gif" );
    
    double width = im_empty.getWidth();
    double height = im_empty.getHeight();
    
    private int turn = BLACK;
    
    private Image im_move_vec[] = {im_black, im_white};
    private Image im_mark_vec[] = {im_move_black, im_move_white};
    
    Image im_on_move = im_move_vec[turn];
    Image im_off_move = im_move_vec[1-turn];
    
    Canvas canvas = new Canvas( width*8, height*8);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    public static void main(String[] args) { launch(args); }
    
    private final int nrows = 8;
    private final int ncols = 8;
    
    private Image b[][] = new Image[nrows][ncols];
    private final Group group = new Group(canvas);
    private final Scene scene = new Scene(group, width*ncols, height*nrows, Color.BLACK );
//-------------------------------------------------------------------------
// start() method (build the GUI)
//
    @Override public void start(Stage stage) throws Exception 
    {

        
        final Group group = new Group(canvas);
        final Scene scene = new Scene(group, width*ncols, height*nrows, Color.BLACK );
        
        for (int row = 0; row < nrows; row++)
          for (int col = 0; col < ncols; col++)
              gc.drawImage(im_empty, col*width, row*height );
        
    
        stage.setScene(scene);
        stage.setTitle("Othello");
        stage.show();
        
        //--------------------------------------------------------------------
        // Handle Mouse Events
        //
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() 
        { 
            @Override 
            public void handle(MouseEvent e) 
            { 
                int row = (int)(e.getY()/height);
                int col = (int)(e.getX()/width);
                gc.drawImage(im_on_move, col*width, row*height );
                turn = 1 - turn;
                im_on_move = im_move_vec[turn];
                im_off_move = im_move_vec[1-turn];
                System.out.println((int)(e.getY()/height) + ", " + (int)(e.getX()/width)); 
            } 
        };// end EventHandler<MouseEvent>

        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
  }// end start 
    
//-------------------------------------------------------------------------
// Move class (stores a move object)
//    
  private class Move
  {
      int turn;
      int row_move;
      int col_move;
      int flip_count;
      int row_flip[];
      int col_flip[];
  }
  
}// end class Othello
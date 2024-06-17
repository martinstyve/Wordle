package no.uib.inf101.game2048;

import javax.swing.JFrame;

import no.uib.inf101.game2048.controller.GameController;
import no.uib.inf101.game2048.model.GameBoard;
import no.uib.inf101.game2048.model.GameModel;
import no.uib.inf101.game2048.model.numberedTile.NumberedTileFactory;
import no.uib.inf101.game2048.view.GameView;

/**
 * The GameMain class is the entry point for the 2048 game application. It
 * initializes the game model, view, and controller, and sets up the main JFrame
 * for the game window.
 */
public class GameMain {

  public static final String WINDOW_TITLE = "INF101 2048";

  public static void main(String[] args) {
    GameBoard board = new GameBoard(5, 6);
    // NumberedTileFactory factory = new RandomNumberedTileFactory();
    GameModel model = new GameModel(board);
    GameView view = new GameView(model);
    new GameController(model, view);

    JFrame frame = new JFrame(WINDOW_TITLE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(view);
    frame.pack();
    frame.setVisible(true);
    frame.setLocationRelativeTo(null); // centers frame
    frame.setResizable(true); // set false for fixed window size
  }
}

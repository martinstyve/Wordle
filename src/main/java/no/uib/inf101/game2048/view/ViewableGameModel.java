package no.uib.inf101.game2048.view;

import no.uib.inf101.game2048.model.GameState;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

/**
 * The ViewableGameModel interface represents the model of the game that is
 * viewable by the user interface. It provides methods to retrieve information
 * about the game state, dimensions of the board, tiles on the board, and the
 * score.
 */
public interface ViewableGameModel {

  /**
   * The dimensions of the board, i.e. number of rows and columns
   *
   * @return an object of type GridDimension
   */
  GridDimension getDimension();

  /**
   * An object that when iterated over returns all positions and corresponding
   * vales
   *
   * @return an iterable object
   */
  Iterable<GridCell<Character>> getTilesOnBoard();

  /**
   * Tells us the state of the game
   *
   * @return the state of the game
   */
  GameState getGameState();

}

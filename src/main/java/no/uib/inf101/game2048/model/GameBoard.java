package no.uib.inf101.game2048.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;

/**
 * The GameBoard class represents the game board for a 2048 game.
 */
public class GameBoard extends Grid<Character> {

  /**
   * Constructs a new GameBoard with the specified number of rows and columns.
   *
   * @param rows the number of rows in the board
   * @param cols the number of columns in the board
   */
  public GameBoard(int rows, int cols) {
    super(rows, cols, '-');
  }

  /**
   * Creates a copy of the GameBoard object.
   * 
   * @return a copy of the GameBoard
   */
  public GameBoard copy() {
    GameBoard boardCopy = new GameBoard(this.rows(), this.cols());
    for (int i = 0; i < this.rows(); i++) {
      for (int j = 0; j < this.cols(); j++) {
        boardCopy.set(new CellPosition(i, j), this.get(new CellPosition(i, j)));
      }
    }
    return boardCopy;
  }

  /**
   * Clears the board by setting all cells to 0.
   *
   * @return a new GameBoard with all cells set to 0
   */
  public GameBoard clearBoard() {
    GameBoard board = new GameBoard(this.rows(), this.cols());
    for (int i = 0; i < this.rows(); i++) {
      for (int j = 0; j < this.cols(); j++) {
        board.set(new CellPosition(i, j), '-');
      }
    }
    return board;
  }

  /**
   * A string representation of the board in a pretty way For test purposes
   *
   * @return a string representation of the board
   */
  public String prettyString() {
    String pretty = "";
    for (int i = 0; i < this.rows(); i++) {
      for (int j = 0; j < this.cols(); j++) {
        pretty += this.get(new CellPosition(i, j));
      }
      pretty += "\n";
    }
    return pretty.strip();
  }
}

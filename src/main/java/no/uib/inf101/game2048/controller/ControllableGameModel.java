package no.uib.inf101.game2048.controller;

import no.uib.inf101.game2048.model.Direction;
import no.uib.inf101.game2048.model.GameState;
import no.uib.inf101.grid.CellPosition;

/**
 * The ControllableGameModel interface represents a controllable game model that
 * allows interaction with the game state and board.
 */
public interface ControllableGameModel {


  /**
   * Checks if the game has been won.
   *
   * @return true if the game has been won, false otherwise
   */
  boolean isGameWon();

  /**
   * Resets the game to its initial state.
   */
  void resetGame();

  /**
   * Sets the game state to the specified state.
   *
   * @param gameSstate the new game state
   */
  void setGameState(GameState gameState);

  /**
   * Gets the current state of the game.
   *
   * @return the state of the game
   */
  GameState getGameState();
}
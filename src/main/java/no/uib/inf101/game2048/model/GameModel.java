package no.uib.inf101.game2048.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import no.uib.inf101.game2048.controller.ControllableGameModel;
import no.uib.inf101.game2048.view.ViewableGameModel;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

public class GameModel implements ViewableGameModel, ControllableGameModel {

  private GameBoard board;
  private GameState gameState;

  private final Random random = new Random();
  private int attempts;

  private String correctWord;
  private List<String> guesses;
  private StringBuilder currentGuess;

  final String BG_GREEN = "\u001b[42m";
  final String BG_YELLOW = "\u001b[43m";
  final String RESET = "\u001b[0m";

  public GameModel(GameBoard board) {
    this.board = board;
    this.gameState = GameState.ACTIVE_GAME;
    this.correctWord = "snake";

    this.attempts = 6;
    this.correctWord = correctWord.toUpperCase();
    this.guesses = new ArrayList<>();
    this.currentGuess = new StringBuilder();
    System.out.println("Wordle!");
  }

  public void handleGuess(String guess) {
    guess = guess.toUpperCase();

    if (guess.length() != correctWord.length()) {
      System.out.println("Invalid guess length. Please try again.");
      return;
    }

    guesses.add(guess);

    if (guess.equals(correctWord)) {
      System.out.println("Congratulations! You've guessed the word correctly.");
      gameState = GameState.GAME_WON;
    } else {
      attempts--;
      System.out.println("Attempts remaining: " + attempts);
      if (attempts <= 0) {
        gameState = GameState.GAME_OVER;
        System.out.println("Game over! The correct word was: " + correctWord);
      }
    }
    currentGuess.setLength(0); // Reset current guess after handling
  }

  public void updateCurrentGuess(char c) {
    if (c == '\b') {
      if (currentGuess.length() > 0) {
        currentGuess.deleteCharAt(currentGuess.length() - 1);
      }
    } else if (c == '\n') {
      if (currentGuess.length() == correctWord.length()) {
        handleGuess(currentGuess.toString());
      }
    } else if (Character.isLetter(c) && currentGuess.length() < correctWord.length()) {
      currentGuess.append(Character.toUpperCase(c));
    }
  }

  public String getCurrentGuess() {
    return currentGuess.toString();
  }

  public List<String> getGuesses() {
    return guesses;
  }

  public String getCorrectWord() {
    return correctWord;
  }

  @Override
  public void resetGame() {
    this.board = board.clearBoard();
    this.gameState = GameState.ACTIVE_GAME;
    this.guesses.clear();
    this.currentGuess.setLength(0);
  }

  @Override
  public GridDimension getDimension() {
    return board;
  }

  @Override
  public Iterable<GridCell<Character>> getTilesOnBoard() {
    return board;
  }

  @Override
  public void setGameState(GameState gameState) {
    this.gameState = gameState;
  }

  @Override
  public GameState getGameState() {
    return gameState;
  }

  @Override
  public String toString() {
    String result = "";
    for (int row = 0; row < board.rows(); row++) {
      for (int col = 0; col < board.cols(); col++) {
        result += board.get(new CellPosition(row, col)) + " ";
      }
      result += "\n";
    }
    return result;
  }

  @Override
  public boolean isGameWon() {
    throw new UnsupportedOperationException("Unimplemented method 'isGameWon'");
  }
}

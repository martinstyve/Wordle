package no.uib.inf101.game2048.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import no.uib.inf101.game2048.controller.ControllableGameModel;
import no.uib.inf101.game2048.model.numberedTile.NumberedTile;
import no.uib.inf101.game2048.model.numberedTile.NumberedTileFactory;
import no.uib.inf101.game2048.model.numberedTile.RandomNumberedTileFactory;
import no.uib.inf101.game2048.view.ViewableGameModel;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

/**
 * The GameModel class represents the model of the 2048 game, handling game
 * logic and state.
 */
public class GameModel implements ViewableGameModel, ControllableGameModel {

  private GameBoard board;
  private GameState gameState;

  private final Random random = new Random();
  private int attempts;

  private String correctWord;
  private List<String> guesses;

  private char[] charArray;

  final String BG_GREEN = "\u001b[42m";
  final String BG_YELLOW = "\u001b[43m";
  final String RESET = "\u001b[0m";

  /**
   * Constructs a new GameModel with the GameBoard and random NumberedTileFactory.
   *
   * @param board the game board for the model
   */
  public GameModel(GameBoard board) {
    this.board = board;
    this.gameState = GameState.ACTIVE_GAME;
    // this.numberedTileFactory = new RandomNumberedTileFactory();
    this.correctWord = "snake";

    this.attempts = 6;
    this.correctWord = correctWord.toUpperCase();
    this.guesses = new ArrayList<>();
    System.out.println("Wordle!");
  }

  // public void guessWord() {
  // Scanner sc = new Scanner(System.in);

  // while (attempts > 0) {
  // System.out.print("Enter your guess: ");
  // String guess = sc.nextLine().toUpperCase();

  // if (guess.length() != correctWord.length()) {
  // System.out.println("Invalid guess length. Please try again.");
  // continue;
  // }

  // guesses.add(guess);

  // char[] guessArray = guess.toCharArray();
  // for (int i = 0; i < guessArray.length; i++) {
  // if (guessArray[i] == correctWord.charAt(i)) {
  // System.out.print(BG_GREEN + guessArray[i] + RESET);
  // } else if (correctWord.contains(String.valueOf(guessArray[i]))) {
  // System.out.print(BG_YELLOW + guessArray[i] + RESET);
  // } else {
  // System.out.print(guessArray[i]);
  // }
  // }
  // System.out.println();

  // if (guess.equals(correctWord)) {
  // System.out.println("Congratulations! You've guessed the word correctly.");
  // return;
  // }

  // attempts--;
  // System.out.println("Attempts remaining: " + attempts);
  // }

  // System.out.println("Game over! The correct word was: " + correctWord);
  // }

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
  }

  public List<String> getGuesses() { // Add this method
    return guesses;
  }

  public String getCorrectWord() { // Add this method
    return correctWord;
  }

  @Override
  public void resetGame() {
    this.board = board.clearBoard();
    this.gameState = GameState.ACTIVE_GAME;
  }

  @Override
  public GridDimension getDimension() {
    return board;
  }

  @Override
  public Iterable<GridCell<Character>> getTilesOnBoard() {
    return null;
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
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'isGameWon'");
  }
}
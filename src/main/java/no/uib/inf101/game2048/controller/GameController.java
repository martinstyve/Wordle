package no.uib.inf101.game2048.controller;

import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import no.uib.inf101.game2048.midi.GameSong;
import no.uib.inf101.game2048.model.GameState;
import no.uib.inf101.game2048.view.GameView;

/**
 * The GameController class handles user input and updates the game state
 * accordingly.
 */
public class GameController implements KeyListener {

  private ControllableGameModel model;
  private GameView view;
  private GameState gameState;
  private GameSong music;
  private boolean musicPlaying;

  /**
   * Constructs a new GameController with the specified ControllableGameModel and
   * GameView.
   *
   * @param controllableModel the ControllableGameModel to control
   * @param view              the GameView to display the game
   */
  public GameController(ControllableGameModel controllableModel, GameView view) {
    this.model = controllableModel;
    this.view = view;
    this.gameState = model.getGameState();

    this.music = new GameSong();
    music.run();
    musicPlaying = false;
    // this.view.setMusicPlaying(musicPlaying);

    view.addKeyListener(this);
  }

  @Override
  public void keyPressed(KeyEvent e) {
    // Update gameState before handling the key event
    gameState = model.getGameState();

    if (gameState == GameState.GAME_OVER || gameState == GameState.GAME_WON) {
      if (e.getKeyCode() == KeyEvent.VK_R) {
        model.resetGame();
      }
    } else if (gameState == GameState.ACTIVE_GAME) {
      handleGameInput(e);
    } else if (gameState == GameState.MENU) {
      handleMenuInput(e);
    }

    if (e.getKeyCode() == KeyEvent.VK_T) { // t for 'Titanium' by David Guetta
      toggleMusicPlayback();
    }
    view.repaint();
  }

  /**
   * Handles user input during an active game.
   *
   * @param e the KeyEvent representing the user input
   */
  private void handleGameInput(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
    } else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
    } else if (e.getKeyCode() == KeyEvent.VK_M) {
      model.setGameState(GameState.MENU);
    }
  }

  /**
   * Handles user input during the menu screen.
   *
   * @param e the KeyEvent representing the user input
   */
  private void handleMenuInput(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_M) {
      model.setGameState(GameState.ACTIVE_GAME);
    } else if (e.getKeyCode() == KeyEvent.VK_R) {
      model.resetGame();
    } else if (e.getKeyCode() == KeyEvent.VK_C) {
      // try/catch code written by ChatGPT
      try {
        Desktop.getDesktop().browse(new URI("https://www.instagram.com/martinstyve/"));
      } catch (IOException | URISyntaxException ex) {
        ex.printStackTrace();
      }
    }
  }

  /**
   * pauses/unpauses music, and toggles state of musicPlaying.
   */
  private void toggleMusicPlayback() {
    if (musicPlaying) {
      music.doPauseMidiSounds();
    } else {
      music.doUnpauseMidiSounds();
    }
    musicPlaying = !musicPlaying; // Toggle music state
    // view.setMusicPlaying(musicPlaying);
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // do nothing
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // do nothing
  }
}
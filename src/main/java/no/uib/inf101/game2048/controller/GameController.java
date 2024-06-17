package no.uib.inf101.game2048.controller;

import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import no.uib.inf101.game2048.midi.GameSong;
import no.uib.inf101.game2048.model.GameModel;
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

  public GameController(ControllableGameModel controllableModel, GameView view) {
    this.model = controllableModel;
    this.view = view;
    this.gameState = model.getGameState();

    this.music = new GameSong();
    // music.run();
    musicPlaying = false;
    view.addKeyListener(this);
  }

  @Override
  public void keyPressed(KeyEvent e) {
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

    if (e.getKeyCode() == KeyEvent.VK_T) {
      toggleMusicPlayback();
    }
    view.repaint();
  }

  private void handleGameInput(KeyEvent e) {
    char keyChar = e.getKeyChar();
    if (Character.isLetter(keyChar) || keyChar == '\b' || keyChar == '\n') {
      ((GameModel) model).updateCurrentGuess(keyChar);
      model.toString();
    }
  }

  private void handleMenuInput(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_M) {
      model.setGameState(GameState.ACTIVE_GAME);
    } else if (e.getKeyCode() == KeyEvent.VK_R) {
      model.resetGame();
    } else if (e.getKeyCode() == KeyEvent.VK_C) {
      try {
        Desktop.getDesktop().browse(new URI("https://www.instagram.com/martinstyve/"));
      } catch (IOException | URISyntaxException ex) {
        ex.printStackTrace();
      }
    }
  }

  private void toggleMusicPlayback() {
    if (musicPlaying) {
      music.doPauseMidiSounds();
    } else {
      music.doUnpauseMidiSounds();
    }
    musicPlaying = !musicPlaying;
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

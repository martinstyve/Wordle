package no.uib.inf101.game2048.view;

import javax.swing.JPanel;

import no.uib.inf101.game2048.model.GameModel;
import no.uib.inf101.game2048.model.GameState;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * The GameView class represents the graphical user interface of the game.
 */
public class GameView extends JPanel {

  private static final int OUTERMARGIN_X = 100;
  private static final int OUTERMARGIN_Y = 200;
  private static final int CELLMARGIN = 10;
  private static final int PREFERREDSIDESIZE = 100;
  private static final int GUESSEDWORD_FONTSIZE = 30;

  private static final int NUMBEREDTILE_FONTSIZE = 44;
  private static final int LOGOTILE_FONTSIZE = 30;
  private static final int SCORETILE_FONTSIZE = 29;
  private static final int MENUTILE_FONTSIZE = 30;

  private static final int MENUPAGE_FONTSIZE = 19;
  private static final int GAMEENDED_FONTSIZE = 25;

  private final ViewableGameModel viewableGameModel;
  private boolean musicPlaying;
  private final ColorTheme colorTheme;

  /**
   * Constructs a GameView with the specified ViewableGameModel.
   *
   * @param viewableGameModel the model to display in the view
   */
  public GameView(ViewableGameModel viewableGameModel) {
    this.viewableGameModel = viewableGameModel;

    this.colorTheme = new DefaultColorTheme();
    this.setBackground(colorTheme.getBackgroundColor());

    this.setFocusable(true);
    this.setPreferredSize(getDefaultSize(viewableGameModel.getDimension()));
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    drawGame(g2);
    drawGuesses(g2);
    // drawTilesAboveGrid(g2); // not part of functional game
  }

  // private void drawGame(Graphics2D g2) {
  // int gridWidth = viewableGameModel.getDimension().cols() * (PREFERREDSIDESIZE
  // + CELLMARGIN) + CELLMARGIN;
  // int gridHeight = viewableGameModel.getDimension().rows() * (PREFERREDSIDESIZE
  // + CELLMARGIN) + CELLMARGIN;

  // int startX = (getWidth() - gridWidth) / 2;
  // int startY = (getHeight() - gridHeight) / 2;

  // Rectangle2D box = new Rectangle2D.Double(startX, startY, gridWidth,
  // gridHeight);
  // g2.setColor(colorTheme.getFrameColor());
  // g2.fill(box);

  // CellPositionToPixelConverter converter = new
  // CellPositionToPixelConverter(box, viewableGameModel.getDimension(),
  // CELLMARGIN);

  // // drawCells(g2, viewableGameModel.getTilesOnBoard(), converter, colorTheme);

  // if (viewableGameModel.getGameState() == GameState.MENU) {
  // // drawMenu(g2);
  // } else if (viewableGameModel.getGameState() == GameState.GAME_OVER) {
  // // drawGameOver(g2);
  // } else if (viewableGameModel.getGameState() == GameState.GAME_WON) {
  // // drawGameWon(g2);
  // }
  // }

  private void drawGame(Graphics2D g2) {
    int gridWidth = viewableGameModel.getDimension().cols() * (PREFERREDSIDESIZE + CELLMARGIN) + CELLMARGIN;
    int gridHeight = viewableGameModel.getDimension().rows() * (PREFERREDSIDESIZE + CELLMARGIN) + CELLMARGIN;

    int startX = (getWidth() - gridWidth) / 2;
    int startY = (getHeight() - gridHeight) / 2;

    Rectangle2D box = new Rectangle2D.Double(startX, startY, gridWidth, gridHeight);
    g2.setColor(colorTheme.getFrameColor());
    g2.fill(box);

    CellPositionToPixelConverter converter = new CellPositionToPixelConverter(box, viewableGameModel.getDimension(),
        CELLMARGIN);

    if (viewableGameModel.getGameState() == GameState.MENU) {
      // drawMenu(g2);
    } else if (viewableGameModel.getGameState() == GameState.GAME_OVER) {
      // drawGameOver(g2);
    } else if (viewableGameModel.getGameState() == GameState.GAME_WON) {
      // drawGameWon(g2);
    }
  }

  private void drawGuesses(Graphics2D g2) {
    GameModel gameModel = (GameModel) viewableGameModel;
    List<String> guesses = gameModel.getGuesses();

    int startX = 50;
    int startY = 50;
    int lineHeight = 40;

    g2.setFont(new Font("Monospaced", Font.BOLD, GUESSEDWORD_FONTSIZE));

    for (int i = 0; i < guesses.size(); i++) {
      String guess = guesses.get(i);
      drawCells(g2, guess, startX, startY + i * lineHeight, gameModel.getCorrectWord());
    }
  }

  private void drawCells(Graphics2D g2, String guess, int startX, int startY, String correctWord) {
    int cellSize = PREFERREDSIDESIZE;
    for (int i = 0; i < guess.length(); i++) {
      char ch = guess.charAt(i);
      char correctChar = correctWord.charAt(i);
      Color cellColor = Color.WHITE;
      Color textColor = Color.BLACK;

      if (ch == correctChar) {
        cellColor = Color.GREEN;
      } else if (correctWord.indexOf(ch) != -1) {
        cellColor = Color.YELLOW;
      }

      Rectangle2D cell = new Rectangle2D.Double(startX + i * (cellSize + CELLMARGIN), startY, cellSize, cellSize);
      g2.setColor(cellColor);
      g2.fill(cell);

      g2.setColor(textColor);
      g2.draw(cell);
      g2.drawString(String.valueOf(ch), (int) (cell.getX() + cellSize / 2 - g2.getFontMetrics().charWidth(ch) / 2),
          (int) (cell.getY() + cellSize / 2 + g2.getFontMetrics().getAscent() / 2));
    }
  }

  // private void drawMenu(Graphics2D g2) {
  // drawSemitranparentScreen(g2);

  // int gridWidth = viewableGameModel.getDimension().cols() * (PREFERREDSIDESIZE
  // + CELLMARGIN) + CELLMARGIN;
  // int gridHeight = viewableGameModel.getDimension().rows() * (PREFERREDSIDESIZE
  // + CELLMARGIN) + CELLMARGIN;
  // int startX = (getWidth() - gridWidth) / 2 + PREFERREDSIDESIZE + CELLMARGIN *
  // 2;
  // int startY = (getHeight() - gridHeight) / 2 - PREFERREDSIDESIZE / 2;

  // int tileWidth = PREFERREDSIDESIZE * 2 + CELLMARGIN;
  // int tileMarginY = CELLMARGIN * 3;

  // int tile1Y = startY + PREFERREDSIDESIZE;
  // int tile2Y = tile1Y + PREFERREDSIDESIZE + tileMarginY;
  // int tile3Y = tile2Y + PREFERREDSIDESIZE + tileMarginY;

  // // "Keep going" tile
  // Rectangle2D keepGoingTile = new Rectangle2D.Double(startX, tile1Y, tileWidth,
  // PREFERREDSIDESIZE);
  // g2.setColor(colorTheme.getCellColor(32));
  // g2.fill(keepGoingTile);
  // g2.setColor(Color.WHITE);
  // g2.setFont(new Font("Monospaced", Font.BOLD, MENUPAGE_FONTSIZE));
  // Inf101Graphics.drawCenteredString(g2, "Keep Going (m)", keepGoingTile);

  // // "New game" tile
  // Rectangle2D newGameTile = new Rectangle2D.Double(startX, tile2Y, tileWidth,
  // PREFERREDSIDESIZE);
  // g2.setColor(colorTheme.getCellColor(32));
  // g2.fill(newGameTile);
  // g2.setColor(Color.WHITE);
  // g2.setFont(new Font("Monospaced", Font.BOLD, MENUPAGE_FONTSIZE));
  // Inf101Graphics.drawCenteredString(g2, "New Game (r)", newGameTile);

  // // "Creator:.." tile
  // Rectangle2D creatorTile = new Rectangle2D.Double(startX, tile3Y, tileWidth,
  // PREFERREDSIDESIZE);
  // g2.setColor(colorTheme.getCellColor(32));
  // g2.fill(creatorTile);
  // g2.setColor(Color.WHITE);
  // g2.setFont(new Font("Monospaced", Font.BOLD, MENUPAGE_FONTSIZE));
  // Inf101Graphics.drawCenteredString(g2, "Support Creator (c)", creatorTile);
  // }

  // private void drawGameOver(Graphics2D g2) {
  // drawSemitranparentScreen(g2);

  // g2.setColor(Color.WHITE);
  // g2.setFont(new Font("Monospaced", Font.BOLD, GAMEENDED_FONTSIZE));
  // Inf101Graphics.drawCenteredString(g2, "Game Over. Again? (r)", getBounds());
  // }

  // private void drawGameWon(Graphics2D g2) {
  // drawSemitranparentScreen(g2);

  // g2.setColor(Color.WHITE);
  // g2.setFont(new Font("Monospaced", Font.BOLD, GAMEENDED_FONTSIZE));
  // Inf101Graphics.drawCenteredString(g2, "CONGRATULATIONS. Again? (r)",
  // getBounds());
  // }

  // private void drawSemitranparentScreen(Graphics2D g2) {
  // int gridWidth = viewableGameModel.getDimension().cols() * (PREFERREDSIDESIZE
  // + CELLMARGIN) + CELLMARGIN;
  // int gridHeight = viewableGameModel.getDimension().rows() * (PREFERREDSIDESIZE
  // + CELLMARGIN) + CELLMARGIN;
  // int startX = (getWidth() - gridWidth) / 2;
  // int startY = (getHeight() - gridHeight) / 2;

  // g2.setColor(colorTheme.getSemitransparentColor());
  // g2.fillRect(startX, startY, gridWidth, gridHeight);
  // }

  // private static void drawCells(Graphics2D g2, Iterable<GridCell<Character>>
  // iterable,
  // CellPositionToPixelConverter converter, ColorTheme colorTheme) {
  // for (GridCell<Character> cell : iterable) {
  // CellPosition pos = cell.pos();
  // int value = cell.value();
  // Rectangle2D tile = converter.getBoundsForCell(pos);

  // g2.setColor(colorTheme.getCellColor(value));
  // g2.fill(tile);

  // g2.setFont(new Font("Monospaced", Font.BOLD, NUMBEREDTILE_FONTSIZE)); // 4
  // digit number fits perfectly on tile

  // if (value >= 8) {
  // g2.setColor(colorTheme.getHighTileNumberColor());
  // } else {
  // g2.setColor(colorTheme.getLowTileNumberColor());
  // }

  // if (value > 0) { // text not drawn on empty cells
  // Inf101Graphics.drawCenteredString(g2, String.valueOf(value), tile);
  // }
  // }
  // }

  // private void draw2048Tile(Graphics2D g2, int x, int y) {
  // Rectangle2D tile = new Rectangle2D.Double(x, y, PREFERREDSIDESIZE,
  // PREFERREDSIDESIZE);
  // g2.setColor(colorTheme.getCellColor(2048));
  // g2.fill(tile);
  // g2.setColor(Color.WHITE);
  // g2.setFont(new Font("Monospaced", Font.BOLD, LOGOTILE_FONTSIZE));
  // Inf101Graphics.drawCenteredString(g2, "2048", tile);
  // }

  // private void drawMuteTile(Graphics2D g2, int x, int y) {
  // BufferedImage musicPlayingImage =
  // Inf101Graphics.loadImageFromResources("/images/musicPlaying.png");
  // BufferedImage musicMutedImage =
  // Inf101Graphics.loadImageFromResources("/images/musicMuted.png");

  // Rectangle2D tile = new Rectangle2D.Double(x, y, PREFERREDSIDESIZE,
  // PREFERREDSIDESIZE);
  // Rectangle2D muteTileRightCorner = new Rectangle2D.Double(x +
  // PREFERREDSIDESIZE / 3, y + PREFERREDSIDESIZE / 2.5,
  // PREFERREDSIDESIZE, PREFERREDSIDESIZE);

  // g2.setColor(colorTheme.getCellColor(0));
  // g2.fill(tile);

  // if (musicPlaying) {
  // Inf101Graphics.drawCenteredImage(g2, musicPlayingImage, x + PREFERREDSIDESIZE
  // / 2, y + PREFERREDSIDESIZE / 1.75,
  // 0.12);
  // } else {
  // Inf101Graphics.drawCenteredImage(g2, musicMutedImage, x + PREFERREDSIDESIZE /
  // 2, y + PREFERREDSIDESIZE / 1.75,
  // 0.12);
  // }

  // g2.setColor(Color.WHITE);
  // g2.setFont(new Font("Monospaced", Font.BOLD, 15));
  // Inf101Graphics.drawCenteredString(g2, "(t)", muteTileRightCorner);
  // }

  // private void drawScoreTile(Graphics2D g2, int x, int y) {
  // Rectangle2D tile = new Rectangle2D.Double(x, y, PREFERREDSIDESIZE,
  // PREFERREDSIDESIZE);
  // Rectangle2D scoreTileUpperHalf = new Rectangle2D.Double(x, y,
  // PREFERREDSIDESIZE, PREFERREDSIDESIZE / 2);

  // g2.setColor(colorTheme.getCellColor(8));
  // g2.fill(tile);
  // g2.setColor(Color.WHITE);
  // g2.setFont(new Font("Monospaced", Font.BOLD, SCORETILE_FONTSIZE));
  // Inf101Graphics.drawCenteredString(g2,
  // String.valueOf(viewableGameModel.getScore()), tile);
  // g2.setColor(colorTheme.getCellColor(2));
  // g2.setFont(new Font("Monospaced", Font.BOLD, SCORETILE_FONTSIZE / 2));
  // Inf101Graphics.drawCenteredString(g2, "SCORE", scoreTileUpperHalf);
  // }

  // private void drawMenuTile(Graphics2D g2, int x, int y) {
  // Rectangle2D tile = new Rectangle2D.Double(x, y, PREFERREDSIDESIZE,
  // PREFERREDSIDESIZE);
  // Rectangle2D menuTileRightCorner = new Rectangle2D.Double(x +
  // PREFERREDSIDESIZE / 3, y + PREFERREDSIDESIZE / 2.5,
  // PREFERREDSIDESIZE, PREFERREDSIDESIZE);

  // g2.setColor(colorTheme.getCellColor(0));
  // g2.fill(tile);
  // g2.setColor(Color.WHITE);
  // g2.setFont(new Font("Monospaced", Font.BOLD, MENUTILE_FONTSIZE));
  // Inf101Graphics.drawCenteredString(g2, "MENU", tile);

  // g2.setColor(Color.WHITE);
  // g2.setFont(new Font("Monospaced", Font.BOLD, MENUTILE_FONTSIZE / 2));
  // Inf101Graphics.drawCenteredString(g2, "(m)", menuTileRightCorner);
  // }

  // private void drawTilesAboveGrid(Graphics2D g2) {
  // int gridWidth = viewableGameModel.getDimension().cols() * (PREFERREDSIDESIZE
  // + CELLMARGIN) + CELLMARGIN;
  // int gridHeight = viewableGameModel.getDimension().rows() * (PREFERREDSIDESIZE
  // + CELLMARGIN) + CELLMARGIN;
  // int startX = (getWidth() - gridWidth) / 2;
  // int startY = (getHeight() - gridHeight) / 2 + PREFERREDSIDESIZE / 2;

  // int tile1X = startX;
  // int tile2X = startX + PREFERREDSIDESIZE + CELLMARGIN * 2;
  // int tile3X = startX + (gridWidth - 2 * PREFERREDSIDESIZE - CELLMARGIN);
  // int tile4X = startX + gridWidth - PREFERREDSIDESIZE;

  // int tilesY = startY - PREFERREDSIDESIZE - CELLMARGIN * 8;

  // draw2048Tile(g2, tile1X, tilesY);
  // drawMuteTile(g2, tile2X, tilesY);
  // drawScoreTile(g2, tile3X, tilesY);
  // drawMenuTile(g2, tile4X, tilesY);
  // }

  // public void setMusicPlaying(boolean musicPlaying) {
  // this.musicPlaying = musicPlaying;
  // }

  private static Dimension getDefaultSize(GridDimension gd) {
    int width = (int) (PREFERREDSIDESIZE * gd.cols() + CELLMARGIN * (gd.cols() + 1) + 2 * OUTERMARGIN_X);
    int height = (int) (PREFERREDSIDESIZE * gd.rows() + CELLMARGIN * (gd.cols() + 1) + 2 * OUTERMARGIN_Y);
    return new Dimension(width, height);
  }
}

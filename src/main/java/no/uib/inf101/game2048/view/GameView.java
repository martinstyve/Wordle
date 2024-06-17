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
import java.util.List;

public class GameView extends JPanel {

  private static final int OUTERMARGIN_X = 100;
  private static final int OUTERMARGIN_Y = 200;
  private static final int CELLMARGIN = 10;
  private static final int PREFERREDSIDESIZE = 100;
  private static final int GUESSEDWORD_FONTSIZE = 50;

  private final ViewableGameModel viewableGameModel;
  private boolean musicPlaying;
  private final ColorTheme colorTheme;

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
  }

  private void drawGame(Graphics2D g2) {
    int gridWidth = viewableGameModel.getDimension().cols() * (PREFERREDSIDESIZE + CELLMARGIN) + CELLMARGIN;
    int gridHeight = viewableGameModel.getDimension().rows() * (PREFERREDSIDESIZE + CELLMARGIN) + CELLMARGIN;

    int boardX = OUTERMARGIN_X;
    int boardY = OUTERMARGIN_Y;

    g2.setColor(colorTheme.getFrameColor());
    g2.fillRoundRect(boardX, boardY, gridWidth, gridHeight, CELLMARGIN, CELLMARGIN);

    for (GridCell<Character> cell : viewableGameModel.getTilesOnBoard()) {
      drawCell(g2, cell);
    }
  }

  private void drawCell(Graphics2D g2, GridCell<Character> cell) {
    int row = cell.pos().row();
    int col = cell.pos().col();

    int x = OUTERMARGIN_X + col * (PREFERREDSIDESIZE + CELLMARGIN) + CELLMARGIN;
    int y = OUTERMARGIN_Y + row * (PREFERREDSIDESIZE + CELLMARGIN) + CELLMARGIN;

    Color backgroundColor = colorTheme.getBackgroundColor();
    Color foregroundColor = colorTheme.getCellColor(2);

    g2.setColor(backgroundColor);
    g2.fillRoundRect(x, y, PREFERREDSIDESIZE, PREFERREDSIDESIZE, CELLMARGIN, CELLMARGIN);

    g2.setColor(foregroundColor);
    g2.setFont(new Font("SansSerif", Font.BOLD, GUESSEDWORD_FONTSIZE));

    String cellValue = cell.value() == null ? "" : cell.value().toString();
    Rectangle2D textBounds = g2.getFontMetrics().getStringBounds(cellValue, g2);

    int textX = (int) (x + (PREFERREDSIDESIZE - textBounds.getWidth()) / 2);
    int textY = (int) (y + (PREFERREDSIDESIZE - textBounds.getHeight()) / 2 + g2.getFontMetrics().getAscent());

    g2.drawString(cellValue, textX, textY);
  }

  private void drawGuesses(Graphics2D g2) {
    GameModel model = (GameModel) viewableGameModel;
    List<String> guesses = model.getGuesses();
    String currentGuess = model.getCurrentGuess();
    int y = OUTERMARGIN_Y + 500;

    g2.setColor(Color.BLACK);
    g2.setFont(new Font("SansSerif", Font.BOLD, GUESSEDWORD_FONTSIZE));

    for (String guess : guesses) {
      g2.drawString(guess, OUTERMARGIN_X, y);
      y += 50;
    }

    g2.drawString(currentGuess, OUTERMARGIN_X, y);
  }

  private Dimension getDefaultSize(GridDimension dim) {
    int width = dim.cols() * (PREFERREDSIDESIZE + CELLMARGIN) + 2 * OUTERMARGIN_X;
    int height = dim.rows() * (PREFERREDSIDESIZE + CELLMARGIN) + 2 * OUTERMARGIN_Y;
    return new Dimension(width, height);
  }

  public void setMusicPlaying(boolean musicPlaying) {
    this.musicPlaying = musicPlaying;
  }

  public boolean isMusicPlaying() {
    return musicPlaying;
  }
}

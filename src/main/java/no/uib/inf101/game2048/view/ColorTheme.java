package no.uib.inf101.game2048.view;

import java.awt.Color;

/**
 * The ColorTheme interface defines methods for obtaining colors used in the
 * game view.
 */
public interface ColorTheme {

  /**
   * Retrieves the color for a given character.
   *
   * @param value the value of a given tile for which to retrieve the color
   * @return the color corresponding to the value
   */
  Color getCellColor(int value);

  /**
   * Retrieves the color of the frame.
   *
   * @return the color of the frame
   */
  Color getFrameColor();

  /**
   * Retrieves the background color.
   *
   * @return the background color
   */
  Color getBackgroundColor();

  /**
   * Retrieves the color of tiles valued between 0 and 8
   *
   * @return the color of low tile numbers
   */
  Color getLowTileNumberColor();

  /**
   * Retrieves the color of tiles numbered 8 and up.
   *
   * @return the color of high tile numbers
   */
  Color getHighTileNumberColor();

  /**
   * Retrieves the semitransparent color.
   *
   * @return the semitransparent color
   */
  Color getSemitransparentColor();
}

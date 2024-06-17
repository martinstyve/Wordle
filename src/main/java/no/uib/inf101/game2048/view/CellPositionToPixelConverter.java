package no.uib.inf101.game2048.view;

import java.awt.geom.Rectangle2D;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;

/**
 * The CellPositionToPixelConverter class converts cell positions to pixel
 * coordinates for rendering purposes.
 */
public class CellPositionToPixelConverter {

  private final Rectangle2D box;
  private final GridDimension gd;
  private final double margin;

  /**
   * Constructs a new CellPositionToPixelConverter.
   *
   * @param box    the bounding box for the grid
   * @param gd     the grid dimension (rows and columns)
   * @param margin the margin between cells
   */
  public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin) {
    this.box = box;
    this.gd = gd;
    this.margin = margin;
  }

  /**
   * Gets the bounding rectangle for the specified cell position.
   *
   * @param cellPosition the position of the cell
   * @return the bounding rectangle for the cell
   */
  public Rectangle2D getBoundsForCell(CellPosition cellPosition) {
    double cellW = (box.getWidth() - margin * gd.cols() - margin) / gd.cols();
    double cellH = (box.getHeight() - margin * gd.rows() - margin) / gd.rows();
    double cellX = box.getX() + margin + (cellW + margin) * cellPosition.col();
    double cellY = box.getY() + margin + (cellH + margin) * cellPosition.row();
    return new Rectangle2D.Double(cellX, cellY, cellW, cellH);
  }

}
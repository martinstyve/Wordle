package no.uib.inf101.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The Grid class represents a two-dimensional grid structure, where each cell
 * contains a value of type E. It implements the IGrid interface to provide
 * basic grid operations.
 *
 * @param <E> the type of elements stored in the grid
 */
public class Grid<E> implements IGrid<E> {

  private int rows;
  private int cols;
  private E defaultValue;
  private List<List<E>> cells;

  /**
   * Constructs a new grid with the specified number of rows and columns,
   * initialized with default values.
   *
   * @param rows the number of rows in the grid
   * @param cols the number of columns in the grid
   */
  public Grid(int rows, int cols) {
    this(rows, cols, null);
  }

  /**
   * Constructs a new grid with the specified number of rows and columns,
   * initialized with the specified default value.
   *
   * @param rows         the number of rows in the grid
   * @param cols         the number of columns in the grid
   * @param defaultValue the default value to initialize each cell with
   */
  public Grid(int rows, int cols, E defaultValue) {
    this.rows = rows;
    this.cols = cols;
    this.defaultValue = defaultValue;

    this.cells = new ArrayList<>();
    for (int i = 0; i < rows; i++) {
      List<E> row = new ArrayList<>();
      for (int j = 0; j < cols; j++) {
        row.add(defaultValue);
      }
      cells.add(row);
    }
  }

  @Override
  public int rows() {
    return rows;
  }

  @Override
  public int cols() {
    return cols;
  }

  @Override
  public Iterator<GridCell<E>> iterator() {
    List<GridCell<E>> list = new ArrayList<>();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        CellPosition pos = new CellPosition(i, j);
        E value = get(pos);
        GridCell<E> cell = new GridCell<>(pos, value);
        list.add(cell);
      }
    }
    return list.iterator();
  }

  @Override
  public void set(CellPosition pos, E value) {
    if (!positionIsOnGrid(pos))
      throw new IndexOutOfBoundsException("Given position is not within grid: (" + pos.row() + ", " + pos.col() + ")");
    cells.get(pos.row()).set(pos.col(), value);
  }

  @Override
  public E get(CellPosition pos) {
    if (!positionIsOnGrid(pos))
      throw new IndexOutOfBoundsException("Given position is not within grid: (" + pos.row() + ", " + pos.col() + ")");
    return cells.get(pos.row()).get(pos.col());
  }

  @Override
  public boolean positionIsOnGrid(CellPosition pos) {
    boolean isWithinRowBound = pos.row() >= 0 && pos.row() < rows;
    boolean isWithinColBound = pos.col() >= 0 && pos.col() < cols;
    return isWithinRowBound && isWithinColBound;
  }
}
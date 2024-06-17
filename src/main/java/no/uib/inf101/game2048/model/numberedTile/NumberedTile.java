package no.uib.inf101.game2048.model.numberedTile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;

/**
 * The NumberedTile class represents a tile with a numeric value at a specific
 * position on the game board.
 */
public class NumberedTile implements Iterable<GridCell<Character>> {

  private final Character value;
  private final CellPosition pos;

  /**
   * Constructs a new NumberedTile with the specified value and position.
   *
   * @param value the value of the tile
   * @param pos   the position of the tile on the game board
   */
  public NumberedTile(Character value, CellPosition pos) {
    this.value = value;
    this.pos = pos;
  }

  /**
   * Gets the value of the tile.
   *
   * @return the value of the tile
   */
  public int getValue() {
    return value;
  }

  @Override
  public Iterator<GridCell<Character>> iterator() {
    List<GridCell<Character>> list = new ArrayList<>();
    list.add(new GridCell<>(pos, value));
    return list.iterator();
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof NumberedTile)) {
      return false;
    }
    NumberedTile tile = (NumberedTile) o;
    return value == tile.value && Objects.equals(pos, tile.pos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, pos);
  }

}

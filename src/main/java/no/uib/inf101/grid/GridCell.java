package no.uib.inf101.grid;

/**
 * The GridCell class represents a cell within a grid, containing a position and
 * a value.
 *
 * @param <E> the type of value stored in the cell
 */
public record GridCell<E>(CellPosition pos, E value) {

}

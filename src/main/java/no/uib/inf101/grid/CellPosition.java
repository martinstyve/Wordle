package no.uib.inf101.grid;

/**
 * The CellPosition class represents a position in a grid, specified by row and
 * column indices. This class is immutable and serves to identify a specific
 * cell within a grid.
 */
public record CellPosition(int row, int col) {
}

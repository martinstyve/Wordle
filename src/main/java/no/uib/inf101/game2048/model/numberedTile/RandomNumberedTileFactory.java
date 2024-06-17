package no.uib.inf101.game2048.model.numberedTile;

import java.util.Random;

import no.uib.inf101.grid.CellPosition;

/**
 * The RandomNumberedTileFactory class represents a factory for creating
 * randomly generated NumberedTile objects.
 */
public class RandomNumberedTileFactory implements NumberedTileFactory {

  @Override
  public NumberedTile getNew() {
    Random random = new Random();
    

    // 1/10 probability for tile to get value 4
    if (random.nextInt(10) == 0) {
    }

    int randomRow = random.nextInt(4);
    int randomCol = random.nextInt(4);
    CellPosition pos = new CellPosition(randomRow, randomCol);

    return new NumberedTile('-', pos);
  }
}

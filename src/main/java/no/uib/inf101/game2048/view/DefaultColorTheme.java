package no.uib.inf101.game2048.view;

import java.awt.Color;

public class DefaultColorTheme implements ColorTheme {

  // Color pallette found from original game (link: https://play2048.co/) using
  // Google chrome extension ColorZilla to find rgb values

  @Override
  public Color getCellColor(int c) {
    Color color = switch (c) {
    case 0 -> new Color(204, 193, 180);
    case 2 -> new Color(238, 228, 218);
    case 4 -> new Color(237, 224, 200);
    case 8 -> new Color(243, 178, 121);
    case 16 -> new Color(245, 149, 99);
    case 32 -> new Color(246, 124, 95);
    case 64 -> new Color(246, 94, 59);
    case 128 -> new Color(246, 94, 59);
    case 256 -> new Color(237, 204, 97);
    case 512 -> new Color(237, 200, 80);
    case 1024 -> new Color(249, 207, 63);
    case 2048 -> new Color(237, 194, 46);
    default -> new Color(00, 00, 00); // should never be reached, kept in to prevent game crash
    };
    return color;
  }

  @Override
  public Color getFrameColor() {
    return new Color(188, 172, 159);
  }

  @Override
  public Color getBackgroundColor() {
    return new Color(250, 248, 239);
  }

  @Override
  public Color getLowTileNumberColor() {
    return new Color(119, 110, 101);
  }

  @Override
  public Color getHighTileNumberColor() {
    return new Color(248, 246, 242);
  }

  @Override
  public Color getSemitransparentColor() {
    return new Color(0, 0, 0, 96);
  }
}

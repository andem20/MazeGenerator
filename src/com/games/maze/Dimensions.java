package com.games.maze;

public class Dimensions {
  // Dimensions & scaling
  private final int WINDOW_SIZE = 700;

  private final int SQUARES = 20;

  private int SCALE = 50;

  public int x = 0;
  public int y = 0;

  public int scale(int size) {
    return size * getSCALE();
  }

  public int getSCALE() {
    return SCALE;
  }

  public int getWindowSize() {
    return WINDOW_SIZE;
  }

  public int getSQUARES() {
    return SQUARES;
  }

  public void setSCALE(int SCALE) {
    this.SCALE = SCALE;
  }
}

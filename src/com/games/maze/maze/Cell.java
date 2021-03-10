package com.games.maze.maze;

import java.util.Arrays;

public class Cell {

  private final int row;
  private final int col;

  private boolean visited = false;

  private boolean[] sides = new boolean[4];

  public Cell(int row, int col) {
    this.row = row;
    this.col = col;

    Arrays.fill(sides, true);
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public boolean isVisited() {
    return visited;
  }

  public void setVisited(boolean visited) {
    this.visited = visited;
  }

  public boolean[] getSides() {
    return sides;
  }

  public void setSides(boolean[] sides) {
    this.sides = sides;
  }
}

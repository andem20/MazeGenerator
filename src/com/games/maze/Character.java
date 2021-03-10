package com.games.maze;

import com.games.maze.maze.Maze;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Character {

  //TODO skal være relativ til dimensions

  public int cx = 0;
  public int cy = 0;

  public int row1 = 0;
  public int col1 = 0;

  public int size;
  public int speed = 2;

  public boolean finished = false;

  private final Controls controls;
  private final Dimensions d;
  private final Maze maze;

  public Character(Dimensions dimensions, Controls controls, Maze maze) {
    this.controls = controls;
    this.d = dimensions;
    this.maze = maze;
  }

  public void render(Graphics g) {
    this.size = (int) (0.3 * d.getSCALE());

    g.setColor(new Color(255, 0, 0));
    g.fillRect(cx, cy, size, size);
  }

  public void move() {
    /*
     * Refactoring
     */

    row1 = (cy + d.y + 1) / d.getSCALE();
    col1 = (cx + d.x + 1) / d.getSCALE();

    int row2 = (cy + d.y + size - 1) / d.getSCALE();
    int col2 = (cx + d.x + size - 1) / d.getSCALE();

    // Character position
    int x = cx + d.x;
    int y = cy + d.y;

    // Boundaries
    int bound = (d.getSCALE() * d.getSQUARES()) - d.getWindowSize();
    int center = d.getWindowSize() / 2;
    boolean yBound = y + size > (d.getSCALE() + row1 * d.getSCALE());
    boolean xBound = x + size > (d.getSCALE() + col1 * d.getSCALE());

    // Sides
    boolean[] topLeftSides = maze.getMazeArray().get(row1).get(col1).getSides();
    boolean[] topRightSides = maze.getMazeArray().get(row1).get(col2).getSides();
    boolean[] bottomLeftSides = maze.getMazeArray().get(row2).get(col1).getSides();
    boolean[] bottomRightSides = maze.getMazeArray().get(row2).get(col2).getSides();

    int accel = 0;

    if (controls.isKey(KeyEvent.VK_RIGHT)) {
      if (topLeftSides[1] || bottomLeftSides[1] || yBound) {
        accel = Math.min((d.getSCALE() + col1 * d.getSCALE()) - (x + size), speed);
      } else {
        accel = speed;
      }

      if (cx >= center && d.x < bound) {
        d.x += accel;
      } else {
        cx += accel;
      }
    }

    if (controls.isKey(KeyEvent.VK_LEFT)) {
      if (topRightSides[3] || bottomRightSides[3] || yBound) {
        accel = Math.min(x - (col1 * d.getSCALE()), speed);
      } else {
        accel = speed;
      }

      if (cx <= center && d.x > 0) {
        d.x -= accel;
      } else {
        cx -= accel;
      }
    }

    if (controls.isKey(KeyEvent.VK_DOWN)) {
      if (topLeftSides[2] || topRightSides[2] || xBound) {
        accel = Math.min((d.getSCALE() + row1 * d.getSCALE()) - (y + size), speed);
      } else {
        accel = speed;
      }

      if (cy >= center && d.y < bound) {
        d.y += accel;
      } else {
        cy += accel;
      }
    }

    if (controls.isKey(KeyEvent.VK_UP)) {
      if (bottomRightSides[0] || bottomLeftSides[0] || xBound) {
        accel = Math.min(y - (row1 * d.getSCALE()), speed);
      } else {
        accel = speed;
      }

      if (cy <= center && d.y > 0) {
        d.y -= accel;
      } else {
        cy -= accel;
      }
    }
  }
}

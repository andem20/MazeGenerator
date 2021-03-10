package com.games.maze.views;

import com.games.maze.Character;
import com.games.maze.Dimensions;
import com.games.maze.maze.Cell;
import com.games.maze.maze.Maze;

import javax.swing.*;
import java.awt.*;

public class MazeView extends JPanel {

  private final Dimensions d;
  private final Maze maze;
  private final Character character;

  public MazeView(Dimensions dimensions, Character character, Maze maze) {
    this.d = dimensions;
    this.character = character;

    setPreferredSize(
        new Dimension(
            dimensions.getWindowSize() + character.size,
            dimensions.getWindowSize() + character.size
        )
    );

    setBackground(new Color(188, 231, 165));

    this.maze = maze;
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);

    int mazeSize = d.getWindowSize() / d.getSCALE();

    // Set finish cell
    g.setColor(new Color(255, 0, 255, 150));
    g.fillRect(
        d.scale(maze.getFinishCell().getCol()) - d.x,
        d.scale(maze.getFinishCell().getRow()) - d.y,
        d.getSCALE(),
        d.getSCALE()
    );

    int yPos = d.y / d.getSCALE();
    int xPos = d.x / d.getSCALE();

    // Draw walls
    for (int y = yPos; y < yPos + mazeSize; y++) {
      for (int x = xPos; x < xPos + mazeSize; x++) {
        Cell cell = maze.getMazeArray().get(y).get(x);

        drawCellWalls(g, cell);
      }
    }

    character.render(g);

    if(character.finished) finish(g);
  }

  public void drawCellWalls(Graphics g, Cell cell) {
    g.setColor(Color.BLACK);

    // Top
    if (cell.getSides()[0]) { drawWall(0, 0, 1, 0, g, cell); }

    // Right
    if (cell.getSides()[1]) { drawWall(1, 0, 1, 1, g, cell); }

    // Bottom
    if (cell.getSides()[2]) { drawWall(0, 1, 1, 1, g, cell); }

    // Left
    if (cell.getSides()[3]) { drawWall(0, 0, 0, 1, g, cell); }
  }

  public void drawWall(int x1, int y1, int x2, int y2, Graphics g, Cell cell) {
    g.drawLine(
        d.scale(cell.getCol() + x1) - d.x,  d.scale(cell.getRow() + y1) - d.y,
        d.scale(cell.getCol() + x2) - d.x, d.scale(cell.getRow() + y2) - d.y
    );
  }

  public void finish(Graphics g) {
    g.setColor(Color.BLACK);
    g.fillRect(d.getWindowSize() / 2 - 130, d.getWindowSize() / 2 - 60, 300, 100);
    g.setColor(Color.GREEN);
    g.setFont(new Font("Arial", Font.BOLD, 40));
    g.drawString("YOU WON!!", d.getWindowSize() / 2 - 100, d.getWindowSize() / 2);
  }
}

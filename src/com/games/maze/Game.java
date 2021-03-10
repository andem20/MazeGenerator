package com.games.maze;

import com.games.maze.maze.Maze;
import com.games.maze.views.MazeView;

import java.awt.event.KeyEvent;

public class Game implements Runnable {

  // Dimensions & scaling
  private final Dimensions dimensions;

  // Views
  private final View view;
  private final MazeView mazeView;

  // Controls
  private final Controls controls;

  //Character
  private final Character character;

  // Maze
  private final Maze maze;

  // Gamesettings
  private final double FPS = 60;
  private final double UPDATE_CAP = 1.0 / FPS;
  private boolean running = false;

  public Game() {
    // Setup size and scale
    dimensions = new Dimensions();

    // Setting up the view
    view = new View("Maze Game");

    controls = new Controls();

    maze = new Maze(dimensions);

    character = new Character(dimensions, controls, maze);

    mazeView = new MazeView(dimensions, character, maze);

    view.addKeyListener(controls);

    view.add(mazeView);
    view.pack();
  }

  @Override
  public void run() {
    running = true;

    boolean render;

    double lastTime = System.nanoTime() / 1E9;
    double presentTime;
    double delta;

    double frameTime = 0;

    int frames = 0;

    double unprocessedTime = 0;

    while (running) {
      render = false;

      presentTime = System.nanoTime() / 1E9;
      delta = presentTime - lastTime;

      unprocessedTime += delta;
      frameTime += delta;

      if (unprocessedTime >= UPDATE_CAP) {
        render = true;

        unprocessedTime -= UPDATE_CAP;

        if (frameTime >= 1.0) {
          frameTime = 0;
          frames = 0;
        }
      }

      if (render) {
        frames++;

        character.move();

        // TODO fix character position
        if(controls.isKey(KeyEvent.VK_Z)){
          dimensions.setSCALE(Math.max(dimensions.getSCALE() - 1, dimensions.getWindowSize() / dimensions.getSQUARES()+1));
        }

        if(controls.isKey(KeyEvent.VK_X)){
          dimensions.setSCALE(dimensions.getSCALE() + 1);
        }

        if (character.col1 == maze.getFinishCell().getCol() && character.row1 == maze.getFinishCell().getRow()) {
          character.finished = true;
        }

        mazeView.repaint();
      }

      lastTime = presentTime;
    }
  }
}

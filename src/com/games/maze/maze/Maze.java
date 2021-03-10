package com.games.maze.maze;

import com.games.maze.Dimensions;
import java.util.ArrayList;

public class Maze {

  private final ArrayList<ArrayList<Cell>> mazeArray = new ArrayList<>();

  private final Cell finishCell;

  public Maze(Dimensions dimensions) {

    /**
     * Refactoring
     */

    for(int i = 0; i < dimensions.getSQUARES(); i++) {
      ArrayList<Cell> tempMazeArray = new ArrayList<>();

      for(int j = 0; j < dimensions.getSQUARES(); j++) {
        tempMazeArray.add(new Cell(i, j));
      }

      mazeArray.add(tempMazeArray);
    }

    finishCell = mazeArray.get((int)(Math.random() * mazeArray.size())).get((int)(Math.random() * mazeArray.size()));

    int totalVisited = 0;
    Cell current = mazeArray.get(0).get(0);

    ArrayList<Cell> path = new ArrayList<>();

    while(totalVisited < Math.pow(dimensions.getSQUARES(), 2)) {

      if(!current.isVisited()) {
        current.setVisited(true);
        path.add(current);
        totalVisited++;
      }

      ArrayList<Cell> neighbours = new ArrayList<>();
      ArrayList<Integer> places = new ArrayList<>();

      if(current.getRow() > 0) {
        Cell top = mazeArray.get(current.getRow() - 1).get(current.getCol());

        if(!top.isVisited()) {
          neighbours.add(top);
          places.add(0);
        }
      }

      if(current.getCol() < mazeArray.size() - 1) {
        Cell right = mazeArray.get(current.getRow()).get(current.getCol() + 1);

        if(!right.isVisited()) {
          neighbours.add(right);
          places.add(1);
        }
      }

      if(current.getRow() < mazeArray.size() - 1) {
        Cell bottom = mazeArray.get(current.getRow() + 1).get(current.getCol());

        if(!bottom.isVisited()) {
          neighbours.add(bottom);
          places.add(2);
        }
      }

      if(current.getCol() > 0) {
        Cell left = mazeArray.get(current.getRow()).get(current.getCol() - 1);

        if(!left.isVisited()) {
          neighbours.add(left);
          places.add(3);
        }
      }

      if(neighbours.size() < 1) {
        path.remove(path.size() - 1);
        current = path.get(path.size() - 1);
        continue;
      }

      int random = (int) (Math.random() * neighbours.size());

      // Remove sides
      boolean[] sides = current.getSides().clone();
      sides[places.get(random)] = false;
      current.setSides(sides);

      boolean[] neighbourSides = neighbours.get(random).getSides().clone();
      int nPlace = places.get(random) < 2 ? places.get(random) + 2 : places.get(random) - 2;
      neighbourSides[nPlace] = false;
      neighbours.get(random).setSides(neighbourSides);

      current = neighbours.get(random);
    }
  }

  public ArrayList<ArrayList<Cell>> getMazeArray() {
    return mazeArray;
  }

  public Cell getFinishCell() {
    return finishCell;
  }
}

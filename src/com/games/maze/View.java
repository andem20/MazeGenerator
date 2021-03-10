package com.games.maze;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

  public View(String title) throws HeadlessException {
    setTitle(title);

    setDefaultCloseOperation(EXIT_ON_CLOSE);

    setResizable(true);

    pack();
    setVisible(true);
  }

  public JFrame getFrame() {
    return this;
  }
}

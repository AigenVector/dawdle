package com.aigenvector.dawdle;

import javax.swing.JLabel;
import javax.swing.JFrame;

public class WindowManager {
  //TODO Stubbed
  private static WindowManager _instance = null;

  private WindowManager() {
    JFrame frame = new JFrame("Dawdle");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Add the ubiquitous "Hello World" label.
    JLabel label = new JLabel("Hello World");
    frame.getContentPane().add(label);

    //Display the window.
    frame.pack();
    frame.setVisible(true);
  }

  public static void initialize() {
    WindowManager.getInstance();
  }

  public static WindowManager getInstance() {
    if(_instance == null) {
      _instance = new WindowManager();
    }
    return _instance;
  }
}

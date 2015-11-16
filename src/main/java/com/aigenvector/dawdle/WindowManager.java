package com.aigenvector.dawdle;

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;
import javax.swing.KeyStroke;

import com.aigenvector.dawdle.actionlistener.AddFlashListener;
import com.aigenvector.dawdle.actionlistener.ExportListener;
import com.aigenvector.dawdle.actionlistener.OpenImageListener;
import com.aigenvector.dawdle.actionlistener.ResetListener;
import com.aigenvector.dawdle.actionlistener.StartListener;
import com.aigenvector.dawdle.actionlistener.StopListener;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class WindowManager {
  //TODO Stubbed
  private static WindowManager _instance = null;
  private Canvas _canvas = null;

  private WindowManager() {
    JFrame frame = new JFrame("Dawdle");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setMinimumSize(new Dimension(800, 600));

    _canvas = new Canvas();
    frame.getContentPane().add(_canvas, BorderLayout.CENTER);

    JMenuBar menuBar;
    JMenu menu, submenu;
    JMenuItem menuItem;
    JRadioButtonMenuItem rbMenuItem;
    JCheckBoxMenuItem cbMenuItem;

    //Create the menu bar.
    menuBar = new JMenuBar();

    //Build the file menu.
    menu = new JMenu("File");
    menu.setMnemonic(KeyEvent.VK_F);
    menu.getAccessibleContext().setAccessibleDescription(
            "Manage input and output of the Dawdle.");
    menuBar.add(menu);

    //a group of JMenuItems
    menuItem = new JMenuItem("Open Background Image",
                             KeyEvent.VK_B);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(
            KeyEvent.VK_B, ActionEvent.ALT_MASK));
    menuItem.getAccessibleContext().setAccessibleDescription(
            "Chooses background image.");
    menuItem.addActionListener(new OpenImageListener());
    menu.add(menuItem);

    menuItem = new JMenuItem("Manage Flash Coordinate(s)",
                             KeyEvent.VK_A);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(
            KeyEvent.VK_A, ActionEvent.ALT_MASK));
    menuItem.getAccessibleContext().setAccessibleDescription(
            "Adds coordinate for flash during experiment.");
    menuItem.addActionListener(new AddFlashListener(frame));
    menu.add(menuItem);
    
    menuItem = new JMenuItem("Start Experiment");
    menuItem.setAccelerator(KeyStroke.getKeyStroke(
    		KeyEvent.VK_SPACE, ActionEvent.ALT_MASK));
    menuItem.getAccessibleContext().setAccessibleDescription(
    		"Start that shit.");
    menuItem.addActionListener(new StartListener(_canvas));
    menu.add(menuItem);
    
    menuItem = new JMenuItem("Stop Experiment");
    menuItem.setAccelerator(KeyStroke.getKeyStroke(
    		KeyEvent.VK_SPACE, ActionEvent.CTRL_MASK));
    menuItem.getAccessibleContext().setAccessibleDescription(
    		"Stahppppit");
    menuItem.addActionListener(new StopListener());
    menu.add(menuItem);

    menuItem = new JMenuItem("Export to Excel",
                             KeyEvent.VK_X);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(
            KeyEvent.VK_X, ActionEvent.ALT_MASK));
    menuItem.getAccessibleContext().setAccessibleDescription(
            "Exports recorded results to an Excel file of your choice.");
    menuItem.addActionListener(new ExportListener());
    menu.add(menuItem);

    menuItem = new JMenuItem("Reset Recording", KeyEvent.VK_R);
    menuItem.setMnemonic(KeyEvent.VK_R);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(
            KeyEvent.VK_R, ActionEvent.ALT_MASK));
    menuItem.addActionListener(new ResetListener());
    menu.add(menuItem);

    frame.setJMenuBar(menuBar);

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

  public Canvas getCanvas() {
    return _canvas;
  }
}

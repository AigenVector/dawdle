package com.aigenvector.dawdle.actionlistener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.aigenvector.dawdle.dialog.CoordinateDialog;

public class AddFlashListener implements ActionListener {
  private JFrame _root = null;

  public AddFlashListener(JFrame root) {
    _root = root;
  }

  public void actionPerformed(ActionEvent e) {
    new CoordinateDialog(_root);
  }
}

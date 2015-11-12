package com.aigenvector.dawdle;

import java.io.File;
import java.io.IOException;

import javax.swing.JComponent;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public class Canvas extends JComponent {

  private final String _logPrefix = "com.aigenvector.dawdle.Canvas: ";
  private Image _backgroundImage = null;

  public Canvas() {
    log("background-image: "+PropertyManager.getInstance().getValue("background-image"));
    Image img = Toolkit.getDefaultToolkit().getImage(PropertyManager.getInstance().getValue("background-image"));
    log(Boolean.toString((img == null)));
  }

  public void paint(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    if(_backgroundImage != null) {
      g2.drawImage(_backgroundImage, 0, 0, this); 
    }
  }

  public void setImageFile(String rhs) {
    _backgroundImage = Toolkit.getDefaultToolkit().getImage(rhs)
        .getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
    this.repaint();
  }

  private void log(String rhs) {
    System.out.println(_logPrefix + rhs);
  }

}

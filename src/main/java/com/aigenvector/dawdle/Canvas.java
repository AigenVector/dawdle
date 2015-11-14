package com.aigenvector.dawdle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.Timer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

public class Canvas extends JComponent {

  private final String _logPrefix = "com.aigenvector.dawdle.Canvas: ";
  private Image _backgroundImage = null;
  private ArrayList<Ellipse2D> flashes = new ArrayList<Ellipse2D>(); 
  private Timer cleanUpTimer = null;

  public Canvas() {
    log("background-image: "+PropertyManager.getInstance().getValue("background-image"));
    Image img = Toolkit.getDefaultToolkit().getImage(PropertyManager.getInstance().getValue("background-image"));
    log(Boolean.toString((img == null)));
  }

  public void paint(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g.clearRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    if(_backgroundImage != null) {
      g2.drawImage(_backgroundImage, 0, 0, this); 
    }
    if(flashes.size() > 0) {
    	for(Ellipse2D a : flashes) {
    		g2.setColor(java.awt.Color.RED);
    		g2.fill(a);
    	}
    	System.out.println("Clearing the flashes.");
    	cleanUpTimer = new Timer(20, new CleanupFlash(this));
    	cleanUpTimer.start();
    	cleanUpTimer.setRepeats(false);
    	flashes.clear();
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
  
  public void randomFlash() {
	  for(int[] a : FlashCoordinateManager.getInstance().getPoints()) {
		  flashes.add(new Ellipse2D.Double(a[0], a[1],
                  20,
                  20));
	  }
	  this.repaint();
  }
  
  private class CleanupFlash implements ActionListener {
	  public JComponent refresher;
	  
	  public CleanupFlash(JComponent r) {
		  refresher = r;
	  }
	  
	  public void actionPerformed(ActionEvent e) {
		  System.out.println("Cleanup timer called.");
		  refresher.repaint();  
	  }
  }

}

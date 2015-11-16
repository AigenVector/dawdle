package com.aigenvector.dawdle.actionlistener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.aigenvector.dawdle.Canvas;
import com.aigenvector.dawdle.ExperimentManager;
import com.aigenvector.dawdle.WindowManager;

public class ResetListener implements ActionListener {
  	
  public ResetListener() {
	  // Stubbed
  }

  public void actionPerformed(ActionEvent e) {
    ExperimentManager.getInstance().resetExperiment();
  }
}

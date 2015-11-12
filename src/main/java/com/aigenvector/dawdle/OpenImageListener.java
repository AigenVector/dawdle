package com.aigenvector.dawdle;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OpenImageListener implements ActionListener {
  public OpenImageListener() {
    //Stubbed
  }

  public void actionPerformed(ActionEvent e) {
    JFileChooser fc = new JFileChooser();
    fc.setFileFilter(new FileNameExtensionFilter("Image file","jpg","png","gif"));
    int result = fc.showOpenDialog(null);
    File file = null;
    if (result == JFileChooser.APPROVE_OPTION) {
      file = fc.getSelectedFile();
      WindowManager.getInstance().getCanvas().setImageFile(file.getPath());
    }
  }
}

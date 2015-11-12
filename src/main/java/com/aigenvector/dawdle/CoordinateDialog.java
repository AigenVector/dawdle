package com.aigenvector.dawdle;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class CoordinateDialog extends JDialog {

  private static final long serialVersionUID = 1L;
  private final String _message = "";
  private JTextField _x = null;
  private JTextField _y = null;

  public CoordinateDialog(JFrame parent) {
    super(parent, PropertyManager.getInstance().getValue("coordinate.title"));
    Point p = new Point(parent.getWidth()/2, parent.getHeight()/2);
    setLocation(p.x, p.y);

    JPanel messagePane = new JPanel();
    messagePane.add(new JLabel(PropertyManager.getInstance().getValue("coordinate.message")));
    getContentPane().add(messagePane, BorderLayout.PAGE_START);

    JPanel inputgroup = new JPanel();
    _x = new JTextField(4);
    inputgroup.add(_x);
    _y = new JTextField(4);
    inputgroup.add(_y);
    getContentPane().add(inputgroup, BorderLayout.CENTER);

    // Create a button
    JPanel buttonPane = new JPanel();
    JButton button = new JButton("Close me");
    buttonPane.add(button);
    // set action listener on the button
    button.addActionListener(new MyActionListener());
    getContentPane().add(buttonPane, BorderLayout.PAGE_END);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    pack();
    setVisible(true);
  }

  public JRootPane createRootPane() {
    JRootPane rootPane = new JRootPane();
    KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
    Action action = new AbstractAction() {
      
      private static final long serialVersionUID = 1L;

      public void actionPerformed(ActionEvent e) {
        setVisible(false);
        dispose();
      }
    };
    InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    inputMap.put(stroke, "ESCAPE");
    rootPane.getActionMap().put("ESCAPE", action);
    return rootPane;
  }

  class MyActionListener implements ActionListener {

    //close and dispose of the window.
    public void actionPerformed(ActionEvent e) {
      System.out.println("disposing the window..");
      setVisible(false);
      dispose();
    }
  }
}

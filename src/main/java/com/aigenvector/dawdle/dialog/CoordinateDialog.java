package com.aigenvector.dawdle.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.aigenvector.dawdle.FlashCoordinateManager;
import com.aigenvector.dawdle.PropertyManager;

public class CoordinateDialog extends JDialog implements ListSelectionListener {

	private static final long serialVersionUID = 1L;
	private final String _logPrefix = "com.aigenvector.dawdle.dialog.CoordinateDialog: ";
	private final String _message = "";
	private JList list;
	private DefaultListModel listModel;
	private JTextField _x = null;
	private JTextField _y = null;

	public CoordinateDialog(JFrame parent) {
		super(parent, "Manage Flash Coordinate(s)");
		this.setMinimumSize(new Dimension(300, 200));
		this.setMaximumSize(new Dimension(300, 200));
		Point p = new Point(parent.getWidth() / 2 - 100, parent.getHeight() / 2 - 300);
		setLocation(p.x, p.y);

		listModel = new DefaultListModel();

		// Create the list and put it in a scroll pane.
		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(this);
		list.setVisibleRowCount(5);
		for(int[] ele : FlashCoordinateManager.getInstance().getPoints()) {
			listModel.addElement(ele[0] + ", " + ele[1]);
		}
		
		JScrollPane listScrollPane = new JScrollPane(list);
		getContentPane().add(listScrollPane, BorderLayout.PAGE_START);
		
		JPanel inputgroup = new JPanel();
		_x = new JTextField(4);
		inputgroup.add(_x);
		_y = new JTextField(4);
		inputgroup.add(_y);
		getContentPane().add(inputgroup, BorderLayout.CENTER);

		// Create a button
		JPanel buttonPane = new JPanel();
		JButton button = new JButton("Add");
		buttonPane.add(button);
		// set action listener on the button
		button.addActionListener(new MyAddListener());

		button = new JButton("Remove");
		buttonPane.add(button);
		button.addActionListener(new MyRemoveListener());

		button = new JButton("Close");
		buttonPane.add(button);
		button.addActionListener(new MyCloseListener());

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

	private class MyAddListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String xCoord = _x.getText();
			String yCoord = _y.getText();
			int x = -1;
			int y = -1;
			try {
				x = Integer.parseInt(xCoord);
				y = Integer.parseInt(yCoord);
			} catch (NumberFormatException nfe) {
				log("Error parsing coordinate.");
			}
			if (x > 0 && y > 0) {
				listModel.addElement(x + ", " + y);
				FlashCoordinateManager.getInstance().addPoint(x, y);
			}
		}
	}

	private class MyCloseListener implements ActionListener {
		// close and dispose of the window.
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			dispose();
		}
	}

	private class MyRemoveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int selNdx = list.getSelectedIndex();
			if(selNdx >= 0) {
				String selected = (String)listModel.getElementAt(selNdx);
				String[] pieces = selected.split(", ");
				int x = Integer.parseInt(pieces[0]);
				int y = Integer.parseInt(pieces[1]);
				listModel.remove(selNdx);
				FlashCoordinateManager.getInstance().removePoint(x, y);
			}
		}
	}

	private void log(String rhs) {
		System.out.println(_logPrefix + rhs);
	}

	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {
			int selNdx = list.getSelectedIndex();
			if(selNdx >= 0) {
				String selected = (String)listModel.getElementAt(selNdx);
				String[] pieces = selected.split(", ");
				int x = Integer.parseInt(pieces[0]);
				int y = Integer.parseInt(pieces[1]);
				_x.setText(Integer.toString(x));
				_y.setText(Integer.toString(y));
			}
		}
	}
}

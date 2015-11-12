package com.aigenvector.dawdle;

import javax.swing.SwingUtilities;

public class App {
    public static void main( String[] args ) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
              WindowManager.initialize();
            }
        });
        System.out.println( "Dawdle v"+PropertyManager.getInstance().getValue("version")+" started..." );
    }
}

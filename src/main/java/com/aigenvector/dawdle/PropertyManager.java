package com.aigenvector.dawdle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.util.Date;
import java.util.Properties;
 
public class PropertyManager {
  //TODO Stubbed
  private static PropertyManager _instance = null;
  private Properties _props = null;

  private PropertyManager() {
    InputStream inputStream = null;
    try {
      _props = new Properties();
      String propFileName = "application.properties";
 
      inputStream = getClass().getClassLoader()
        .getResourceAsStream(propFileName);
 
      if (inputStream != null) {
        _props.load(inputStream);
      } else {
        throw new FileNotFoundException("Property file '" + propFileName 
          + "' not found in the classpath.");
      }
    } catch (Exception e) {
      System.out.println("Exception: " + e);
    } finally {
      if(inputStream != null) {
        try {
          inputStream.close();
        } catch(IOException ioe) {
          System.out.println("IOException: " + ioe);
        }
      }
    }
  }

  public static void initialize() {
    PropertyManager.getInstance();
  }

  public static PropertyManager getInstance() {
    if(_instance == null) {
      _instance = new PropertyManager();
    }
    return _instance;
  }

  public String getValue(String rhs) {
    return _props.getProperty(rhs);
  }
}

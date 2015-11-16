package com.aigenvector.dawdle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.Timer;

public class ExperimentManager {
	
  private static ExperimentManager _instance = null;
  private Random randomGen;
  private Canvas _canvas;
  private Timer timer;
  private ArrayList<Long> _distractionTimes = null;
  private boolean isStopped = false;
  
  private ExperimentManager() {
	  randomGen = new Random();
	  randomGen.setSeed(new Date().getTime());
	  _distractionTimes = new ArrayList<Long>();
	  timer = new Timer(randomGen.nextInt(Integer.parseInt(PropertyManager.getInstance().getValue("random.max.interval"))), new TimerListener());
	  timer.setRepeats(false);
  }

  public static void initialize() {
    ExperimentManager.getInstance();
  }

  public static ExperimentManager getInstance() {
    if(_instance == null) {
      _instance = new ExperimentManager();
    }
    return _instance;
  }
  
  public void startExperiment(Canvas canv) {
	  System.out.println("Experiment started.");
	  _canvas = canv;
	  isStopped = false;
	  timer.start();
  }
  
  private class TimerListener implements ActionListener {
	  public void actionPerformed(ActionEvent e) {
		  System.out.println("Experiment timer fired.");
		  if(!isStopped) {
			  int delay = randomGen.nextInt(Integer.parseInt(PropertyManager.getInstance().getValue("random.max.interval")));
			  timer.setInitialDelay(delay);
			  timer.setDelay(delay);
			  addDistractionTime();
			  _canvas.randomFlash();
			  timer.restart();
		  }
	  }
  }
  
  public void stopExperiment() {
	  System.out.println("Experiment stopped.");
	  isStopped = true;
  }
  
  public void resetExperiment() {
	  System.out.println("Experiment reset.");
	  this._distractionTimes.clear();
  }
  
  public void addDistractionTime() {
	  this._distractionTimes.add(new Date().getTime());
  }
  
  public ArrayList<Long> getDistractionTimes() {
	  return this._distractionTimes;
  }
  
}

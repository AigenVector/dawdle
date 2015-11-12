package com.aigenvector.dawdle;

import java.util.ArrayList;

public class FlashCoordinateManager {
	
	private static FlashCoordinateManager _instance = null;
	private ArrayList<int[]> _pointCollect = null;
	
	private FlashCoordinateManager() {
		_pointCollect = new ArrayList<int[]>();
	}

	public static FlashCoordinateManager getInstance() {
		if(_instance == null) {
			_instance = new FlashCoordinateManager();
		}
		return _instance;
	}
	
	public void addPoint(int x, int y) {
		System.out.println("Adding coordinate "+x+", "+y+".");
		_pointCollect.add(new int[]{x, y});
	}
	
	public int pointCount() {
		return _pointCollect.size();
	}
	
	public void clear() {
		_pointCollect.clear();
	}
}

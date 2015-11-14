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

	public void removePoint(int x, int y) {
		System.out.println("Removing coordinate "+x+", "+y);
		boolean found = false;
		for(int i = 0; i < _pointCollect.size(); i++) {
			if(_pointCollect.get(i)[0] == x && 
					_pointCollect.get(i)[1] == y) {
				_pointCollect.remove(i);
				found = true;
				break;
			}
		}
		System.out.println("Removed coordinate? "+x+", "+y+" - result: "+found);
	}
	
	public ArrayList<int[]> getPoints() {
		return _pointCollect;
	}
}

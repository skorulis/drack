package com.skorulis.drack.pathfinding;

import com.skorulis.drack.map.MapSquare;


public class PathNodeInfo {
	
	public PathNodeInfo parent;
	public MapSquare square;
	public float value;
	public float heuristic;
	
	public PathNodeInfo(MapSquare square) {
		this.square = square;
	}
	
	public PathNodeInfo(MapSquare square,PathNodeInfo parent) {
		this.square = square;
		this.parent = parent;
	}
	
	public float score() {
		return value + heuristic;
	}
	
	public String toString() {
		return "(" + square.x() + ", " + square.z() +") V: " + value + " S: " + score();
	}
	
}

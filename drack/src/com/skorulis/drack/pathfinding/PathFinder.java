package com.skorulis.drack.pathfinding;

import java.util.ArrayList;
import java.util.Collections;

import com.skorulis.drack.map.GameMap;
import com.skorulis.drack.map.MapSquare;

public class PathFinder {

	private MapSquare from;
	private MapSquare to;
	private ArrayList<PathNodeInfo> openList;
	private ArrayList<PathNodeInfo> closedList;
	private GameMap map;
	
	public PathFinder(GameMap map, MapSquare from, MapSquare to) {
		this.from = from;
		this.to = to;
		this.map = map;
		openList = new ArrayList<PathNodeInfo>();
		closedList = new ArrayList<PathNodeInfo>();
	}
	
	public MapPath generatePath() {
		PathNodeInfo pni = new PathNodeInfo(from);
		pni.heuristic = calculateHeuristic(pni.square, to);
		openList.add(pni);
		
		while(true) {
			pni = removeNext();
			if(pni == null) {
				break;
			} else if(squaresEqual(pni.square, to)) {
				return buildPath(pni);
			} else {
				evaluate(pni);
			}	
		}
		
		return null;
	}
	
	private MapPath buildPath(PathNodeInfo pni) {
		ArrayList<PathNodeInfo> nodes = new ArrayList<PathNodeInfo>();
		while(pni != null) {
			nodes.add(pni);
			pni = pni.parent;
		}
		Collections.reverse(nodes);
		return new MapPath(nodes);
	}
	
	private PathNodeInfo removeNext() {
		if(openList.isEmpty()) {
			return null;
		}
		PathNodeInfo best = openList.get(0);
		for(PathNodeInfo pni : openList) {
			boolean better = pni.score() < best.score() || (pni.score() == best.score() && pni.value > best.value); 
			if(better) {
				best = pni;
			}
		}
		openList.remove(best);
		return best;
	}
	
	private void evaluate(PathNodeInfo parentNode) {
		closedList.add(parentNode);
		ArrayList<MapSquare> adj = map.adjacentSquares(parentNode.square);
		for(MapSquare cs : adj) {
			if(!cs.isPassable()) {
				continue;
			}
			
			PathNodeInfo pni = new PathNodeInfo(cs,parentNode);
			pni.value = parentNode.value + calculateHeuristic(parentNode.square, pni.square);
			pni.heuristic = calculateHeuristic(pni.square, to);
			PathNodeInfo match = findNode(cs);
			
			if(match == null) {
				openList.add(pni);
			} else {
				if(pni.value < match.value) {
					openList.remove(match);
					closedList.remove(match);
					openList.add(pni);
				}
			}
		}
	}
	
	private PathNodeInfo findNode(MapSquare square) {
		for(PathNodeInfo pni : openList) {
			if(squaresEqual(pni.square, square)) {
				return pni;
			}
		}
		
		for(PathNodeInfo pni : closedList) {
			if(squaresEqual(pni.square, square)) {
				return pni;
			}
		}
		
		return null;
	}
	
	private boolean squaresEqual(MapSquare s1, MapSquare s2) {
		return s1.x() == s2.x() && s1.z() == s2.z();
	}
	
	private int calculateHeuristic(MapSquare square, MapSquare dest) {
		if(square == null) { throw new IllegalArgumentException("Missing from"); }
		if(dest == null) { throw new IllegalArgumentException("Missing dest"); }
		int x = (int) (dest.x() - square.x());
		int y = (int) (dest.z() - square.z());
		x = Math.abs(x);
		y = Math.abs(y);
		
		int min = Math.min(x, y);
		int max = Math.max(x, y);
		
		int diff = max - min;
		
		return (int)(diff + (max - diff) * 1.4);
	}
	
}

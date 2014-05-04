package com.skorulis.drack.pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.actor.building.Building;
import com.skorulis.drack.map.GameMap;
import com.skorulis.drack.map.MapSquare;
import com.skorulis.drack.scene.DrackActorNode;

public class PathFinder {

	private MapSquare from;
	private MapSquare to;
	private Set<MapSquare> nearSquares;
	private final ArrayList<PathNodeInfo> openList;
	private final ArrayList<PathNodeInfo> closedList;
	private final GameMap map;
	
	public PathFinder(GameMap map, MapSquare from, MapSquare to) {
		this(map,from,to,null);
	}
	
	public PathFinder(GameMap map, MapSquare from, MapSquare to, Set<MapSquare> nearSquares) {
		this(map);
		this.to = to;
		this.nearSquares = nearSquares;	
	}
	
	public PathFinder(GameMap map) {
		this.map = map;
		openList = new ArrayList<PathNodeInfo>();
		closedList = new ArrayList<PathNodeInfo>();	
	}
	
	public MapPath navigate(DrackActorNode unit, Building to) {
		Vector3 pos = unit.currentPosition();
		MapSquare from = map.squareAt(pos);
		return navigate(from, to);
	}
	
	public MapPath navigate(MapSquare from, Building to) {
		this.from = from;
		
		Set<MapSquare> squares = to.coveredSquares();
		this.to = squares.iterator().next();
		this.nearSquares = map.squaresAround(to);
		
		return generatePath();
	}
	
	public MapPath navigate(MapSquare from, MapSquare to) {
		this.from = from;
		this.to = to;
		if(to.anyBuilding() != null) {
			nearSquares = map.adjacentSquares(to);
		} else {
			this.nearSquares = new HashSet<MapSquare>();
		}
		
		return generatePath();
	}
	
	public MapPath generatePath() {
		openList.clear();
		closedList.clear();
		PathNodeInfo pni = new PathNodeInfo(from);
		if(isAtEnd(pni)) {
			return null;
		}
		pni.heuristic = calculateHeuristic(pni.square, to);
		openList.add(pni);
		
		while(true) {
			pni = removeNext();
			if(pni == null) {
				break;
			} else if(isAtEnd(pni)) {
				return buildPath(pni);
			} else {
				evaluate(pni);
			}	
		}
		
		return null;
	}
	
	private boolean isAtEnd(PathNodeInfo pni) {
		if(pni.square == to) {
			return true;
		}
		if(nearSquares == null) {
			return false;
		}
		for(MapSquare ms: nearSquares) {
			if(pni.square == ms) {
				return true;
			}
		}
		return false;
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
		Set<MapSquare> adj = map.adjacentSquares(parentNode.square);
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
	
	private float calculateHeuristic(MapSquare square, MapSquare dest) {
		if(square == null) { throw new IllegalArgumentException("Missing from"); }
		if(dest == null) { throw new IllegalArgumentException("Missing dest"); }
		float x =  (dest.x() - square.x());
		float y =  (dest.z() - square.z());
		x = Math.abs(x);
		y = Math.abs(y);
		
		float min = Math.min(x, y);
		float max = Math.max(x, y);
		
		float diff = max - min;
		
		return (diff + (max - diff) * 1.41f);
	}
	
}

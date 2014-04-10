package com.skorulis.drack.pathfinding;

import java.util.List;
import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.map.MapSquare;

public class ComputerPath {

	private List<PathNodeInfo> nodes;
	private int nodeIndex;
	
	public ComputerPath(List<PathNodeInfo> nodes) {
		this.nodes = nodes;
		nodeIndex = 0;
	}
	
	public MapSquare currentNode() {
		return this.nodes.get(nodeIndex).square;
	}
	
	public MapSquare nextNode() {
		return this.nodes.get(nodeIndex + 1).square;
	}
	
	public MovementInfo getMovement(float speed) {
		Vector3 v1 = nodes.get(nodeIndex).square.getCentreLoc();
		Vector3 v2 = nodes.get(nodeIndex + 1).square.getCentreLoc();
		MovementInfo mi = new MovementInfo(v1, v2, speed);
		mi.destSquare = nextNode();
		return mi;
	}
	
	public MovementInfo next(float speed) {
		nodeIndex ++;
		return getMovement(speed);
	}
	
	public boolean finished() {
		return nodeIndex == this.nodes.size() - 2;
	}
	
}

package com.skorulis.drack.pathfinding;

import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.map.MapSquare;

public class MovementInfo {

	public float travelTime;
	public float currentTime;
	
	public Vector3 startLoc;
	public Vector3 endLoc;
	private Vector3 direction;
	
	public MapSquare destSquare;
	
	public MovementInfo(Vector3 startLoc, Vector3 endLoc,float speed) {
		this.startLoc = startLoc.cpy();
		this.endLoc = endLoc.cpy();
		this.direction = this.endLoc.cpy().sub(this.startLoc).nor();
		direction.x *= -1;
		this.travelTime = startLoc.cpy().sub(endLoc).len() / speed;
	}
	
	public Vector3 update(float delta) {
		currentTime += delta;
		currentTime = Math.min(currentTime, travelTime);
		
		float pct = currentTime / travelTime;
		Vector3 v1 = startLoc.cpy().scl(1-pct);
		Vector3 v2 = endLoc.cpy().scl(pct);
		
		return v1.add(v2);
	}
	
	public boolean finished() {
		return currentTime == travelTime;
	}
	
	public Vector3 direction() {
		return direction;
	}
	
}

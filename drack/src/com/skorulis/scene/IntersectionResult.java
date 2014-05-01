package com.skorulis.scene;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

public class IntersectionResult implements Comparable<IntersectionResult> {

	private SceneNode node;
	private Vector3 point;
	private float intersectionTime = Float.NaN;
	
	public IntersectionResult() {
		
	}
	
	public IntersectionResult(SceneNode node, Vector3 point) {
		this.node = node;
		this.point = point;
	}
	
	public float intersectionTime(Ray ray) {
		if(Float.isNaN(intersectionTime)) {
			intersectionTime = point.cpy().sub(ray.origin).len();
		}
		return intersectionTime;
	}
	
	public SceneNode node() {
		return node;
	}
	
	public Vector3 point() {
		return point;
	}

	@Override
	public int compareTo(IntersectionResult o) {
		if(this.intersectionTime == o.intersectionTime) {
			return 0;
		} else if(this.intersectionTime < o.intersectionTime) {
			return -1;
		}
		return 1;
	}
	
	public String toString() {
		return "" + node + " at " + point + " t: " + intersectionTime;
	}
}

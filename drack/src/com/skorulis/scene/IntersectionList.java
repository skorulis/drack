package com.skorulis.scene;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

public class IntersectionList {

	private Set<IntersectionResult> intersections;
	public Vector3 tmpPoint;
	private final Ray ray;
	
	public IntersectionList(Ray ray) {
		this.ray = ray;
		intersections = new HashSet<IntersectionResult>();
		tmpPoint = new Vector3();
	}
	
	public void addIntersection(IntersectionResult result) {
		intersections.add(result);
	}
	
	public void addIntersection(SceneNode node, Vector3 point) {
		IntersectionResult res = new IntersectionResult(node, point.cpy());
		intersections.add(res);
	}
	
	public Ray ray() {
		return ray;
	}
	
	public int intersectionCount() {
		return intersections.size();
	}
	
	public ArrayList<IntersectionResult> sortedResults() {
		for(IntersectionResult ir : intersections) {
			ir.intersectionTime(ray);
		}
		ArrayList<IntersectionResult> list = new ArrayList<IntersectionResult>(intersections);
		Collections.sort(list);
		return list;
	}
}

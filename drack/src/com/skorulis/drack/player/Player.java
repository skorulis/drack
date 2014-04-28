package com.skorulis.drack.player;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.building.Building;
import com.skorulis.drack.resource.ResourceBatch;
import com.skorulis.drack.unit.Unit;
import com.skorulis.scene.SceneHelper;

public class Player {

	private String playerId;
	private ResourceBatch resources;
	private Unit controllingUnit;
	private Set<Unit> ownedUnits;
	private Set<Building> ownedBuildings;
	
	public Player() {
		resources = new ResourceBatch();
		ownedUnits = new HashSet<Unit>();
		ownedBuildings = new HashSet<Building>();
	}
	
	public void addUnit(Unit unit) {
		this.ownedUnits.add(unit);
	}
	
	public void addBuilding(Building building) {
		ownedBuildings.add(building);
		building.setOwner(this);
	}
	
	public Unit controllUnit() {
		return controllingUnit;
	}
	
	public void setControllingUnit(Unit unit) {
		this.controllingUnit = unit;
	}
	
	public ResourceBatch resources() {
		return resources;
	}
	
	public Set<Unit> ownedUnits() {
		return ownedUnits;
	}
	
	public Unit freeUnit() {
		for(Unit u : ownedUnits) {
			if(u != controllingUnit) {
				return u;
			}
		}
		return null;
	}
	
	public Building findBuilding(String name, Vector3 near) {
		Building best = null;
		float bestDist = Float.MAX_VALUE;
		for(Building b: ownedBuildings) {
			if(b.def().name().equals(name)) {
				float dist = SceneHelper.dist(b, near);
				if(dist < bestDist) {
					best = b;
				}
			}
		}
		return best;
	}
	
	public void update(float delta) {
		Iterator<Unit> it = ownedUnits.iterator();
		while(it.hasNext()) {
			Unit u = it.next();
			if(!u.isAlive()) {
				it.remove();
			}
		}
	}
	
	public String playerId() {
		return playerId;
	}
	
	
}

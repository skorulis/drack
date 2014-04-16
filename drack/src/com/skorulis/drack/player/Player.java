package com.skorulis.drack.player;

import java.util.HashSet;
import java.util.Set;

import com.skorulis.drack.resource.ResourceBatch;
import com.skorulis.drack.unit.Unit;

public class Player {

	private ResourceBatch resources;
	private Unit controllingUnit;
	private Set<Unit> ownedUnits;
	
	public Player() {
		resources = new ResourceBatch();
		ownedUnits = new HashSet<Unit>();
	}
	
	public void addUnit(Unit unit) {
		this.ownedUnits.add(unit);
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
	
	
}

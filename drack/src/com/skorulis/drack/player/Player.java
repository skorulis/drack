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
	
	public Unit controllUnit() {
		return controllingUnit;
	}
	
	public void setControllingUnit(Unit unit) {
		this.controllingUnit = unit;
	}
	
	public ResourceBatch resources() {
		return resources;
	}
	
	
	
}

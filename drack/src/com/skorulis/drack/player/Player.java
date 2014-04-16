package com.skorulis.drack.player;

import com.skorulis.drack.resource.ResourceBatch;
import com.skorulis.drack.unit.Unit;

public class Player {

	private ResourceBatch resources;
	private Unit controllingUnit;
	
	public Player() {
		resources = new ResourceBatch();
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

package com.skorulis.drack.def.building;

import com.skorulis.drack.def.attachment.HardPointContainer;

public class CompositeBuildingDef extends BuildingDef {

	public HardPointContainer hardPoints;
	
	public CompositeBuildingDef(String name) {
		super(name);
		this.hardPoints = new HardPointContainer();
	}
	
}

package com.skorulis.drack.def.building;

import java.util.HashSet;
import java.util.Set;

import com.skorulis.drack.def.attachment.HardPointDef;

public class CompositeBuildingDef extends BuildingDef {

	public Set<HardPointDef> hardPoints;
	
	public CompositeBuildingDef(String name) {
		super(name);
		this.hardPoints = new HashSet<HardPointDef>();
	}
	
	public void addHardPoint(HardPointDef point) {
		hardPoints.add(point);
	}
	
}

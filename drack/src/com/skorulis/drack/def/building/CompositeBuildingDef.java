package com.skorulis.drack.def.building;

import java.util.HashSet;
import java.util.Set;

import com.skorulis.drack.def.attachment.HullPointDef;

public class CompositeBuildingDef extends BuildingDef {

	public Set<HullPointDef> hardPoints;
	
	public CompositeBuildingDef(String name) {
		super(name);
		this.hardPoints = new HashSet<HullPointDef>();
	}
	
	public void addHardPoint(HullPointDef point) {
		hardPoints.add(point);
	}
	
}

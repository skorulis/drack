package com.skorulis.drack.def.building;

import java.util.HashSet;
import java.util.Set;

import com.skorulis.drack.def.attachment.HullPointDef;

public class CompositeBuildingDef extends BuildingDef {

	public Set<HullPointDef> points;
	
	public CompositeBuildingDef(String name) {
		super(name);
		this.points = new HashSet<HullPointDef>();
	}
	
}

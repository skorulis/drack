package com.skorulis.drack.def.building;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.def.attachment.HardPointDef;
import com.skorulis.drack.def.attachment.HardPointDef.HullPointType;

public class CompositeBuildingDef extends BuildingDef {

	public Set<HardPointDef> hardPoints;
	
	public CompositeBuildingDef(String name) {
		super(name);
		this.hardPoints = new HashSet<HardPointDef>();
	}
	
	public void addHardPoint(HardPointDef point) {
		hardPoints.add(point);
	}
	
	public void addHardPoint(Vector3 loc, float rotation, HullPointType type) {
		HardPointDef hardPoint = new HardPointDef(loc, rotation, type, this.hardPoints.size());
		this.hardPoints.add(hardPoint);
	}
	
}

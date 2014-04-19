package com.skorulis.drack.def.unit;

import java.util.HashSet;
import java.util.Set;

import com.skorulis.drack.def.BaseDef;

public class HullDef extends BaseDef {
	
	public String modelName;
	public Set<HullPointDef> points;
	public int baseCapacity;
	public float baseSpeed;
	
	public HullDef(String name) {
		super(name);
		this.points = new HashSet<HullPointDef>();
	}
	
	public void addPoint(HullPointDef point) {
		this.points.add(point);
	}

}

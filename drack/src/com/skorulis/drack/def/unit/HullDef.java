package com.skorulis.drack.def.unit;

import java.util.HashSet;
import java.util.Set;

import com.skorulis.drack.def.BaseDef;
import com.skorulis.drack.def.attachment.HullPointDef;

public class HullDef extends BaseDef {
	
	public String modelName;
	public Set<HullPointDef> hardPoints;
	public int baseCapacity;
	public float baseSpeed;
	
	public HullDef(String name) {
		super(name);
		this.hardPoints = new HashSet<HullPointDef>();
	}
	
	public void addPoint(HullPointDef point) {
		this.hardPoints.add(point);
	}
	
	public Class<? extends BaseDef> getTypeClass() {
		return HullDef.class;
	}

}

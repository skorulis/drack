package com.skorulis.drack.def.unit;

import java.util.HashSet;
import java.util.Set;

import com.skorulis.drack.def.BaseDef;
import com.skorulis.drack.def.attachment.HardPointDef;

public class HullDef extends BaseDef {
	
	public String modelName;
	public Set<HardPointDef> hardPoints;
	public int baseCapacity;
	public float baseSpeed;
	
	public HullDef(String name) {
		super(name);
		this.hardPoints = new HashSet<HardPointDef>();
	}
	
	public void addPoint(HardPointDef point) {
		this.hardPoints.add(point);
	}
	
	public Class<? extends BaseDef> getTypeClass() {
		return HullDef.class;
	}

}

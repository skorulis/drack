package com.skorulis.drack.def;

import java.util.ArrayList;

import com.skorulis.drack.def.unit.HullPointDef;

public class HullDef extends BaseDef {
	
	public String modelName;
	public ArrayList<HullPointDef> points;
	public int baseCapacity;
	public float baseSpeed;
	
	public HullDef(String name) {
		super(name);
		this.points = new ArrayList<HullPointDef>();
	}

}

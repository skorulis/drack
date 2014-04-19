package com.skorulis.drack.unit.composite;

import com.skorulis.drack.def.unit.HullDef;

public class UnitHull {

	private HullDef def;
	
	public UnitHull(HullDef def) {
		this.def = def;
	}
	
	public HullDef def() {
		return def;
	}
	
}

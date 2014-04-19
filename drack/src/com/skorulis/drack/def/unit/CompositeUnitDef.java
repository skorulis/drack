package com.skorulis.drack.def.unit;

import com.skorulis.drack.def.HullDef;
import com.skorulis.drack.def.UnitDef;

public class CompositeUnitDef extends UnitDef {

	public HullDef hull;
	
	public CompositeUnitDef(String name) {
		super(name);
	}
	
	public CompositeUnitDef cpy() {
		CompositeUnitDef copy = new CompositeUnitDef(this.name());
		
		return copy;
	}
	
	public String modelName() {
		return hull.modelName;
	}
	
	public float speed() {
		return hull.baseSpeed;
	}
	
	
}

package com.skorulis.drack.def.unit;

import com.skorulis.drack.def.BaseDef;


public class CompositeUnitDef extends BaseDef implements UnitDef{

	public HullDef hull;
	
	public CompositeUnitDef(String name) {
		super(name);
	}
	
	public CompositeUnitDef cpy() {
		CompositeUnitDef copy = new CompositeUnitDef(this.name());
		
		return copy;
	}
	
	@Override
	public String modelName() {
		return hull.modelName;
	}
	
	@Override
	public float speed() {
		return hull.baseSpeed;
	}

	@Override
	public int resourceCapacity() {
		return hull.baseCapacity;
	}
	
	
	
	
}

package com.skorulis.drack.def.unit;

import com.skorulis.drack.def.BaseDef;

public class BasicUnitDef extends BaseDef implements UnitDef {

	private String modelName;
	private float speed;
	private int resourceCapacity;
	
	public BasicUnitDef(String name) {
		super(name);
	}
	
	public String modelName() {
		return modelName;
	}
	
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	public float speed() {
		return speed;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public int resourceCapacity() {
		return resourceCapacity;
	}
	
	public void setResourceCapacity(int cap) {
		this.resourceCapacity = cap;
	}
	
}
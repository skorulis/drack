package com.skorulis.drack.def;

public class UnitDef extends BaseDef {

	private String modelName;
	private float speed;
	private int resourceCapacity;
	
	public UnitDef(String name) {
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

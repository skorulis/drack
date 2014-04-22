package com.skorulis.drack.def;

public abstract class BaseDef {

	private final String name;
	
	public BaseDef(String name) {
		this.name = name;
	}
	
	public String name() {
		return name;
	}
	
	public abstract Class<? extends BaseDef> getTypeClass();
	
}

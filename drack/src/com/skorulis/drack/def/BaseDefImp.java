package com.skorulis.drack.def;

public abstract class BaseDefImp implements BaseDef{

	private final String name;
	
	public BaseDefImp(String name) {
		this.name = name;
	}
	
	public String name() {
		return name;
	}
	
}

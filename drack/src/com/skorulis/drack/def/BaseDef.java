package com.skorulis.drack.def;

public interface BaseDef {

	public String name();
	public Class<? extends BaseDef> getTypeClass();
	
}

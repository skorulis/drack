package com.skorulis.drack.def;

public class ResourceDef extends BaseDefImp {

	public ResourceDef(String name) {
		super(name);
	}
	
	public Class<? extends BaseDef> getTypeClass() {
		return ResourceDef.class;
	}
	
}

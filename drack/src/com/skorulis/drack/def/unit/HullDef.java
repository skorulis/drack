package com.skorulis.drack.def.unit;

import com.skorulis.drack.def.BaseDef;
import com.skorulis.drack.def.BaseDefImp;
import com.skorulis.drack.def.attachment.HardPointContainer;

public class HullDef extends BaseDefImp {
	
	public String modelName;
	public HardPointContainer hardPoints;
	public int baseCapacity;
	public float baseSpeed;
	
	public HullDef(String name) {
		super(name);
		this.hardPoints = new HardPointContainer();
	}
	
	public Class<? extends BaseDef> getTypeClass() {
		return HullDef.class;
	}

}

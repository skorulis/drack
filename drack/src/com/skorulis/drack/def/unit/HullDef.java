package com.skorulis.drack.def.unit;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.def.BaseDef;
import com.skorulis.drack.def.BaseDefImp;
import com.skorulis.drack.def.attachment.HardPointDef;
import com.skorulis.drack.def.attachment.HardPointDef.HullPointType;

public class HullDef extends BaseDefImp {
	
	public String modelName;
	public Set<HardPointDef> hardPoints;
	public int baseCapacity;
	public float baseSpeed;
	
	public HullDef(String name) {
		super(name);
		this.hardPoints = new HashSet<HardPointDef>();
	}
	
	public void addPoint(HardPointDef point) {
		this.hardPoints.add(point);
	}
	
	public void addHardPoint(Vector3 loc, float rotation, HullPointType type) {
		HardPointDef hardPoint = new HardPointDef(loc, rotation, type, this.hardPoints.size());
		this.hardPoints.add(hardPoint);
	}
	
	public Class<? extends BaseDef> getTypeClass() {
		return HullDef.class;
	}

}

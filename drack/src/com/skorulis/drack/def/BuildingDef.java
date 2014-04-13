package com.skorulis.drack.def;

import com.badlogic.gdx.assets.AssetManager;
import com.skorulis.drack.building.Building;

public class BuildingDef extends BaseDef {

	public Class<? extends Building> buildingClass;
	public String modelName;
	public boolean isBuildable;
	public int width;
	public int depth;
	
	public BuildingDef(String name) {
		super(name);
		this.width = 1;
		this.depth = 1;
	}
	
	public Building create(AssetManager assets) {
		try {
			Building ret = buildingClass.newInstance();
			ret.setDef(this);
			ret.loadModel(assets);
			return ret;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean isBuildable() {
		return isBuildable;
	}
	
}

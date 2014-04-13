package com.skorulis.drack.def;

import com.skorulis.drack.building.Building;
import com.skorulis.gdx.SKAssetManager;

public class BuildingDef extends BaseDef {

	public Class<? extends Building> buildingClass;
	public String modelName;
	public boolean isBuildable;
	public int width;
	public int depth;
	public boolean replacesTerrain;
	
	public BuildingDef(String name) {
		super(name);
		this.width = 1;
		this.depth = 1;
		this.replacesTerrain = false;
	}
	
	public Building create(SKAssetManager assets) {
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

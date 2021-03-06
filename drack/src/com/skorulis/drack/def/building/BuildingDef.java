package com.skorulis.drack.def.building;

import com.skorulis.drack.actor.building.Building;
import com.skorulis.drack.def.BaseDef;
import com.skorulis.drack.def.BaseDefImp;
import com.skorulis.drack.ui.building.BuildingUI;
import com.skorulis.gdx.SKAssetManager;

public class BuildingDef extends BaseDefImp {

	public Class<? extends Building> buildingClass;
	public Class<? extends BuildingUI> uiClass;
	public String modelName;
	public boolean isBuildable;
	public int width;
	public int depth;
	public boolean replacesTerrain;
	
	public BuildingDef(String name) {
		super(name);
		buildingClass = Building.class;
		uiClass = BuildingUI.class;
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
	
	public BuildingUI createUI() {
		try {
			BuildingUI ret = uiClass.newInstance();
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
	
	public String iconName() {
		return name() + "_building_icon";
	}
	
	public Class<? extends BaseDef> getTypeClass() {
		return BuildingDef.class;
	}
	
}

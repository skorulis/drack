package com.skorulis.drack.def.unit;

import com.skorulis.drack.def.BaseDef;
import com.skorulis.drack.player.Player;
import com.skorulis.drack.unit.Unit;
import com.skorulis.gdx.SKAssetManager;

public class BasicUnitDef extends BaseDef implements UnitDef {

	private String modelName;
	private float speed;
	private int resourceCapacity;
	
	public BasicUnitDef(String name) {
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

	public Unit create(SKAssetManager assets, Player player) {
		try {
			Unit ret = unitClass().newInstance();
			ret.setDef(this);
			ret.setOwner(player);
			ret.loadAssets(assets);
			return ret;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Class<? extends Unit> unitClass() {
		return Unit.class;
	}
	
}

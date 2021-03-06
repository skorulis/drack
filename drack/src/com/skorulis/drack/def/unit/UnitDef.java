package com.skorulis.drack.def.unit;

import com.skorulis.drack.actor.unit.Unit;
import com.skorulis.drack.def.BaseDef;
import com.skorulis.drack.player.Player;
import com.skorulis.gdx.SKAssetManager;

public interface UnitDef extends BaseDef {
	
	public String modelName();
	public float speed();
	public int resourceCapacity();
	
	public Unit create(SKAssetManager assets, Player player);
	public Class<? extends Unit> unitClass();
	
}

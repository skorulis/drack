package com.skorulis.drack.def.unit;

import com.skorulis.drack.actor.unit.Unit;
import com.skorulis.drack.def.BaseDef;
import com.skorulis.drack.def.BaseDefImp;
import com.skorulis.drack.player.Player;
import com.skorulis.drack.unit.composite.CompositeUnit;
import com.skorulis.gdx.SKAssetManager;


public class CompositeUnitDef extends BaseDefImp implements UnitDef{

	public HullDef hull;
	
	public CompositeUnitDef(String name) {
		super(name);
	}
	
	public CompositeUnitDef cpy() {
		CompositeUnitDef copy = new CompositeUnitDef(this.name());
		
		return copy;
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
	
	@Override
	public String modelName() {
		return hull.modelName;
	}
	
	@Override
	public float speed() {
		return hull.baseSpeed;
	}

	@Override
	public int resourceCapacity() {
		return hull.baseCapacity;
	}
	
	public Class<? extends Unit> unitClass() {
		return CompositeUnit.class;
	}
	
	public Class<? extends BaseDef> getTypeClass() {
		return UnitDef.class;
	}
	
	
	
}

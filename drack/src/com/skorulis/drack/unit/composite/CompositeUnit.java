package com.skorulis.drack.unit.composite;

import com.skorulis.drack.def.unit.CompositeUnitDef;
import com.skorulis.drack.player.Player;
import com.skorulis.drack.unit.Unit;
import com.skorulis.gdx.SKAssetManager;

public class CompositeUnit extends Unit {

	public CompositeUnit(SKAssetManager assets, Player owner, CompositeUnitDef def) {
		super(assets, owner, def);
	}
	
	public CompositeUnitDef compDef() {
		return (CompositeUnitDef) this.def;
	}
	
	


}

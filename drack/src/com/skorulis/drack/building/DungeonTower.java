package com.skorulis.drack.building;

import java.util.Set;

import com.skorulis.drack.map.MapSquare;
import com.skorulis.drack.player.Player;
import com.skorulis.drack.unit.Unit;
import com.skorulis.scene.UpdateInfo;

public class DungeonTower extends Building{

	public DungeonTower() {
		this.owner = new Player();
		this.owner.addBuilding(this);
	}
	
	public void update(UpdateInfo info) {
		if(this.owner.ownedUnits().size() < 1) {
			Set<MapSquare> squares = info.map().squaresAround(this);
			Unit unit = info.def().getUnit("enemy").create(info.assets(), dungeonEnemy()); 
			
			info.logic.scene().addUnit(unit, squares.iterator().next());
		}
		this.owner.update(info.delta); //HACK, players need to be put somewhere else
	}
	
	public Player dungeonEnemy() {
		return this.owner;
	}
	
}

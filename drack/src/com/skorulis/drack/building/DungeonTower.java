package com.skorulis.drack.building;

import java.util.Set;

import com.skorulis.drack.def.unit.CompositeUnitDef;
import com.skorulis.drack.map.MapSquare;
import com.skorulis.drack.player.Player;
import com.skorulis.drack.unit.composite.CompositeUnit;
import com.skorulis.drack.unit.composite.HullAttachment;
import com.skorulis.scene.UpdateInfo;

public class DungeonTower extends Building{

	public DungeonTower() {
		this.owner = new Player();
		this.owner.addBuilding(this);
	}
	
	public void update(UpdateInfo info) {
		if(this.owner.ownedUnits().size() < 1) {
			Set<MapSquare> squares = info.map().squaresAround(this);
			
			CompositeUnitDef cud = info.def().createCompositeDefWithHull("tank");
			CompositeUnit unit = (CompositeUnit) cud.create(info.assets(), dungeonEnemy());
			
			HullAttachment att = info.def().getAttachment("gun").create(info.assets(), unit.attachmentContainer().emptyPoint());
			
			unit.attachmentContainer().addAttachment(att);
			
			info.logic.scene().addUnit(unit, squares.iterator().next());
		}
		this.owner.update(info.delta); //HACK, players need to be put somewhere else
	}
	
	public Player dungeonEnemy() {
		return this.owner;
	}
	
}

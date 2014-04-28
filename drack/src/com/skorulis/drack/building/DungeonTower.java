package com.skorulis.drack.building;

import java.util.Set;
import com.skorulis.drack.def.unit.UnitDef;
import com.skorulis.drack.map.MapSquare;
import com.skorulis.drack.player.Player;
import com.skorulis.drack.unit.composite.CompositeUnit;
import com.skorulis.drack.unit.composite.HullAttachment;
import com.skorulis.scene.UpdateInfo;

public class DungeonTower extends Building{

	public DungeonTower() {
		
	}
	
	public void update(UpdateInfo info) {
		if(this.owner.ownedUnits().size() < 1) {
			Set<MapSquare> squares = info.map().squaresAround(this);
			
			UnitDef cud = info.def().getUnit("tank");
			CompositeUnit unit = (CompositeUnit) cud.create(info.assets(), dungeonEnemy());
			
			HullAttachment att = info.def().getAttachment("gun").create(info.assets(), unit.attachmentContainer().emptyPoint());
			
			unit.attachmentContainer().addAttachment(att);
			
			info.logic.scene().addUnit(unit, squares.iterator().next());
		}
	}
	
	public Player dungeonEnemy() {
		return this.owner;
	}
	
}

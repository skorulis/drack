package com.skorulis.drack.game;

import com.skorulis.drack.effects.Effect2DLayer;
import com.skorulis.drack.map.MapGenerator;
import com.skorulis.drack.map.MapSquare;
import com.skorulis.drack.player.PlayerContainer;
import com.skorulis.drack.unit.composite.CompositeUnit;
import com.skorulis.drack.unit.composite.HullAttachment;

public class SceneGenerator {

	private GameScene scene;
	
	public SceneGenerator(MapGenerator mapGen, Effect2DLayer effects2D, PlayerContainer players) {
		scene = new GameScene(mapGen.def(), mapGen.assets(), mapGen.map(), effects2D, players);
		
		CompositeUnit unit = (CompositeUnit) mapGen.def().getUnit("base").create(mapGen.assets(), players.humanPlayer());
		HullAttachment gun1 = mapGen.def().getWeapon("gun").create(mapGen.assets(), unit.attachmentContainer().emptyPoint());
		unit.attachmentContainer().addAttachment(gun1);
		
		HullAttachment gun2 = mapGen.def().getWeapon("beam").create(mapGen.assets(), unit.attachmentContainer().emptyPoint());
		unit.attachmentContainer().addAttachment(gun2);
		
		MapSquare sq = scene.map().squareAt(10, 10);
		scene.addUnit(unit, sq);
		
		players.humanPlayer().setControllingUnit(unit);
	}
	
	public GameScene scene() {
		return scene;
	}
	
}

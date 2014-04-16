package com.skorulis.drack.game;

import com.skorulis.drack.effects.Effect2DLayer;
import com.skorulis.drack.map.MapGenerator;
import com.skorulis.drack.map.MapSquare;
import com.skorulis.drack.unit.Unit;

public class SceneGenerator {

	private GameScene scene;
	
	public SceneGenerator(MapGenerator mapGen, Effect2DLayer effects2D) {
		scene = new GameScene(mapGen.def(), mapGen.assets(), mapGen.map(), effects2D);
		
		Unit unit = new Unit(mapGen.assets(), scene.player(), mapGen.def().getUnit("avatar"));
		unit.setDelegate(scene);
		MapSquare sq = scene.map().squareAt(10, 10);
		scene.addUnit(unit, sq);
		
		scene.player().setControllingUnit(unit);
	}
	
	public GameScene scene() {
		return scene;
	}
	
}
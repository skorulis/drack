package com.skorulis.scene;

import com.skorulis.drack.def.DefManager;
import com.skorulis.drack.game.GameLogic;
import com.skorulis.drack.game.GameScene;
import com.skorulis.drack.map.GameMap;
import com.skorulis.gdx.SKAssetManager;

public class UpdateInfo {

	public float delta;
	public GameLogic logic;
	
	public UpdateInfo(float delta, GameLogic logic) {
		this.delta = delta;
		this.logic = logic;
	}
	
	public GameMap map() {
		return logic.map();
	}
	
	public GameLogic logic() {
		return logic;
	}
	
	public GameScene scene() {
		return logic.scene();
	}
	
	public DefManager def() {
		return logic.scene().def();
	}
	
	public SKAssetManager assets() {
		return logic.scene().assets();
	}
	
}

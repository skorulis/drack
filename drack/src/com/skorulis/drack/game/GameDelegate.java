package com.skorulis.drack.game;

import com.skorulis.drack.actor.building.Building;
import com.skorulis.scene.SceneWindow;

public interface GameDelegate {

	public void buildingSelected(Building building);
	public void playerMoved();
	public void setSubScene(SceneWindow sw);
	
}

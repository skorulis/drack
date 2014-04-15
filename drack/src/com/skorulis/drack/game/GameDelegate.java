package com.skorulis.drack.game;

import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.building.Building;

public interface GameDelegate {

	public void buildingSelected(Building building);
	public void playerMoved();
	public void showTextEffect(Vector3 loc,String text);
	
}

package com.skorulis.drack.game;

import com.skorulis.drack.building.Building;

public interface GameDelegate {

	public void buildingSelected(Building building);
	public void playerMoved();
	
}

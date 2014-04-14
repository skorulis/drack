package com.skorulis.drack.ui.building;

import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.skorulis.drack.building.Building;
import com.skorulis.drack.building.Mine;

public class BuildingUI extends WidgetGroup {
	
	public static BuildingUI uiForBuilding(Building building) {
		if(building instanceof Mine) {
			return new MineUI((Mine)building);
		}
		throw new IllegalArgumentException("Could not find UI for " + building);
	}
	
}

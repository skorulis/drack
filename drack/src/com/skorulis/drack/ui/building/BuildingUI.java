package com.skorulis.drack.ui.building;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.skorulis.drack.building.Building;
import com.skorulis.drack.building.Mine;
import com.skorulis.drack.ui.UIManager;

public abstract class BuildingUI extends WidgetGroup {
	
	protected UIManager ui;
	
	public BuildingUI() {
		
	}
	
	public static BuildingUI uiForBuilding(Building building) {
		BuildingUI ret = null;
		if(building instanceof Mine) {
			ret = new MineUI();
		} else {
			throw new IllegalArgumentException("Could not find UI for " + building);
		}
		
		
		return ret;
	}
	
	public void init(Skin skin, UIManager ui) {
		this.ui = ui;
	}
	
	public abstract void setBuilding(Building building);
	
}

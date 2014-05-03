package com.skorulis.drack.serialisation.building;

import java.util.ArrayList;
import java.util.List;

import com.skorulis.drack.serialisation.unit.action.UnitActionJson;

public class BuildingJson {

	public String defName;
	public String playerId;
	public List<UnitActionJson> actions;
	
	public BuildingJson() {
		this.actions = new ArrayList<UnitActionJson>();
	}
	
}

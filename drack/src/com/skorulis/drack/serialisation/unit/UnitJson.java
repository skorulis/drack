package com.skorulis.drack.serialisation.unit;

import java.util.ArrayList;

import com.skorulis.drack.serialisation.unit.action.UnitActionJson;

public class UnitJson {

	public String defName;
	public String playerId;
	public int x;
	public int z;
	public boolean controlled;
	public ArrayList<UnitActionJson> actions;
	
	public UnitJson() {
		actions = new ArrayList<UnitActionJson>();
	}
	
}

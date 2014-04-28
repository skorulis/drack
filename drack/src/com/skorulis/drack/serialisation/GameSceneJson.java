package com.skorulis.drack.serialisation;

import java.util.ArrayList;

public class GameSceneJson {

	public MapJson map;
	public ArrayList<UnitJson> units;
	
	public GameSceneJson() {
		units = new ArrayList<UnitJson>();
	}
	
	
}

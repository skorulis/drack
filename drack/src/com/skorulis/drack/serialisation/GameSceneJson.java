package com.skorulis.drack.serialisation;

import java.util.ArrayList;

import com.skorulis.drack.serialisation.unit.UnitJson;

public class GameSceneJson {

	public MapJson map;
	public ArrayList<UnitJson> units;
	public ArrayList<PlayerJson> players;
	
	public GameSceneJson() {
		units = new ArrayList<UnitJson>();
		players = new ArrayList<PlayerJson>();
	}
	
	
}

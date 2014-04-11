package com.skorulis.drack.map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.building.Building;
import com.skorulis.drack.building.CommandCentre;

public class MapGenerator {

	private GameMap map;
	
	public MapGenerator(int width , int depth, AssetManager assets) {
		map = new GameMap(width, depth, assets);
		
		MapSquare square = map.squareAt(new Vector3(5, 0, 5));
		Building b = new CommandCentre(assets);
		square.setBuilding(b);
	}
	
	public GameMap map() {
		return map;
	}
	
}

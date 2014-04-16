package com.skorulis.drack.map;

import com.skorulis.drack.building.Building;
import com.skorulis.drack.building.CommandCentre;
import com.skorulis.drack.building.Mine;
import com.skorulis.drack.def.DefManager;
import com.skorulis.gdx.SKAssetManager;

public class MapGenerator {

	private GameMap map;
	private SKAssetManager assets;
	private DefManager def;
	
	public MapGenerator(int width , int depth, SKAssetManager assets,DefManager def) {
		map = new GameMap(width, depth, assets);
		this.assets = assets;
		this.def = def;
		
		CommandCentre b = (CommandCentre) addBuilding("command", 5, 5);
		b.generateField(b.fieldSize(), map);
		
		addBuilding("tree", 2, 5);
		addBuilding("tree", 12, 3);
		addBuilding("tree", 8, 15);
		
		addBuilding("barracks", 8, 8);
		
		Mine mine = (Mine) addBuilding("mine", 11, 3);
		mine.addResource(def.getResource("iron"), 1);
	}
	
	private Building addBuilding(String name, int x, int z) {
		Building building = def.getBuilding(name).create(assets);
		
		for(int i = 0; i < building.def().width; ++i) {
			for(int j = 0; j < building.def().depth; ++j) {
				MapSquare sq = map.squareAt(x + j, z + i);
				if(i == 0 && j == 0) {
					sq.setBuilding(building);
				} else {
					sq.setSharedBuilding(building);
				}
			}
		}
		
		return building;
	}
	
	public GameMap map() {
		return map;
	}
	
}

package com.skorulis.drack.map;

import com.skorulis.drack.building.Barracks;
import com.skorulis.drack.building.Building;
import com.skorulis.drack.building.CommandCentre;
import com.skorulis.drack.building.DungeonTower;
import com.skorulis.drack.building.Mine;
import com.skorulis.drack.def.DefManager;
import com.skorulis.drack.def.building.BuildingDef;
import com.skorulis.drack.player.PlayerContainer;
import com.skorulis.drack.serialisation.MapChunkJson;
import com.skorulis.drack.serialisation.MapJson;
import com.skorulis.drack.serialisation.MapSquareJson;
import com.skorulis.gdx.SKAssetManager;

public class MapGenerator {

	private final GameMap map;
	private final SKAssetManager assets;
	private final DefManager def;
	private final PlayerContainer players;
	
	public MapGenerator(int width , int depth, SKAssetManager assets,DefManager def, PlayerContainer players) {
		this.assets = assets;
		this.def = def;
		this.players = players;
		map = new GameMap(width, depth, assets);
		
	}
	
	public void generateMap() {
		CommandCentre b = (CommandCentre) addBuilding("command", 5, 5);
		players.humanPlayer().addBuilding(b);
		b.generateField(b.fieldSize(), map);
		
		addBuilding("tree", 2, 5);
		addBuilding("tree", 12, 3);
		addBuilding("tree", 8, 15);
		
		Barracks barracks = (Barracks) addBuilding("barracks", 8, 8);
		players.humanPlayer().addBuilding(barracks);
		
		Mine mine = (Mine) addBuilding("mine", 11, 3);
		mine.addResource(def.getResource("iron"), 1);
		mine.addResource(def.getResource("gold"), 1);
		
		DungeonTower tower = (DungeonTower) addBuilding("round tower", 20, 20);
		
		players.addPlayer(tower.owner());
	}
	
	public void loadMap(MapJson json) {
		for(MapChunkJson mcj : json.chunks) {
			MapChunk chunk = map.getChunk(mcj.offsetX, mcj.offsetZ);
			int squareIndex = 0;
			for(MapSquareJson msj : mcj.squares) {
				MapSquare square = chunk.squareAtIndex(squareIndex);
				if(msj.building != null) {
					addBuilding(msj.building.defName, square.x(), square.z());
				}
				
				squareIndex++;
			}
		}
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
	
	public DefManager def() {
		return def;
	}
	
	public SKAssetManager assets() {
		return assets;
	}
}

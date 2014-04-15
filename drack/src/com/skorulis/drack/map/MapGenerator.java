package com.skorulis.drack.map;
import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.building.CommandCentre;
import com.skorulis.drack.building.Mine;
import com.skorulis.drack.def.DefManager;
import com.skorulis.gdx.SKAssetManager;

public class MapGenerator {

	private GameMap map;
	
	public MapGenerator(int width , int depth, SKAssetManager assets,DefManager def) {
		map = new GameMap(width, depth, assets);
		
		MapSquare square = map.squareAt(new Vector3(5, 0, 5));
		CommandCentre b = (CommandCentre) def.getBuilding("command").create(assets);
		
		square.setBuilding(b);
		b.generateField(b.fieldSize(), map);
		
		Mine mine = (Mine) def.getBuilding("mine").create(assets);
		mine.addResource(def.getResource("iron"), 1);
		
		map.squareAt(11,3).setBuilding(mine);
		map.squareAt(2,5).setBuilding(def.getBuilding("tree").create(assets));
		map.squareAt(12,3).setBuilding(def.getBuilding("tree").create(assets));
		map.squareAt(8,15).setBuilding(def.getBuilding("tree").create(assets));
		
	}
	
	public GameMap map() {
		return map;
	}
	
}

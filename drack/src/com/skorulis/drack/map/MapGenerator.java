package com.skorulis.drack.map;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.building.CommandCentre;
import com.skorulis.drack.building.Tree;

public class MapGenerator {

	private GameMap map;
	
	public MapGenerator(int width , int depth, AssetManager assets) {
		map = new GameMap(width, depth, assets);
		
		MapSquare square = map.squareAt(new Vector3(5, 0, 5));
		CommandCentre b = new CommandCentre(assets);
		
		square.setBuilding(b);
		b.generateField(b.fieldSize(), map);
		
		map.squareAt(2,5).setBuilding(new Tree(assets));
		map.squareAt(12,3).setBuilding(new Tree(assets));
		map.squareAt(8,15).setBuilding(new Tree(assets));
		
	}
	
	public GameMap map() {
		return map;
	}
	
}

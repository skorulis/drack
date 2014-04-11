package com.skorulis.drack.building;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.map.ForceField;
import com.skorulis.drack.map.GameMap;
import com.skorulis.drack.map.MapSquare;

public class CommandCentre extends Building {

	private int fieldSize = 3;
	private MapSquare[][] forceField;
	
	public CommandCentre(AssetManager assets) {
		super(assets);
		
	}
	
	public void generateField(int size, GameMap map) {
		forceField = map.getSquareAround(absTransform().getTranslation(new Vector3()), size);
		for(int i = 0; i < 2 * size + 1; ++i) {
			for(int j = 0; j < 2 * size + 1; ++j) {
				if(forceField[i][j] != null) {
					ForceField field = new ForceField();
					boolean hasTop = i > 0 && forceField[i-1][j] != null;
					boolean hasBottom = i < 2*size && forceField[i+1][j] != null;
					boolean hasLeft = j > 0 && forceField[i][j-1] != null;
					boolean hasRight = j < 2*size && forceField[i][j+1] != null;
					field.setAdjacent(hasTop, hasRight, hasBottom, hasLeft);
					field.buildModel(map.assets());
					forceField[i][j].setForceField(field);
				}
			}
		}
	}
	
	public int fieldSize() {
		return fieldSize;
	}
	
}

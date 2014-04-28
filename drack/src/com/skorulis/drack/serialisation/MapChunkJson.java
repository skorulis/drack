package com.skorulis.drack.serialisation;

import java.util.ArrayList;

public class MapChunkJson {

	public int offsetX;
	public int offsetZ;
	
	public ArrayList<MapSquareJson> squares;
	
	public MapChunkJson() {
		squares = new ArrayList<MapSquareJson>();
	}
	
}

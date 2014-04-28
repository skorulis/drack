package com.skorulis.drack.serialisation;

import java.util.ArrayList;

public class MapJson {

	public int width;
	public int depth;
	
	public ArrayList<MapChunkJson> chunks;
	
	public MapJson() {
		chunks = new ArrayList<MapChunkJson>();
	}
	
}

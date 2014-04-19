package com.skorulis.drack.def.unit;

import com.badlogic.gdx.math.Vector3;

public class HullPointDef {

	public enum HullPointType {
		SMALL (0.2f);
		
		
		private final float size;
		private HullPointType(float size) {
			this.size = size;
		}
		
		public float size() { return size;}
	}
	
	public Vector3 loc;
	public Vector3 normal;
	public HullPointType type;
	
	public HullPointDef(Vector3 loc, Vector3 normal, HullPointType type) {
		this.loc = loc;
		this.normal = normal;
		this.type = type;
	}
	
}

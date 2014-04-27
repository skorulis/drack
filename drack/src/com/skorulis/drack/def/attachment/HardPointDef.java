package com.skorulis.drack.def.attachment;

import com.badlogic.gdx.math.Vector3;

public class HardPointDef {

	public enum HullPointType {
		SMALL (0.2f);
		
		
		private final float size;
		private HullPointType(float size) {
			this.size = size;
		}
		
		public float size() { return size;}
	}
	
	public Vector3 loc;
	public float rotation;
	public HullPointType type;
	
	public HardPointDef(Vector3 loc, float rotation, HullPointType type) {
		this.loc = loc;
		this.rotation = rotation;
		this.type = type;
	}
	
}

package com.skorulis.drack.effects;

import com.badlogic.gdx.math.Vector3;

public interface Effect2D {

	public boolean isAlive();
	public void setAnchor(Vector3 vec);
	public Vector3 getAnchor();
	
}

package com.skorulis.drack.effects;

import com.badlogic.gdx.graphics.Camera;

public interface Effect2DMovement {

	public Effect2D effect();
	public void setEffect(Effect2D effect);
	public void updatePosition(Camera cam, float delta);
	
}

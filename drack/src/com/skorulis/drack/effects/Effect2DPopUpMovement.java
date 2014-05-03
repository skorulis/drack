package com.skorulis.drack.effects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

public class Effect2DPopUpMovement implements Effect2DMovement{
	
	private Effect2D effect;
	private float duration;
	private float time;
	
	public Effect2DPopUpMovement(float duration) {
		this.duration = duration;
		this.time = 0;
	}
	
	public Effect2D effect() {
		return effect;
	}
	
	public void setEffect(Effect2D effect) {
		this.effect = effect;
	}

	@Override
	public void updatePosition(Camera cam, float delta) {
		this.time ++;
		Vector3 screenPos = cam.project(effect.getAnchor().cpy());
		screenPos.y += (time / duration) * 2;
		
		screenPos.x += effect.offset().x;
		screenPos.y += effect.offset().y;
		
		effect.actor().setPosition(screenPos.x, screenPos.y);
	}
	
}

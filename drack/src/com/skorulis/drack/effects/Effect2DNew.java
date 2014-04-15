package com.skorulis.drack.effects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Effect2DNew {

	private Actor actor;
	private Vector3 anchor;
	private float life;
	
	public Effect2DNew(Actor actor) {
		this.actor = actor;
		this.life = 5;
	}
	
	public void update(float delta) {
		life -= delta;
	}
	
	public boolean isAlive() {
		return life > 0;
	}
	
	public void setAnchor(Vector3 vec) {
		this.anchor = vec;
	}
	
	public Vector3 getAnchor() {
		return anchor;
	}
	
	public Actor actor() {
		return actor;
	}
	
	public void position(Camera camera) {
		Vector3 screenPos = camera.project(anchor.cpy());
		actor.setX(screenPos.x);
		actor.setY(screenPos.y);
	}
	
}

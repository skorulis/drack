package com.skorulis.drack.effects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.skorulis.scene.SceneNode;

public class Effect2D {

	private Actor actor;
	private SceneNode nodeAnchor;
	private Vector3 anchor;
	private float life;
	private Effect2DMovement movement;
	
	public Effect2D(Actor actor, float life) {
		this.actor = actor;
		this.life = life;
	}
	
	public void update(float delta) {
		life -= delta;
	}
	
	public float life() {
		return life;
	}
	
	public boolean isAlive() {
		if(nodeAnchor != null) {
			if(!nodeAnchor.isAlive()) {
				return false;
			}
		}
		return life > 0;
	}
	
	public void setAnchor(Vector3 vec) {
		this.anchor = vec;
	}
	
	public Vector3 getAnchor() {
		return anchor;
	}
	
	public void setNodeAnchor(SceneNode node) {
		this.nodeAnchor = node;
	}
	
	public Actor actor() {
		return actor;
	}
	
	public void position(Camera camera) {
		if(nodeAnchor != null) {
			anchor = nodeAnchor.absTransform().getTranslation(new Vector3());
		}
		Vector3 screenPos = camera.project(anchor.cpy());
		actor.setX(screenPos.x);
		actor.setY(screenPos.y);
	}
	
	public void setMovement(Effect2DMovement movement) {
		this.movement = movement;
		movement.setEffect(this);
	}
	
	public Effect2DMovement movement() {
		return movement;
	}
	
}

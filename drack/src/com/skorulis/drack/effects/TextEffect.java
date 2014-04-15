package com.skorulis.drack.effects;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class TextEffect extends Label implements Effect2D {

	private float life;
	private Vector3 anchor;
	
	public TextEffect(CharSequence text, Skin skin) {
		super(text, skin);
		life = 5;
	}
	
	public void act(float delta) {
		super.act(delta);
		life -= delta;
	}

	@Override
	public boolean isAlive() {
		return life > 0;
	}
	
	public void setAnchor(Vector3 anchor) {
		this.anchor = anchor;
	}
	
	public Vector3 getAnchor() {
		return this.anchor;
	}
	
}

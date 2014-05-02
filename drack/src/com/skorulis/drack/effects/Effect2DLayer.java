package com.skorulis.drack.effects;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.skorulis.drack.game.IsoPerspectiveCamera;
import com.skorulis.drack.ui.effects.HealthBar;
import com.skorulis.drack.unit.Unit;

public class Effect2DLayer {
	
	private final Stage stage;
	private final Skin skin;
	private IsoPerspectiveCamera camera;
	private ArrayList<Effect2D> effects;
	
	public Effect2DLayer(Skin skin) {
		this.skin = skin;
		stage = new Stage(new ScreenViewport());
		this.effects = new ArrayList<Effect2D>();
	}
	
	public void update(float delta) {
		Iterator<Effect2D> it = effects.iterator();
		while(it.hasNext()) {
			Effect2D effect = it.next();
			effect.update(delta);
			if(!effect.isAlive()) {
				it.remove();
				effect.actor().remove();
			} else {
				if(effect.movement() != null) {
					effect.movement().updatePosition(this.camera.cam(),delta);
				} else {
					effect.position(camera.cam());
				}
			}
		}
		
		stage.act(delta);
	}
	
	public Stage stage() {
		return stage;
	}
	
	public void addTextEffect(Vector3 worldPos, String text) {
		addTextEffect(worldPos, text, 0);
	}
	
	public void addTextEffect(Vector3 worldPos, String text,int offset) {
		Label label = new Label(text,this.skin);
		Effect2D effect = new Effect2D(label,2);
		effect.setLineOffset(offset);
		effect.setAnchor(worldPos);
		
		addEffect(effect);
		
		AlphaAction act = new AlphaAction();
		act.setDuration(effect.life());
		act.setAlpha(0.0f);
		effect.actor().addAction(act);
		
		effect.setMovement(new Effect2DPopUpMovement(effect.life()));
	}
	
	public HealthBar createHealthBar(Unit unit) {
		HealthBar hb = new HealthBar(skin);
		Effect2D effect = new Effect2D(hb, Integer.MAX_VALUE);
		effect.setNodeAnchor(unit);
		
		addEffect(effect);
		return hb;
	}
	
	private void addEffect(Effect2D effect) {
		effects.add(effect);
		stage.addActor(effect.actor());
		if(camera != null) {
			effect.position(camera.cam());
		}
	}
	
	public void setCam(IsoPerspectiveCamera cam) {
		this.camera = cam;
	}
	
}

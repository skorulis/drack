package com.skorulis.drack.effects;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.skorulis.drack.IsoPerspectiveCamera;

public class GameInfoLayer {

	private final Stage stage;
	private final Skin skin;
	private final IsoPerspectiveCamera camera;
	private ArrayList<Effect2D> effects;
	
	public GameInfoLayer(Skin skin,IsoPerspectiveCamera camera) {
		this.skin = skin;
		this.camera = camera;
		stage = new Stage();
		this.effects = new ArrayList<Effect2D>();
	}
	
	public void update(float delta) {
		stage.act(delta);
		for(int i = effects.size() - 1; i >= 0; --i) {
			if(!effects.get(i).isAlive()) {
				Actor actor = (Actor) effects.remove(i);
				actor.remove();
				System.out.println("Removing effect");
			}
		}
	}
	
	public Stage stage() {
		return stage;
	}
	
	public void addTextEffect(Vector3 worldPos, String text) {
		TextEffect effect = new TextEffect(text, this.skin);
		effect.setAnchor(worldPos);
		placeEffect(effect);
		stage.addActor(effect);
		effects.add(effect);
		
		MoveByAction action = new MoveByAction();
		action.setDuration(5);
		action.setAmount(0, 100);
		effect.addAction(action);
	}
	
	private void placeEffect(Effect2D effect) {
		Vector3 screenPos = camera.cam().project(effect.getAnchor());
		((Actor)effect).setX(screenPos.x);
		((Actor)effect).setY(screenPos.y);
	}
	
}

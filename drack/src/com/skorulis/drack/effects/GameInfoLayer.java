package com.skorulis.drack.effects;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.skorulis.drack.IsoPerspectiveCamera;

public class GameInfoLayer {

	private final Stage stage;
	private final Skin skin;
	private final IsoPerspectiveCamera camera;
	private ArrayList<Effect2DNew> effects;
	
	public GameInfoLayer(Skin skin,IsoPerspectiveCamera camera) {
		this.skin = skin;
		this.camera = camera;
		stage = new Stage();
		this.effects = new ArrayList<Effect2DNew>();
	}
	
	public void update(float delta) {
		stage.act(delta);
		for(int i = effects.size() - 1; i >= 0; --i) {
			Effect2DNew effect = effects.get(i);
			effect.update(delta);
			if(!effect.isAlive()) {
				effects.remove(i);
				effect.actor().remove();
				System.out.println("Removing effect");
			} else {
				effect.position(camera.cam());
			}
		}
	}
	
	public Stage stage() {
		return stage;
	}
	
	public void addTextEffect(Vector3 worldPos, String text) {
		Label label = new Label(text,this.skin);
		Effect2DNew effect = new Effect2DNew(label);
		effect.setAnchor(worldPos);
		
		effect.position(camera.cam());
		effects.add(effect);
		stage.addActor(effect.actor());
		
	}
	
}

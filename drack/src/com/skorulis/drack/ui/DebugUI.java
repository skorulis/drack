package com.skorulis.drack.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

public class DebugUI extends WidgetGroup {

	private Label label;
	
	public DebugUI(Skin skin) {
		label = new Label("TEST",skin);
		this.addActor(label);
	}
	
	public void act(float delta) {
		super.act(delta);
		label.setText("fps: " + Gdx.graphics.getFramesPerSecond());
	}

}

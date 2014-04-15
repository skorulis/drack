package com.skorulis.drack.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

public class DebugUI extends WidgetGroup {

	private Label label;
	
	public DebugUI(StyleManager style) {
		label = new Label("TEST",style.defaultSkin());
		this.addActor(label);
	}
	
	public void act(float delta) {
		super.act(delta);
		label.setText("fps: " + Gdx.graphics.getFramesPerSecond());
	}

}

package com.skorulis.drack.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class UIManager {

	private Stage stage;
	private Skin skin;
	private DebugUI debugUI;
	
	public UIManager() {
		stage = new Stage(new ScreenViewport());
		skin = new Skin(Gdx.files.internal("data/skin/uiskin.json"));
		debugUI = new DebugUI(skin);
		
		stage.addActor(debugUI);
	}
	
	public Stage stage() {
		return stage;
	}
	
}

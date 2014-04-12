package com.skorulis.drack.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class UIManager {

	private Stage stage;
	private Skin skin;
	private DebugUI debugUI;
	private PlayerUI playerUI;
	
	public UIManager(AssetManager assets) {
		stage = new Stage(new ScreenViewport());
		skin = new Skin(Gdx.files.internal("data/skin/uiskin.json"));
		debugUI = new DebugUI(skin);
		playerUI = new PlayerUI(skin,assets);

		stage.addActor(playerUI);
		
		stage.addActor(debugUI);
		resized(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	public Stage stage() {
		return stage;
	}
	
	public void resized(int width, int height) {
		stage.getViewport().update(width, height, true);
	}
	
}

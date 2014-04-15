package com.skorulis.drack.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class StyleManager {

	private final Skin defaultSkin;
	private final Skin gameSkin;
	
	public StyleManager() {
		defaultSkin = new Skin(Gdx.files.internal("data/skin/uiskin.json"));
		gameSkin = new Skin(Gdx.files.internal("data/game_skin/ui.json")); 
	}
	
	public Skin defaultSkin() {
		return defaultSkin;
	}
	
	public Skin gameSkin() {
		return gameSkin;
	}
	
}

package com.skorulis.drack.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

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
	
	public ImageButtonStyle createImageStyle(String imageName) {
		ButtonStyle buttonStyle = gameSkin.get("default",ButtonStyle.class);
		ImageButtonStyle ibs = new ImageButtonStyle(buttonStyle);
		ibs.imageUp = ibs.imageDown = new TextureRegionDrawable(gameSkin.getRegion(imageName));
		return ibs;
	}
	
}

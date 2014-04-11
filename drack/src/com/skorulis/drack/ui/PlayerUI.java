package com.skorulis.drack.ui;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PlayerUI extends Group {
	
	private ImageButton buildButton;
	
	public PlayerUI(Skin skin,AssetManager assets) {
		TextureAtlas atlas = assets.get("data/ui.png.atlas", TextureAtlas.class);
		AtlasRegion ar = atlas.findRegion("hammer");
		buildButton = new ImageButton(new TextureRegionDrawable(ar));
		this.addActor(buildButton);
	}
	
	public void resized(int width, int height) {
		buildButton.setX(width - buildButton.getWidth());
	}
	
}

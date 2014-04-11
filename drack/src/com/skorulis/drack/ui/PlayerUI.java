package com.skorulis.drack.ui;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PlayerUI extends WidgetGroup {
	
	private ImageButton buildButton;
	
	public PlayerUI(Skin skin,AssetManager assets) {
		TextureAtlas atlas = assets.get("data/ui.png.atlas", TextureAtlas.class);
		AtlasRegion ar = atlas.findRegion("hammer");
		buildButton = new ImageButton(new TextureRegionDrawable(ar));
		this.addActor(buildButton);
		this.setFillParent(true);
	}
	
	public void layout() {
		buildButton.setX(getWidth() - buildButton.getWidth());
	}
	
	
	
}

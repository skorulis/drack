package com.skorulis.drack.ui;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PlayerUI extends WidgetGroup {
	
	private ImageButton buildButton;
	private BuildMenuDialog buildDialog;
	
	public PlayerUI(final Skin skin,AssetManager assets) {
		TextureAtlas atlas = assets.get("data/ui.png.atlas", TextureAtlas.class);
		AtlasRegion ar = atlas.findRegion("hammer");
		buildButton = new ImageButton(new TextureRegionDrawable(ar));
		this.addActor(buildButton);
		this.setFillParent(true);
		
		buildButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				buildDialog = new BuildMenuDialog(skin);
				addActor(buildDialog);
			}
		});
	}
	
	public void layout() {
		buildButton.setX(getWidth() - buildButton.getWidth());
		if(buildDialog != null) {
			//buildDialog.setBounds(0, 0, this.getWidth(), this.getHeight());
		}
	}
	
	
	
}

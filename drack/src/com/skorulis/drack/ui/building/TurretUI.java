package com.skorulis.drack.ui.building;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.skorulis.drack.ui.UIManager;

public class TurretUI extends BuildingUI {

	private TextButton editButton;
	
	public void init(Skin skin, UIManager ui) {
		super.init(skin,ui);
		editButton = new TextButton("edit", skin);
		
		editButton.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				unitPressed();
			}
		});
		
		buttonGroup.addActor(editButton);
		layout();
	}
	
	private void unitPressed() {
		
	}
	
}

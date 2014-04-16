package com.skorulis.drack.ui.building;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.skorulis.drack.ui.UIManager;

public class BarracksUI extends BuildingUI {

	private TextButton hireButton;
	
	public void init(Skin skin, UIManager ui) {
		super.init(skin,ui);
		hireButton = new TextButton("hire", skin);
		
		hireButton.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				hirePressed();
			}
		});
		
		this.addActor(hireButton);
	}
	
	private void hirePressed() {
		System.out.println("Hire pressed");
	}
	
	public void layout() {
		super.layout();
		helper.centreChildX(hireButton);
	}
	
}

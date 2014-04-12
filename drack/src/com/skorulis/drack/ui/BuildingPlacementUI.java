package com.skorulis.drack.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class BuildingPlacementUI extends WidgetGroup {

	private TextButton tickButton;
	private TextButton cancelButton;
	
	public BuildingPlacementUI(final UIManager ui, final PlayerUI playerUI) {
		tickButton = new TextButton("CONFIRM",ui.skin());
		cancelButton = new TextButton("CANCEL",ui.skin());
		
		addActor(tickButton);
		addActor(cancelButton);
		
		tickButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				ui.game().placeBuilding();
				playerUI.placementFinished();
			}
		});
		
		cancelButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				ui.game().setPlacingBuilding(null);
				playerUI.placementFinished();
			}
		});
	}
	
	public void layout() {
		tickButton.setBounds(100, 100, tickButton.getPrefWidth(), tickButton.getPrefHeight());
		cancelButton.setBounds(200, 100, cancelButton.getPrefWidth(), cancelButton.getPrefHeight());
	}
	
	public float getPrefHeight() {
		return 200;
	}
	
	public float getPrefWidth() {
		return 300;
	}
	
	
	
}

package com.skorulis.drack.ui.building;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.skorulis.drack.building.Building;
import com.skorulis.drack.ui.PlayerUI;
import com.skorulis.drack.ui.UIManager;

public class BuildingPlacementUI extends WidgetGroup {

	private final TextButton tickButton;
	private final TextButton cancelButton;
	private final Building building;
	private final UIManager ui;
	
	public BuildingPlacementUI(final UIManager ui, final PlayerUI playerUI,Building building) {
		tickButton = new TextButton("CONFIRM",ui.style().defaultSkin());
		cancelButton = new TextButton("CANCEL",ui.style().defaultSkin());
		this.building = building;
		this.ui = ui;
		
		addActor(tickButton);
		addActor(cancelButton);
		
		tickButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				if(ui.game().placingBuilding().canPlace()) {
					ui.game().placeBuilding();
					playerUI.placementFinished();
				}
			}
		});
		
		cancelButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				ui.game().clearPlacingBuilding();
				playerUI.placementFinished();
			}
		});
	}
	
	public void layout() {
		tickButton.setBounds(100, 100, tickButton.getPrefWidth(), tickButton.getPrefHeight());
		cancelButton.setBounds(200, 100, cancelButton.getPrefWidth(), cancelButton.getPrefHeight());
	}
	
	public void act(float delta) {
		Vector3 pos = building.absTransform().getTranslation(new Vector3());
		pos = ui.camera().cam().project(pos);
		this.setX(pos.x - getPrefWidth()/2);
		this.setY(pos.y - getPrefHeight()/2);
	}
	
	public float getPrefHeight() {
		return 200;
	}
	
	public float getPrefWidth() {
		return 300;
	}
	
	
	
}

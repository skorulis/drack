package com.skorulis.drack.ui.building;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.skorulis.drack.actor.building.Building;
import com.skorulis.drack.ui.PlayerUI;
import com.skorulis.drack.ui.UIManager;
import com.skorulis.gdx.ui.LayoutHelper;

public class BuildingPlacementUI extends WidgetGroup {

	private final ImageButton tickButton;
	private final ImageButton cancelButton;
	private final Building building;
	private final UIManager ui;
	private LayoutHelper helper;
	
	public BuildingPlacementUI(final UIManager ui, final PlayerUI playerUI,Building building) {
		helper = new LayoutHelper(this);
		tickButton = new ImageButton(ui.style().createImageStyle("check"));
		cancelButton = new ImageButton(ui.style().createImageStyle("cross_white"));
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
		//tickButton.setBounds(100, 100, tickButton.getPrefWidth(), tickButton.getPrefHeight());
		helper.alignRight(cancelButton, 0);
	}
	
	public void act(float delta) {
		Vector3 pos = building.absTransform().getTranslation(new Vector3());
		pos = ui.camera().cam().project(pos);
		this.setX(pos.x - getPrefWidth()/2);
		this.setY(pos.y - getPrefHeight()/2);
	}
	
	public float getPrefHeight() {
		return 150;
	}
	
	public float getPrefWidth() {
		return 150;
	}
	
	
	
}

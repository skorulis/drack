package com.skorulis.drack.ui;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.skorulis.drack.building.Building;

public class PlayerUI extends WidgetGroup {
	
	private ImageButton buildButton;
	private ImageButton inventoryButton;
	private BuildMenuDialog buildDialog;
	private BuildingPlacementUI buildingPlacement;
	private InventoryDialog inventoryDialog;
	private UIManager ui;
	
	public PlayerUI(UIManager uiManager) {
		this.ui = uiManager;
		TextureAtlas atlas = uiManager.assets().get("data/ui.png.atlas", TextureAtlas.class);
		buildButton = new ImageButton(new TextureRegionDrawable(atlas.findRegion("hammer")));
		this.addActor(buildButton);
		
		
		buildButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				buildClicked();
			}
		});
		
		inventoryButton = new ImageButton(new TextureRegionDrawable(atlas.findRegion("inventory")));
		this.addActor(inventoryButton);
		
		inventoryButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				inventoryClicked();
			}
		});
		
		this.setFillParent(true);
	}
	
	private void inventoryClicked() {
		inventoryDialog = new InventoryDialog(ui.game().playerAvatar(), ui.skin());
		addActor(inventoryDialog);
	}
	
	private void buildClicked() {
		buildDialog = new BuildMenuDialog(this, ui.skin(),ui);
		addActor(buildDialog);
	}
	
	public void layout() {
		buildButton.setX(getWidth() - buildButton.getWidth());
		inventoryButton.setX(buildButton.getX());
		inventoryButton.setY(buildButton.getTop());
		if(buildDialog != null) {
			//buildDialog.setBounds(0, 0, this.getWidth(), this.getHeight());
		}
		if(buildingPlacement != null) {
			buildingPlacement.setBounds(200, 200, buildingPlacement.getPrefWidth(), buildingPlacement.getPrefHeight());
		}
	}
	
	public void showPlacementUI(Building building) {
		buildingPlacement = new BuildingPlacementUI(ui, this);
		this.addActor(buildingPlacement);
	}
	
	public void placementFinished() {
		this.removeActor(buildingPlacement);
		this.buildingPlacement = null;
	}
	
}

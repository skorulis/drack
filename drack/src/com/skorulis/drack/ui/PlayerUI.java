package com.skorulis.drack.ui;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.skorulis.drack.building.Building;
import com.skorulis.drack.ui.building.BuildMenuDialog;
import com.skorulis.drack.ui.building.BuildingPlacementUI;
import com.skorulis.drack.ui.inventory.InventoryDialog;

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
		
		TextureRegionDrawable bgUp = new TextureRegionDrawable(atlas.findRegion("red_button1"));
		TextureRegionDrawable bgDown = new TextureRegionDrawable(atlas.findRegion("red_dark"));
		TextureRegionDrawable hammer = new TextureRegionDrawable(atlas.findRegion("hammer"));
		
		ImageButtonStyle ibs = new ImageButtonStyle();
		ibs.down = bgDown;
		ibs.up = bgUp;
		ibs.imageUp = hammer;
		
		buildButton = new ImageButton(ibs);
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
		buildingPlacement = new BuildingPlacementUI(ui, this,building);
		this.addActor(buildingPlacement);
	}
	
	public void placementFinished() {
		this.removeActor(buildingPlacement);
		this.buildingPlacement = null;
	}
	
}

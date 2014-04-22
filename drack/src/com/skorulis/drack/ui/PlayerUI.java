package com.skorulis.drack.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.skorulis.drack.building.Building;
import com.skorulis.drack.ui.building.BuildMenuDialog;
import com.skorulis.drack.ui.building.BuildingPlacementUI;
import com.skorulis.drack.ui.inventory.InventoryDialog;
import com.skorulis.gdx.ui.LayoutHelper;

public class PlayerUI extends WidgetGroup {
	
	private ImageButton buildButton;
	private ImageButton inventoryButton;
	private BuildMenuDialog buildDialog;
	private BuildingPlacementUI buildingPlacement;
	private InventoryDialog inventoryDialog;
	private UIManager ui;
	private final LayoutHelper helper;
	private Image bgImage;
	
	public PlayerUI(UIManager uiManager) {
		this.ui = uiManager;
		this.helper = new LayoutHelper(this);
		
		this.bgImage = new Image(uiManager.style().gameSkin().getDrawable("off_white"));
		this.bgImage.setFillParent(true);
		addActor(bgImage);
		
		buildButton = new ImageButton(uiManager.style().createImageStyle("hammer_dark"));
		this.addActor(buildButton);
		
		
		buildButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				buildClicked();
			}
		});
		
		inventoryButton = new ImageButton(uiManager.style().createImageStyle("inventory_dark"));
		this.addActor(inventoryButton);
		
		inventoryButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				inventoryClicked();
			}
		});
		
		this.setWidth(100);
		this.setHeight(176);
	}
	
	private void inventoryClicked() {
		inventoryDialog = new InventoryDialog(ui.logic().player(), ui);
		ui.showDialog(inventoryDialog);
	}
	
	private void buildClicked() {
		buildDialog = new BuildMenuDialog(this, ui.style().gameSkin(),ui);
		ui.showDialog(buildDialog);
	}
	
	public void layout() {
		helper.centreChildX(buildButton);
		helper.centreChildX(inventoryButton);
		helper.alignBottom(buildButton, 16);
		
		helper.alignTopOf(inventoryButton, buildButton, 16);
		if(buildDialog != null) {
			//buildDialog.setBounds(0, 0, this.getWidth(), this.getHeight());
		}
		if(buildingPlacement != null) {
			buildingPlacement.setBounds(200, 200, buildingPlacement.getPrefWidth(), buildingPlacement.getPrefHeight());
		}
	}
	
	public void showPlacementUI(Building building) {
		buildingPlacement = new BuildingPlacementUI(ui, this,building);
		this.ui.addActor(buildingPlacement);
	}
	
	public void placementFinished() {
		this.ui.removeActor(buildingPlacement);
		this.buildingPlacement = null;
	}
	
}

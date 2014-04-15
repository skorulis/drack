package com.skorulis.drack.ui;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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
	
	public PlayerUI(UIManager uiManager) {
		this.ui = uiManager;
		this.helper = new LayoutHelper(this);
		TextureAtlas atlas = uiManager.assets().get("data/ui.png.atlas", TextureAtlas.class);
		
		ButtonStyle buttonStyle = uiManager.style().gameSkin().get("default",ButtonStyle.class);
		
		
		TextureRegionDrawable bgUp = new TextureRegionDrawable(atlas.findRegion("red_light2"));
		TextureRegionDrawable bgDown = new TextureRegionDrawable(atlas.findRegion("red_dark2"));
		TextureRegionDrawable hammer = new TextureRegionDrawable(atlas.findRegion("hammer"));
		TextureRegionDrawable inventory = new TextureRegionDrawable(atlas.findRegion("inventory"));
		
		buildButton = new ImageButton(new ImageButtonStyle(bgUp, bgDown,null, hammer, null, null));
		this.addActor(buildButton);
		
		
		buildButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				buildClicked();
			}
		});
		
		inventoryButton = new ImageButton(new ImageButtonStyle(bgUp, bgDown,null, inventory, null, null));
		this.addActor(inventoryButton);
		
		inventoryButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				inventoryClicked();
			}
		});
		
		this.setFillParent(true);
	}
	
	private void inventoryClicked() {
		inventoryDialog = new InventoryDialog(ui.game().playerAvatar(), ui.style().defaultSkin());
		addActor(inventoryDialog);
	}
	
	private void buildClicked() {
		buildDialog = new BuildMenuDialog(this, ui.style().defaultSkin(),ui);
		addActor(buildDialog);
	}
	
	public void layout() {
		helper.alignBottom(buildButton, 8);
		helper.alignRight(buildButton, 8);
		helper.alignRight(inventoryButton, 8);
		helper.alignTopOf(inventoryButton, buildButton, 8);
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

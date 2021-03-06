package com.skorulis.drack.ui.building;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.skorulis.drack.def.building.BuildingDef;
import com.skorulis.drack.ui.ModalDialog;
import com.skorulis.drack.ui.PlayerUI;
import com.skorulis.drack.ui.UIManager;

public class BuildMenuDialog extends ModalDialog {
	
	private ScrollPane scrollPane;
	private HorizontalGroup horizGroup;
	private UIManager ui;
	
	public BuildMenuDialog(final PlayerUI playerUI, Skin skin, UIManager ui) {
		super(ui);
		this.ui = ui;
		horizGroup = new HorizontalGroup();
		
		horizGroup.space(10);
		ArrayList<BuildingDef> buildings = ui.def().buildableBuildings();
		for(final BuildingDef bd : buildings) {
			Texture texture = ui.assets().get(bd.iconName(), Texture.class);
			
			ImageButton button = new ImageButton(ui.style().createImageStyle(texture,true));
			horizGroup.addActor(button);
			button.addListener(new ClickListener() {
				public void clicked(InputEvent event, float x, float y) {
					build(playerUI, bd);
					close();
				}
			});
			
		}
		scrollPane = new ScrollPane(horizGroup);
		this.setContent(scrollPane);
	}
	
	public void build(PlayerUI playerUI, BuildingDef bd) {
		Ray ray = ui.camera().centreRay();
		Vector3 loc = ui.game().map().groundIntersection(ray);
		ui.game().setPlacingBuilding(bd,loc);
		playerUI.showPlacementUI(ui.game().placingBuilding().building());
	}
	
	public void layout() {
		scrollPane.setWidth(scrollPane.getPrefWidth());
		scrollPane.setHeight(scrollPane.getPrefHeight());
		super.layout();
	}

}

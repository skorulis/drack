package com.skorulis.drack.ui;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.skorulis.drack.building.Building;
import com.skorulis.drack.def.BuildingDef;

public class BuildMenuDialog extends ModalDialog {
	
	private HorizontalGroup horizGroup;
	private UIManager ui;
	
	public BuildMenuDialog(final PlayerUI playerUI, Skin skin, UIManager ui) {
		super(skin);
		this.ui = ui;
		horizGroup = new HorizontalGroup();
		horizGroup.space(10);
		ArrayList<BuildingDef> buildings = ui.def().buildableBuildings();
		for(final BuildingDef bd : buildings) {
			TextButton button = new TextButton(bd.name(),skin);
			horizGroup.addActor(button);
			button.addListener(new ClickListener() {
				public void clicked(InputEvent event, float x, float y) {
					System.out.println("CLICK " + bd.name());
					build(playerUI, bd);
					close();
				}
			});
			
		}
		super.content = horizGroup;
		this.addActor(horizGroup);
	}
	
	public void build(PlayerUI playerUI, BuildingDef bd) {
		Building building = bd.create(ui.assets());
		ui.game().setPlacingBuilding(building);
		playerUI.showPlacementUI(building);
	}

}

package com.skorulis.drack.ui.building;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.skorulis.drack.building.TurretBuilding;
import com.skorulis.drack.ui.UIManager;
import com.skorulis.drack.ui.component.CompositeEditorDialog;

public class TurretUI extends BuildingUI {

	private TextButton editButton;
	
	public void init(Skin skin, UIManager ui) {
		super.init(skin,ui);
		editButton = new TextButton("edit", skin);
		
		editButton.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				unitPressed();
			}
		});
		
		buttonGroup.addActor(editButton);
		layout();
	}
	
	public TurretBuilding turret() {
		return (TurretBuilding) this.building;
	}
	
	private void unitPressed() {
		CompositeEditorDialog edit = new CompositeEditorDialog(ui,turret());
		ui.showDialog(edit);
	}
	
}

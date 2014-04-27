package com.skorulis.drack.ui.building;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.skorulis.drack.ui.UIManager;
import com.skorulis.drack.ui.component.CompositeEditorDialog;
import com.skorulis.drack.unit.composite.CompositeUnit;

public class CommandUI extends BuildingUI {

	private TextButton unitButton;
	
	public void init(Skin skin, UIManager ui) {
		super.init(skin,ui);
		unitButton = new TextButton("unit", skin);
		
		unitButton.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				unitPressed();
			}
		});
		
		buttonGroup.addActor(unitButton);
		layout();
	}
	
	private void unitPressed() {
		CompositeUnit unit = (CompositeUnit) ui.logic().player().controllUnit();
		CompositeEditorDialog edit = new CompositeEditorDialog(ui,unit);
		ui.showDialog(edit);
	}
	
	public void layout() {
		super.layout();
	}
	
}

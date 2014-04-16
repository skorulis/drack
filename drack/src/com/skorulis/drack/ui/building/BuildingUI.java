package com.skorulis.drack.ui.building;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.skorulis.drack.building.Building;
import com.skorulis.drack.ui.UIManager;
import com.skorulis.gdx.ui.LayoutHelper;

public class BuildingUI extends WidgetGroup {
	
	protected UIManager ui;
	protected Label nameLabel;
	protected final LayoutHelper helper;
	protected Building building;
	protected HorizontalGroup buttonGroup;
	
	public BuildingUI() {
		helper = new LayoutHelper(this);
		this.setFillParent(true);
		this.buttonGroup = new HorizontalGroup();
		this.buttonGroup.space(10);
		this.addActor(buttonGroup);
	}
	
	public void init(Skin skin, UIManager ui) {
		this.ui = ui;
		this.nameLabel = new Label("name",skin);
		this.addActor(nameLabel);
	}
	
	public void layout() {
		helper.centreChildX(nameLabel);
		helper.centreChildX(buttonGroup);
		nameLabel.setY(50);
	}
	
	public void setBuilding(Building building) {
		this.building = building;
		this.nameLabel.setText(building.def().name());
	}
	
}

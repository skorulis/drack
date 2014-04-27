package com.skorulis.drack.ui.building;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.skorulis.drack.building.Building;
import com.skorulis.drack.ui.UIManager;
import com.skorulis.gdx.ui.LayoutHelper;

public class BuildingUI extends Stack {
	
	protected UIManager ui;
	protected Label nameLabel;
	protected final LayoutHelper helper;
	protected Building building;
	protected HorizontalGroup buttonGroup;
	protected VerticalGroup verticalLayout;
	protected Image bgImage;
	
	public BuildingUI() {
		helper = new LayoutHelper(this);
	}
	
	public void init(Skin skin, UIManager ui) {
		this.ui = ui;
		
		bgImage = new Image(skin.getDrawable("off_white_bg"));
		bgImage.setFillParent(true);
		add(bgImage);
		
		verticalLayout = new VerticalGroup();
		verticalLayout.space(8);
		add(verticalLayout);
		
		this.nameLabel = new Label("name",skin);
		verticalLayout.addActor(nameLabel);
		
		this.buttonGroup = new HorizontalGroup();
		this.buttonGroup.space(10);
		verticalLayout.addActor(buttonGroup);
		
	}
	
	public void setBuilding(Building building) {
		this.building = building;
		this.nameLabel.setText(building.def().name());
	}
	
	public void layout() {
		super.layout();
		this.setHeight(120);
		this.setWidth(Math.max(buttonGroup.getWidth(), nameLabel.getWidth()) + 20);
		
	}
	
	
}

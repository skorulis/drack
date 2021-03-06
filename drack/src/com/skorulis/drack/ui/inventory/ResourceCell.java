package com.skorulis.drack.ui.inventory;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.skorulis.drack.resource.ResourceQuantity;

public class ResourceCell extends WidgetGroup {

	private Label nameLabel;
	private Label quantityLabel;
	
	public ResourceCell(ResourceQuantity resource, Skin skin) {
		nameLabel = new Label("", skin);
		quantityLabel = new Label("",skin);
		
		this.addActor(nameLabel);
		this.addActor(quantityLabel);
		if(resource != null) {
			setResource(resource);
		}
	}
	
	public void setResource(ResourceQuantity resource) {
		nameLabel.setText(resource.resource().name());
		quantityLabel.setText(""+resource.quantity());
	}
	
	public void layout() {
		this.nameLabel.setBounds(0, this.getHeight()/2, this.getWidth(), this.getHeight()/2);
		this.quantityLabel.setBounds(0, 0, this.getWidth(), this.getHeight()/2);
	}
	
	public float getPrefHeight() {
		return 100;
	}
	
	public float getPrefWidth() {
		return 100;
	}
	
}

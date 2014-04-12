package com.skorulis.drack.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.skorulis.gdx.LayoutHelper;

public class ModalDialog extends WidgetGroup {

	protected TextButton closeButton;
	protected WidgetGroup content;
	protected LayoutHelper layoutHelper;
	
	public ModalDialog(Skin skin) {
		closeButton = new TextButton("X",skin);
		this.addActor(closeButton);
		this.setFillParent(true);
		this.layoutHelper = new LayoutHelper(this);
		
		closeButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				close();
			}
		});
	}
	
	public void layout() {
		layoutHelper.centreChild(this.content);
		closeButton.setBounds(content.getRight() - closeButton.getWidth(), content.getTop(), closeButton.getPrefWidth(), closeButton.getPrefHeight());
	}
	
	public void close() {
		getParent().removeActor(this);
	}
	
}

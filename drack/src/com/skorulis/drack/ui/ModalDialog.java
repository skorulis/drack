package com.skorulis.drack.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.skorulis.gdx.LayoutHelper;

public class ModalDialog extends WidgetGroup {

	protected TextButton closeButton;
	protected WidgetGroup content;
	protected LayoutHelper layoutHelper;
	protected Image bgImage;
	protected int padding = 10;
	
	public ModalDialog(Skin skin) {
		bgImage = new Image(skin.getDrawable("default-round-down"));
		addActor(bgImage);
		
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
		float height = closeButton.getTop() - content.getY() + padding * 2;
		
		bgImage.setBounds(content.getX() - padding, content.getY() - padding, content.getWidth() + padding * 2, height);
	}
	
	public void close() {
		getParent().removeActor(this);
	}
	
}

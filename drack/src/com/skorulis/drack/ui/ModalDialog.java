package com.skorulis.drack.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.skorulis.gdx.ui.LayoutHelper;

public class ModalDialog extends WidgetGroup {

	protected TextButton closeButton;
	protected WidgetGroup content;
	protected LayoutHelper layoutHelper;
	protected Image bgImage;
	protected Button dimButton;
	protected int padding = 10;
	
	public ModalDialog(Skin skin) {
		bgImage = new Image(skin.getDrawable("off_white"));
		dimButton = new Button(skin.get("dim", ButtonStyle.class));
		addActor(dimButton);
		addActor(bgImage);
		
		closeButton = new TextButton("X",skin);
		this.addActor(closeButton);
		
		this.layoutHelper = new LayoutHelper(this);
		
		closeButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				close();
			}
		});
		dimButton.setFillParent(true);
		this.setFillParent(true);
	}
	
	public void layout() {
		assert content != null;
		layoutHelper.centreChild(this.content,20);
		closeButton.setBounds(content.getRight() - closeButton.getWidth(), content.getTop(), closeButton.getPrefWidth(), closeButton.getPrefHeight());
		float height = closeButton.getTop() - content.getY() + padding * 2;
		
		bgImage.setBounds(content.getX() - padding, content.getY() - padding, content.getWidth() + padding * 2, height);
	}
	
	public void close() {
		getParent().removeActor(this);
	}
	
	public void setContent(WidgetGroup widget) {
		this.content = widget;
		this.addActor(content);
	}
	
}

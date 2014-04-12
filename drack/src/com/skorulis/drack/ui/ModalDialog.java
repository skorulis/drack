package com.skorulis.drack.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
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
	}
	
	public void layout() {
		layoutHelper.centreChild(this.content);
		
	}
	
}

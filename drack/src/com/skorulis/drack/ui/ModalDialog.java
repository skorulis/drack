package com.skorulis.drack.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

public class ModalDialog extends WidgetGroup {

	public TextButton closeButton;
	
	public ModalDialog(Skin skin) {
		closeButton = new TextButton("X",skin);
		this.addActor(closeButton);
	}
	
}

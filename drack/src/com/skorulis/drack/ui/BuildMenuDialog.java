package com.skorulis.drack.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class BuildMenuDialog extends ModalDialog {
	
	private TextButton test;
	
	public BuildMenuDialog(Skin skin) {
		super(skin);
		test = new TextButton("TEST",skin);
		super.content = test;
		this.addActor(test);
	}

}

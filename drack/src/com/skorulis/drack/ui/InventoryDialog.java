package com.skorulis.drack.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;

public class InventoryDialog extends ModalDialog {

	private VerticalGroup list;
	
	public InventoryDialog(Skin skin) {
		super(skin);
		list = new VerticalGroup();
	}

}

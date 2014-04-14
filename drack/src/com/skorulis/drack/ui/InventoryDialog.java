package com.skorulis.drack.ui;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.skorulis.drack.avatar.Avatar;

public class InventoryDialog extends ModalDialog {

	private ScrollPane resourceScrollPane;
	private VerticalGroup resourceList;
	
	public InventoryDialog(Avatar avatar, Skin skin) {
		super(skin);
		resourceList = new VerticalGroup();
		resourceScrollPane = new ScrollPane(resourceList);
		this.setContent(resourceScrollPane);
	}

}

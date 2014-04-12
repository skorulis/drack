package com.skorulis.drack.ui;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.skorulis.drack.def.BuildingDef;

public class BuildMenuDialog extends ModalDialog {
	
	private HorizontalGroup horizGroup;
	
	public BuildMenuDialog(Skin skin,ArrayList<BuildingDef> buildings) {
		super(skin);
		horizGroup = new HorizontalGroup();
		for(BuildingDef bd : buildings) {
			TextButton button = new TextButton(bd.name(),skin);
			horizGroup.addActor(button);
		}
		super.content = horizGroup;
		this.addActor(horizGroup);
	}

}

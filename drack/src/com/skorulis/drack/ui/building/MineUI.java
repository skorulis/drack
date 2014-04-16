package com.skorulis.drack.ui.building;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.skorulis.drack.building.Mine;
import com.skorulis.drack.ui.UIManager;
import com.skorulis.drack.unit.MineAction;
import com.skorulis.drack.unit.Unit;

public class MineUI extends BuildingUI {
	
	private TextButton mineButton;
	
	public MineUI() {
		
	}
	
	public void init(Skin skin, UIManager ui) {
		super.init(skin,ui);
		mineButton = new TextButton("Mine",skin);
		addActor(mineButton);
		
		mineButton.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				mineClicked();
			}
		});
	}
	
	public void mineClicked() {
		Unit avatar = this.ui.game().player().controllUnit();
		MineAction action = new MineAction(avatar, mine());
		avatar.setAction(action);
	}
	
	public void layout() {
		helper.centreChildX(mineButton);
		super.layout();
	}
	
	public Mine mine() {
		return (Mine) building;
	}
	
}

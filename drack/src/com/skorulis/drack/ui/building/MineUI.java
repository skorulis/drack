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
	
	private TextButton infoButton;
	private TextButton mineButton;
	private TextButton assignButton;
	
	public void init(Skin skin, UIManager ui) {
		super.init(skin,ui);
		infoButton = new TextButton("Info",skin);
		mineButton = new TextButton("Mine",skin);
		assignButton = new TextButton("Assign",skin);
		
		buttonGroup.addActor(infoButton);
		buttonGroup.addActor(mineButton);
		buttonGroup.addActor(assignButton);
		
		infoButton.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				infoClicked();
			}
		});
		
		mineButton.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				mineClicked();
			}
		});
		
		assignButton.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				assignClicked();
			}
		});	
	}
	
	public void assignClicked() {
		
	}
	
	public void infoClicked() {
		
	}
	
	public void mineClicked() {
		Unit avatar = this.ui.game().player().controllUnit();
		MineAction action = new MineAction(avatar, mine());
		avatar.setAction(action);
	}
	

	
	public Mine mine() {
		return (Mine) building;
	}
	
}

package com.skorulis.drack.ui.building;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.skorulis.drack.avatar.Avatar;
import com.skorulis.drack.avatar.MineAction;
import com.skorulis.drack.building.Building;
import com.skorulis.drack.building.Mine;
import com.skorulis.drack.ui.UIManager;
import com.skorulis.gdx.ui.LayoutHelper;

public class MineUI extends BuildingUI {
	
	private TextButton mineButton;
	private LayoutHelper helper;
	private Mine mine;
	
	public MineUI() {
		helper = new LayoutHelper(this);
		
		
		this.setFillParent(true);
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
	
	public void setBuilding(Building building) {
		this.mine = (Mine) building;
	}
	
	public void mineClicked() {
		Avatar avatar = this.ui.game().playerAvatar(); 
		MineAction action = new MineAction(avatar, mine);
		avatar.setAction(action);
	}
	
	public void layout() {
		helper.centreChildX(mineButton);
	}
	
}

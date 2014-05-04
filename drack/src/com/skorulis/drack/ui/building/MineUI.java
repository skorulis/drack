package com.skorulis.drack.ui.building;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.skorulis.drack.actor.action.MineAction;
import com.skorulis.drack.building.Mine;
import com.skorulis.drack.map.MapSquare;
import com.skorulis.drack.pathfinding.MapPath;
import com.skorulis.drack.pathfinding.PathFinder;
import com.skorulis.drack.ui.UIManager;
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
		layout();
	}
	
	public void assignClicked() {
		Unit unit = this.ui.logic().player().freeUnit();
		if(unit != null) {
			MapSquare from = ui.logic().map().squareAt(unit.currentPosition());
			
			PathFinder finder = new PathFinder(ui.logic().map());
			MapPath path = finder.navigate(from, building);
			unit.setPath(path);
			
			MineAction action = new MineAction(unit, mine());
			unit.addAction(action);
		}
		
	}
	
	public void infoClicked() {
		
	}
	
	public void mineClicked() {
		Unit avatar = this.ui.logic().player().controllUnit();
		MineAction action = new MineAction(avatar, mine());
		avatar.addAction(action);
	}
	
	public Mine mine() {
		return (Mine) building;
	}
	
}

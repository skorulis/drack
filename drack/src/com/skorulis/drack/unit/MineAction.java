package com.skorulis.drack.unit;

import java.util.ArrayList;

import com.skorulis.drack.building.Building;
import com.skorulis.drack.building.Mine;
import com.skorulis.scene.UpdateInfo;

public class MineAction extends UnitAction {

	private Mine mine;
	private float time;
	private int chunkSize;
	private boolean finished;
	
	public MineAction(Unit avatar, Mine mine) {
		super(avatar);
		this.mine = mine;
		this.chunkSize = 2;
	}
	
	public void update(float delta) {
		time += delta;
		if(time > chunkSize) {
			unit.addResources(mine.mine(chunkSize));
			if(unit.resources().full()) {
				finished = true;
			}
			time -= chunkSize;
		}
	}
	
	public boolean finished() {
		return finished;
	}
	
	public boolean shouldReplace() {
		return false;
	}
	
	public ArrayList<UnitAction> followingActions(UpdateInfo info) {
		Building b = unit.owner().findBuilding("command", unit.currentPosition());
		
		
		
		System.out.println("Found building " + b);
		return new ArrayList<UnitAction>();
	}
	
}

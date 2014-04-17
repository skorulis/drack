package com.skorulis.drack.unit;

import com.skorulis.drack.building.Mine;

public class MineAction extends UnitAction {

	private Mine mine;
	private float time;
	
	public MineAction(Unit avatar, Mine mine) {
		super(avatar);
		this.mine = mine;
	}
	
	public void update(float delta) {
		time += delta;
		if(time > 1) {
			avatar.addResources(mine.mine(time));
			time = 0;
		}
	}
	
	public boolean finished() {
		return false;
	}
	
	public boolean shouldReplace() {
		return false;
	}
	
}

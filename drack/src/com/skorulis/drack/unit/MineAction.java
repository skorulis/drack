package com.skorulis.drack.unit;

import com.skorulis.drack.building.Mine;

public class MineAction extends UnitAction {

	private Mine mine;
	private float time;
	private int chunkSize;
	
	public MineAction(Unit avatar, Mine mine) {
		super(avatar);
		this.mine = mine;
		this.chunkSize = 10;
	}
	
	public void update(float delta) {
		time += delta;
		if(time > chunkSize) {
			avatar.addResources(mine.mine(chunkSize));
			time -= chunkSize;
		}
	}
	
	public boolean finished() {
		return false;
	}
	
	public boolean shouldReplace() {
		return false;
	}
	
}

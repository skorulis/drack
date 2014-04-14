package com.skorulis.drack.avatar;

import com.skorulis.drack.building.Mine;

public class MineAction extends UnitAction {

	private Mine mine;
	private float time;
	
	public MineAction(Avatar avatar, Mine mine) {
		super(avatar);
		this.mine = mine;
	}
	
	public void update(float delta) {
		time += delta;
		if(time > 1) {
			avatar.resources().add(mine.mine(time));
			time = 0;
		}
	}
	
}
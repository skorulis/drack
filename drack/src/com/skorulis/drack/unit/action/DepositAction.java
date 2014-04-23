package com.skorulis.drack.unit.action;

import java.util.ArrayList;

import com.skorulis.drack.unit.Unit;
import com.skorulis.scene.UpdateInfo;

public class DepositAction extends UnitAction {

	private boolean finished;
	
	public DepositAction(Unit avatar) {
		super(avatar);
	}

	public void update(float delta) {
		this.unit.owner().resources().add(this.unit.resources());
		this.unit.resources().clear();
		this.finished = true;
	}
	
	public boolean finished() {
		return this.finished;
	}
	
	public boolean shouldReplace() {
		return false;
	}
	
	public ArrayList<UnitAction> followingActions(UpdateInfo info) {
		return null;
	}
	
}

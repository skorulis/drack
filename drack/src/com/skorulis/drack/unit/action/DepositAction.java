package com.skorulis.drack.unit.action;

import java.util.ArrayList;

import com.skorulis.drack.unit.Unit;
import com.skorulis.scene.UpdateInfo;

public class DepositAction extends UnitAction {

	private boolean finished;
	
	public DepositAction(Unit avatar) {
		super(avatar);
	}

	public void update(UpdateInfo ui) {
		this.unit.owner().addResources(this.unit.resources(), this.unit);
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
	
	public void stopAction() { }
	
}

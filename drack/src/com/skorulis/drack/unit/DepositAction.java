package com.skorulis.drack.unit;

import java.util.ArrayList;
import com.skorulis.scene.UpdateInfo;

public class DepositAction extends UnitAction {

	public DepositAction(Unit avatar) {
		super(avatar);
	}

	private boolean finished;
	
	public void update(float delta) {
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

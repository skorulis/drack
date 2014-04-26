package com.skorulis.drack.unit.action;

import java.util.ArrayList;

import com.skorulis.drack.unit.Unit;
import com.skorulis.scene.UpdateInfo;

public abstract class UnitAction {

	protected Unit unit;
	
	public UnitAction(Unit avatar) {
		this.unit = avatar;
	}
	
	public abstract void update(UpdateInfo ui);
	public abstract boolean finished();
	public abstract boolean shouldReplace();
	public abstract void stopAction();
	public abstract ArrayList<UnitAction> followingActions(UpdateInfo info);
	
}

package com.skorulis.drack.unit;

import java.util.ArrayList;

import com.skorulis.scene.UpdateInfo;

public abstract class UnitAction {

	protected Unit unit;
	
	public UnitAction(Unit avatar) {
		this.unit = avatar;
	}
	
	public abstract void update(float delta);
	public abstract boolean finished();
	public abstract boolean shouldReplace();
	public abstract ArrayList<UnitAction> followingActions(UpdateInfo info);
	
}

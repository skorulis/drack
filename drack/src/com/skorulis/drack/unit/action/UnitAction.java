package com.skorulis.drack.unit.action;

import java.util.ArrayList;
import java.util.List;

import com.skorulis.drack.scene.DrackActorNode;
import com.skorulis.drack.serialisation.unit.action.UnitActionJson;
import com.skorulis.scene.UpdateInfo;

public abstract class UnitAction {

	protected DrackActorNode actor;
	
	public UnitAction(DrackActorNode avatar) {
		this.actor = avatar;
	}
	
	public abstract void update(UpdateInfo ui);
	public abstract boolean finished();
	public abstract boolean shouldReplace();
	public abstract void stopAction();
	public List<UnitAction> followingActions(UpdateInfo info) {
		return new ArrayList<UnitAction>();
	}
	
	public UnitActionJson getSerialisation() {
		return null;
	}
	
}

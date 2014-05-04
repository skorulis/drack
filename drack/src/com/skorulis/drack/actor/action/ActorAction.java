package com.skorulis.drack.actor.action;

import java.util.ArrayList;
import java.util.List;

import com.skorulis.drack.scene.DrackActorNode;
import com.skorulis.drack.serialisation.unit.action.UnitActionJson;
import com.skorulis.scene.UpdateInfo;

public abstract class ActorAction {

	protected DrackActorNode actor;
	
	public ActorAction(DrackActorNode avatar) {
		this.actor = avatar;
	}
	
	public abstract void update(UpdateInfo ui);
	public abstract boolean finished();
	public abstract boolean shouldReplace();
	public abstract void stopAction();
	public List<ActorAction> followingActions(UpdateInfo info) {
		return new ArrayList<ActorAction>();
	}
	
	public UnitActionJson getSerialisation() {
		return null;
	}
	
}

package com.skorulis.drack.actor.action;

import java.util.ArrayList;
import com.skorulis.drack.scene.DrackActorNode;
import com.skorulis.drack.serialisation.unit.action.DepositActionJson;
import com.skorulis.drack.serialisation.unit.action.UnitActionJson;
import com.skorulis.scene.UpdateInfo;

public class DepositAction extends ActorAction {

	private boolean finished;
	
	public DepositAction(DrackActorNode avatar) {
		super(avatar);
	}

	public void update(UpdateInfo ui) {
		this.actor.owner().addResources(this.actor.resources(), this.actor);
		this.actor.resources().clear();
		this.finished = true;
	}
	
	public boolean finished() {
		return this.finished;
	}
	
	public boolean shouldReplace() {
		return false;
	}
	
	public ArrayList<ActorAction> followingActions(UpdateInfo info) {
		return null;
	}
	
	public void stopAction() { }

	@Override
	public UnitActionJson getSerialisation() {
		return new DepositActionJson();
	}
	
}

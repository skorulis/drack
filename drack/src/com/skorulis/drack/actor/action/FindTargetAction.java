package com.skorulis.drack.actor.action;

import com.skorulis.drack.actor.unit.Unit;
import com.skorulis.drack.scene.DrackActorNode;
import com.skorulis.scene.UpdateInfo;

public class FindTargetAction extends ActorAction {

	private Unit discovered;
	
	public FindTargetAction(DrackActorNode actor) {
		super(actor);
	}
	
	@Override
	public void update(UpdateInfo ui) {
		discovered = ui.scene().findEnemyUnit(actor.owner());
	}

	@Override
	public boolean finished() {
		return false;
	}

	@Override
	public boolean shouldReplace() {
		return false;
	}

	@Override
	public void stopAction() { }
	
	public Unit discovered() {
		return discovered;
	}

}

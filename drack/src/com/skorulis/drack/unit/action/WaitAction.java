package com.skorulis.drack.unit.action;

import java.util.ArrayList;
import com.skorulis.drack.scene.DrackActorNode;
import com.skorulis.drack.serialisation.unit.action.WaitActionJson;
import com.skorulis.scene.UpdateInfo;

public class WaitAction extends UnitAction {

	private float timeRemaining;
	
	public WaitAction(DrackActorNode unit, float timeRemaining) {
		super(unit);
		this.timeRemaining = timeRemaining;
	}
	
	@Override
	public void update(UpdateInfo ui) {
		this.timeRemaining -= ui.delta;
	}

	@Override
	public boolean finished() {
		return this.timeRemaining <= 0;
	}

	@Override
	public boolean shouldReplace() {
		return false;
	}

	@Override
	public void stopAction() { }

	@Override
	public ArrayList<UnitAction> followingActions(UpdateInfo info) {
		return new ArrayList<UnitAction>();
	}
	
	public WaitActionJson getSerialisation() {
		WaitActionJson json = new WaitActionJson();
		json.timeRemaining = this.timeRemaining;
		return json;
	}

}

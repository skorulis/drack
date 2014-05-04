package com.skorulis.drack.serialisation.unit.action;

import com.skorulis.drack.actor.action.ActorAction;
import com.skorulis.drack.actor.action.WaitAction;
import com.skorulis.drack.scene.DrackActorNode;
import com.skorulis.drack.serialisation.LoadData;

public class WaitActionJson extends UnitActionJson {

	public float timeRemaining;
	
	@Override
	public ActorAction load(LoadData ld, DrackActorNode actor) {
		return new WaitAction(actor, timeRemaining);
	}
	
	

}

package com.skorulis.drack.serialisation.unit.action;

import com.skorulis.drack.actor.action.FindTargetAction;
import com.skorulis.drack.actor.action.ActorAction;
import com.skorulis.drack.scene.DrackActorNode;
import com.skorulis.drack.serialisation.LoadData;

public class FindTargetActionJson extends UnitActionJson {

	@Override
	public ActorAction load(LoadData ld, DrackActorNode actor) {
		return new FindTargetAction(actor);
	}

}

package com.skorulis.drack.serialisation.unit.action;

import com.skorulis.drack.actor.action.DepositAction;
import com.skorulis.drack.actor.action.ActorAction;
import com.skorulis.drack.scene.DrackActorNode;
import com.skorulis.drack.serialisation.LoadData;

public class DepositActionJson extends UnitActionJson {

	@Override
	public ActorAction load(LoadData ld, DrackActorNode unit) {
		return new DepositAction(unit);
	}

}

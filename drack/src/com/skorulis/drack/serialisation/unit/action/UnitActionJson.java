package com.skorulis.drack.serialisation.unit.action;

import com.skorulis.drack.actor.action.ActorAction;
import com.skorulis.drack.scene.DrackActorNode;
import com.skorulis.drack.serialisation.LoadData;

public abstract class UnitActionJson {

	public abstract ActorAction load(LoadData ld, DrackActorNode node);
	
}

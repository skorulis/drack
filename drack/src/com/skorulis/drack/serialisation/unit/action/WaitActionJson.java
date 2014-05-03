package com.skorulis.drack.serialisation.unit.action;

import com.skorulis.drack.scene.DrackActorNode;
import com.skorulis.drack.serialisation.LoadData;
import com.skorulis.drack.unit.action.UnitAction;
import com.skorulis.drack.unit.action.WaitAction;

public class WaitActionJson extends UnitActionJson {

	public float timeRemaining;
	
	@Override
	public UnitAction load(LoadData ld, DrackActorNode actor) {
		return new WaitAction(actor, timeRemaining);
	}
	
	

}

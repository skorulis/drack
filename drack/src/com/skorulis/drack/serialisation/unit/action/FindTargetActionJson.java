package com.skorulis.drack.serialisation.unit.action;

import com.skorulis.drack.scene.DrackActorNode;
import com.skorulis.drack.serialisation.LoadData;
import com.skorulis.drack.unit.action.FindTargetAction;
import com.skorulis.drack.unit.action.UnitAction;

public class FindTargetActionJson extends UnitActionJson {

	@Override
	public UnitAction load(LoadData ld, DrackActorNode actor) {
		return new FindTargetAction(actor);
	}

}

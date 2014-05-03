package com.skorulis.drack.serialisation.unit.action;

import com.skorulis.drack.scene.DrackActorNode;
import com.skorulis.drack.serialisation.LoadData;
import com.skorulis.drack.unit.action.UnitAction;

public abstract class UnitActionJson {

	public abstract UnitAction load(LoadData ld, DrackActorNode node);
	
}

package com.skorulis.drack.serialisation.unit.action;

import com.skorulis.drack.serialisation.LoadData;
import com.skorulis.drack.unit.Unit;
import com.skorulis.drack.unit.action.UnitAction;
import com.skorulis.drack.unit.action.WaitAction;

public class WaitActionJson extends UnitActionJson {

	public float timeRemaining;
	
	@Override
	public UnitAction load(LoadData ld, Unit unit) {
		return new WaitAction(unit, timeRemaining);
	}
	
	

}

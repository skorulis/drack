package com.skorulis.drack.serialisation.unit.action;

import com.skorulis.drack.serialisation.LoadData;
import com.skorulis.drack.unit.Unit;
import com.skorulis.drack.unit.action.DepositAction;
import com.skorulis.drack.unit.action.UnitAction;

public class DepositActionJson extends UnitActionJson {

	@Override
	public UnitAction load(LoadData ld, Unit unit) {
		return new DepositAction(unit);
	}

}

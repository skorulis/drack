package com.skorulis.drack.serialisation.unit.action;

import com.skorulis.drack.building.Mine;
import com.skorulis.drack.serialisation.LoadData;
import com.skorulis.drack.unit.Unit;
import com.skorulis.drack.unit.action.MineAction;
import com.skorulis.drack.unit.action.UnitAction;

public class MineActionJson extends UnitActionJson {

	public int squareX;
	public int squareZ;
	
	public UnitAction load(LoadData ld, Unit unit) {
		Mine mine = (Mine) ld.map.squareAt(squareX, squareZ).building();
		MineAction ma = new MineAction(unit, mine);
		return ma;
	}
}

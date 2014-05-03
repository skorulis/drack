package com.skorulis.drack.serialisation.unit.action;

import com.skorulis.drack.building.Mine;
import com.skorulis.drack.scene.DrackActorNode;
import com.skorulis.drack.scene.DrackMoveableActor;
import com.skorulis.drack.serialisation.LoadData;
import com.skorulis.drack.unit.action.MineAction;
import com.skorulis.drack.unit.action.UnitAction;

public class MineActionJson extends UnitActionJson {

	public int squareX;
	public int squareZ;
	
	public UnitAction load(LoadData ld, DrackActorNode actor) {
		Mine mine = (Mine) ld.map.squareAt(squareX, squareZ).building();
		MineAction ma = new MineAction( (DrackMoveableActor) actor, mine);
		return ma;
	}
}

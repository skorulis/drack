package com.skorulis.drack.serialisation.unit.action;

import com.skorulis.drack.actor.action.MineAction;
import com.skorulis.drack.actor.action.ActorAction;
import com.skorulis.drack.building.Mine;
import com.skorulis.drack.scene.DrackActorNode;
import com.skorulis.drack.scene.DrackMoveableActor;
import com.skorulis.drack.serialisation.LoadData;

public class MineActionJson extends UnitActionJson {

	public int squareX;
	public int squareZ;
	
	public ActorAction load(LoadData ld, DrackActorNode actor) {
		Mine mine = (Mine) ld.map.squareAt(squareX, squareZ).building();
		MineAction ma = new MineAction( (DrackMoveableActor) actor, mine);
		return ma;
	}
}

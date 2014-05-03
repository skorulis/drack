package com.skorulis.drack.serialisation.unit.action;

import com.skorulis.drack.map.MapSquare;
import com.skorulis.drack.pathfinding.MapPath;
import com.skorulis.drack.pathfinding.PathFinder;
import com.skorulis.drack.scene.DrackActorNode;
import com.skorulis.drack.scene.DrackMoveableActor;
import com.skorulis.drack.serialisation.LoadData;
import com.skorulis.drack.unit.action.MovementAction;
import com.skorulis.drack.unit.action.UnitAction;

public class MovementActionJson extends UnitActionJson {

	public int squareX;
	public int squareZ;
	
	@Override
	public UnitAction load(LoadData ld, DrackActorNode actor) {
		MapSquare sq = ld.map.squareAt(squareX, squareZ);
		MapSquare current = ld.map.squareAt(actor.currentPosition());

		PathFinder finder = new PathFinder(ld.map);
		MapPath path = finder.navigate(current, sq);
		return new MovementAction((DrackMoveableActor)actor, path);
	}
	
}

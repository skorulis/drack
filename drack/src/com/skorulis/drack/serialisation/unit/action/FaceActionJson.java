package com.skorulis.drack.serialisation.unit.action;

import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.scene.DrackActorNode;
import com.skorulis.drack.serialisation.LoadData;
import com.skorulis.drack.unit.action.FaceAction;
import com.skorulis.drack.unit.action.UnitAction;

public class FaceActionJson extends UnitActionJson {

	public Vector3 target;
	
	@Override
	public UnitAction load(LoadData ld, DrackActorNode unit) {
		return new FaceAction(unit, target);
	}
	
	
	
}

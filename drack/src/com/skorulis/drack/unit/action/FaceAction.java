package com.skorulis.drack.unit.action;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.scene.DrackActorNode;
import com.skorulis.drack.serialisation.unit.action.FaceActionJson;
import com.skorulis.scene.UpdateInfo;

public class FaceAction extends UnitAction {

	private boolean finished;
	private Vector3 target;
	
	public FaceAction(DrackActorNode unit, Vector3 target) {
		super(unit);
		this.target = target;
	}

	@Override
	public void update(UpdateInfo ui) {
		Vector3 loc = actor.currentPosition();
		Vector3 dir = target.cpy().sub(loc);
		dir.y = 0;
		dir.z *= -1;
		dir.nor();
		actor.absTransform().setToWorld(loc, dir, new Vector3(0,1,0));
		finished = true;
	}

	@Override
	public boolean finished() {
		return finished;
	}

	@Override
	public boolean shouldReplace() {
		return false;
	}

	@Override
	public ArrayList<UnitAction> followingActions(UpdateInfo info) {
		return null;
	}
	
	public void stopAction() { }
	
	public FaceActionJson getSerialisation() {
		FaceActionJson json = new FaceActionJson();
		json.target = this.target;
		return json;
	}
	
}

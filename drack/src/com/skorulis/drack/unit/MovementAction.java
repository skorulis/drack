package com.skorulis.drack.unit;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.pathfinding.MapPath;
import com.skorulis.drack.pathfinding.MovementInfo;
import com.skorulis.scene.UpdateInfo;

public class MovementAction extends UnitAction {
	
	private MapPath path;
	private MovementInfo movement;
	
	public MovementAction(Unit avatar, MapPath path) {
		super(avatar);
		setPath(path);
	}
	
	public void setPath(MapPath path) {
		this.path = path;
		if (movement == null) {
			movement = path.getMovement(unit.speed());
		}
	}

	@Override
	public void update(float delta) {
		if(movement == null) {
			return;
		}
		unit.absTransform().setToWorld(movement.update(delta), movement.direction(),new Vector3(0,1,0));
		if (movement.finished()) {
			if (path.finished()) {
				movement = null;
				path = null;
			} else {
				if (movement.destSquare == path.nextNode()) {
					movement = path.next(unit.speed());
				} else {
					movement = path.getMovement(unit.speed());
				}
			}
		}
	}
	
	public boolean finished() {
		return movement == null && path == null;
	}
	
	public Vector3 movingTo() {
		return movement.endLoc;
	}
	
	public boolean shouldReplace() {
		return true;
	}
	
	public ArrayList<UnitAction> followingActions(UpdateInfo info) {
		return new ArrayList<UnitAction>();
	}

}

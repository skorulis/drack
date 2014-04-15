package com.skorulis.drack.avatar;

import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.pathfinding.MapPath;
import com.skorulis.drack.pathfinding.MovementInfo;

public class MovementAction extends UnitAction {
	
	private MapPath path;
	private MovementInfo movement;
	
	public MovementAction(Avatar avatar, MapPath path) {
		super(avatar);
		setPath(path);
	}
	
	public void setPath(MapPath path) {
		this.path = path;
		if (movement == null) {
			movement = path.getMovement(avatar.speed());
		}
	}

	@Override
	public void update(float delta) {
		if(movement == null) {
			return;
		}
		avatar.absTransform().setToWorld(movement.update(delta), movement.direction(),new Vector3(0,1,0));
		if (movement.finished()) {
			if (path.finished()) {
				movement = null;
				path = null;
			} else {
				if (movement.destSquare == path.nextNode()) {
					movement = path.next(avatar.speed());
				} else {
					movement = path.getMovement(avatar.speed());
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

}

package com.skorulis.drack.unit.action;

import java.util.ArrayList;
import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.map.MapSquare;
import com.skorulis.drack.pathfinding.MapPath;
import com.skorulis.drack.pathfinding.MovementInfo;
import com.skorulis.drack.scene.DrackMoveableActor;
import com.skorulis.drack.serialisation.unit.action.MovementActionJson;
import com.skorulis.scene.UpdateInfo;

public class MovementAction extends UnitAction {
	
	private MapPath path;
	private MovementInfo movement;
	
	public MovementAction(DrackMoveableActor avatar, MapPath path) {
		super(avatar);
		setPath(path);
	}
	
	public void setPath(MapPath path) {
		this.path = path;
		if (movement == null) {
			movement = path.getMovement(moveableActor().speed());
		}
	}

	@Override
	public void update(UpdateInfo ui) {
		if(movement == null) {
			return;
		}
		actor.absTransform().setToWorld(movement.update(ui.delta), movement.direction(),new Vector3(0,1,0));
		if (movement.finished()) {
			if (path.finished()) {
				movement = null;
				path = null;
			} else {
				if (movement.destSquare == path.nextNode()) {
					movement = path.next(moveableActor().speed());
				} else {
					movement = path.getMovement(moveableActor().speed());
				}
			}
		}
	}
	
	public boolean finished() {
		return movement == null && path == null;
	}
	
	public Vector3 movingTo() {
		return movement.endLoc.cpy();
	}
	
	public boolean shouldReplace() {
		return true;
	}
	
	public ArrayList<UnitAction> followingActions(UpdateInfo info) {
		return new ArrayList<UnitAction>();
	}
	
	public void stopAction() { }
	
	public MovementActionJson getSerialisation() {
		MovementActionJson json = new MovementActionJson();
		MapSquare square = path.finalSquare();
		json.squareX = square.x();
		json.squareZ = square.z();
		
		return json;
	}
	
	public DrackMoveableActor moveableActor() {
		return (DrackMoveableActor) actor;
	}

}

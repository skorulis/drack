package com.skorulis.drack.unit.action;

import java.util.ArrayList;

import com.skorulis.drack.building.Building;
import com.skorulis.drack.building.Mine;
import com.skorulis.drack.map.MapSquare;
import com.skorulis.drack.pathfinding.MapPath;
import com.skorulis.drack.pathfinding.PathFinder;
import com.skorulis.drack.scene.DrackMoveableActor;
import com.skorulis.drack.serialisation.unit.action.MineActionJson;
import com.skorulis.scene.UpdateInfo;

public class MineAction extends UnitAction {

	private Mine mine;
	private float time;
	private int chunkSize;
	private boolean finished;
	
	public MineAction(DrackMoveableActor avatar, Mine mine) {
		super(avatar);
		this.mine = mine;
		this.chunkSize = 2;
	}
	
	public void update(UpdateInfo ui) {
		time += ui.delta;
		if(time > chunkSize) {
			actor.addResources(mine.mine(chunkSize));
			if(actor.resources().full()) {
				finished = true;
			}
			time -= chunkSize;
		}
	}
	
	public boolean finished() {
		return finished;
	}
	
	public boolean shouldReplace() {
		return false;
	}
	
	public ArrayList<UnitAction> followingActions(UpdateInfo info) {
		Building b = actor.owner().findBuilding("command", actor.currentPosition());
		
		PathFinder finder = new PathFinder(info.map());
		
		MapPath path1 = finder.navigate(actor, b);
		MapPath path2 = finder.navigate(path1.finalSquare(), mine);
		
		MovementAction move1 = new MovementAction(moveableActor(), path1);
		MovementAction move2 = new MovementAction(moveableActor(), path2);
		
		MineAction mineAction = new MineAction(moveableActor(), mine);
		
		ArrayList<UnitAction> ret = new ArrayList<UnitAction>();
		
		ret.add(move1);
		ret.add(new DepositAction(actor));
		ret.add(move2);
		ret.add(mineAction);
		
		return ret;
	}
	
	public void stopAction() { }
	
	
	public MineActionJson getSerialisation() {
		MineActionJson json = new MineActionJson();
		MapSquare square = this.mine.mainSquare();
		json.squareX = square.x();
		json.squareZ = square.z();
		return json;
	}
	
	public DrackMoveableActor moveableActor() {
		return (DrackMoveableActor) this.actor;
	}
	
}

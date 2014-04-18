package com.skorulis.drack.unit.action;

import java.util.ArrayList;

import com.skorulis.drack.building.Building;
import com.skorulis.drack.building.Mine;
import com.skorulis.drack.pathfinding.MapPath;
import com.skorulis.drack.pathfinding.PathFinder;
import com.skorulis.drack.unit.Unit;
import com.skorulis.scene.UpdateInfo;

public class MineAction extends UnitAction {

	private Mine mine;
	private float time;
	private int chunkSize;
	private boolean finished;
	
	public MineAction(Unit avatar, Mine mine) {
		super(avatar);
		this.mine = mine;
		this.chunkSize = 2;
	}
	
	public void update(float delta) {
		time += delta;
		if(time > chunkSize) {
			unit.addResources(mine.mine(chunkSize));
			if(unit.resources().full()) {
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
		Building b = unit.owner().findBuilding("command", unit.currentPosition());
		
		PathFinder finder = new PathFinder(info.map());
		
		MapPath path1 = finder.navigate(unit, b);
		MapPath path2 = finder.navigate(path1.finalSquare(), mine);
		
		MovementAction move1 = new MovementAction(unit, path1);
		MovementAction move2 = new MovementAction(unit, path2);
		
		MineAction mineAction = new MineAction(unit, mine);
		
		ArrayList<UnitAction> ret = new ArrayList<UnitAction>();
		
		ret.add(move1);
		ret.add(new DepositAction(unit));
		ret.add(move2);
		ret.add(mineAction);
		
		return ret;
	}
	
}

package com.skorulis.drack.unit.action;

import java.util.ArrayList;
import java.util.List;

import com.skorulis.drack.scene.DrackActorNode;
import com.skorulis.drack.serialisation.LoadData;
import com.skorulis.drack.serialisation.unit.action.UnitActionJson;
import com.skorulis.scene.UpdateInfo;

public class ActionContainer {

	private DrackActorNode node;
	private ArrayList<UnitAction> actions;
	
	public ActionContainer(DrackActorNode node) {
		this.node = node;
		this.actions = new ArrayList<UnitAction>();
	}
	
	public void load(List<UnitActionJson> list, LoadData ld) {
		for(UnitActionJson act : list) {
			UnitAction action = act.load(ld, this.node);
			actions.add(action);
		}
	}
	
	public void update(UpdateInfo info) {
		UnitAction action = currentAction();
		if(action != null) {
			action.update(info);
			if(action.finished()) {
				action.stopAction();
				actions.remove(0);
				ArrayList<UnitAction> following = action.followingActions(info);
				if(following != null) {
					actions.addAll(0, following);
				}
			}
		}
	}
	
	public UnitAction currentAction() {
		if(actions.size() > 0) {
			return actions.get(0);
		}
		return null;
	}
	
	public void addAction(UnitAction action) {
		if(action.shouldReplace()) {
			clearActions();
		}
		this.actions.add(action);
	}
	
	public void clearActions() {
		UnitAction action = this.currentAction();
		if(action != null) {
			action.stopAction();
		}
		this.actions.clear();
	}
	
	public ArrayList<UnitActionJson> getSerialisation() {
		ArrayList<UnitActionJson> ret = new ArrayList<UnitActionJson>();
		for(UnitAction action : this.actions) {
			UnitActionJson actionJson = action.getSerialisation();
			if(actionJson != null) {
				ret.add(actionJson);
			}
		}
		return ret;
	}
	
}

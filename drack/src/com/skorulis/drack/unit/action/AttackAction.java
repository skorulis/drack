package com.skorulis.drack.unit.action;

import java.util.ArrayList;

import com.skorulis.drack.unit.Unit;
import com.skorulis.scene.UpdateInfo;

public class AttackAction extends UnitAction {

	private final Unit target;
	
	public AttackAction(Unit unit, Unit target) {
		super(unit);
		this.target = target;
	}
	
	@Override
	public void update(float delta) {
		target.takeDamage(delta * 10);
	}

	@Override
	public boolean finished() {
		return !target.isAlive();
	}

	@Override
	public boolean shouldReplace() {
		return false;
	}

	@Override
	public ArrayList<UnitAction> followingActions(UpdateInfo info) {
		return null;
	}
	
	public Unit target() {
		return target;
	}

}

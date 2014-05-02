package com.skorulis.drack.unit.action;

import java.util.ArrayList;
import java.util.Set;

import com.skorulis.drack.unit.Unit;
import com.skorulis.drack.unit.composite.Weapon;
import com.skorulis.scene.UpdateInfo;

public class AttackAction extends UnitAction {

	private final Unit target;
	
	public AttackAction(Unit unit, Unit target) {
		super(unit);
		this.target = target;
	}
	
	@Override
	public void update(UpdateInfo ui) {
		Set<Weapon> weapons = unit.allWeapons();
		for(Weapon w : weapons) {
			if(w.isFinished()) {
				w.startAttack(ui.assets(), unit, target);
			}
		}
		target.takeDamage(ui.delta * 10);
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
	
	public void stopAction() {
		System.out.println("FINISH");
		Set<Weapon> weapons = unit.allWeapons();
		for(Weapon w : weapons) {
			w.finishAttack();
		}
	}
	
}

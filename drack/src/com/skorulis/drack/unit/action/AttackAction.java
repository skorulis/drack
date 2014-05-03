package com.skorulis.drack.unit.action;

import java.util.ArrayList;
import java.util.Set;
import com.skorulis.drack.scene.DrackActorNode;
import com.skorulis.drack.unit.composite.Weapon;
import com.skorulis.scene.UpdateInfo;

public class AttackAction extends UnitAction {

	private final DrackActorNode target;
	
	public AttackAction(DrackActorNode unit, DrackActorNode target) {
		super(unit);
		this.target = target;
	}
	
	@Override
	public void update(UpdateInfo ui) {
		Set<Weapon> weapons = actor.allWeapons();
		for(Weapon w : weapons) {
			if(w.isFinished()) {
				w.startAttack(ui.assets(), actor, target);
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
	
	public DrackActorNode target() {
		return target;
	}
	
	public void stopAction() {
		System.out.println("FINISH");
		Set<Weapon> weapons = actor.allWeapons();
		for(Weapon w : weapons) {
			w.finishAttack();
		}
	}
	
}

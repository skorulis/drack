package com.skorulis.drack.actor.action;

import java.util.ArrayList;
import java.util.Set;

import com.skorulis.drack.attachments.Weapon;
import com.skorulis.drack.scene.DrackActorNode;
import com.skorulis.scene.UpdateInfo;

public class AttackAction extends ActorAction {

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
	public ArrayList<ActorAction> followingActions(UpdateInfo info) {
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

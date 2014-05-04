package com.skorulis.drack.building;

import com.skorulis.drack.building.composite.CompositeBuilding;
import com.skorulis.drack.scene.DrackActorNode;
import com.skorulis.drack.unit.action.AttackAction;
import com.skorulis.drack.unit.action.FindTargetAction;
import com.skorulis.drack.unit.action.UnitAction;
import com.skorulis.scene.UpdateInfo;

public class TurretBuilding extends CompositeBuilding {

	public TurretBuilding() {
	}
	
	@Override
	public void update(UpdateInfo ui) {
		super.update(ui);
		
		UnitAction action = this.actions.currentAction();
		if(action == null) {
			this.actions.addAction(new FindTargetAction(this));
		} else if(action instanceof FindTargetAction) {
			DrackActorNode node = ((FindTargetAction) action).discovered();
			this.actions.clearActions();
			if(node != null) {
				AttackAction attack = new AttackAction(this, node);
				this.actions.addAction(attack);
			}
		}
		
	}
	
}

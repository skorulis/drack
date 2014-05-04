package com.skorulis.drack.actor.building;

import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.actor.action.AttackAction;
import com.skorulis.drack.actor.action.FaceAction;
import com.skorulis.drack.actor.action.FindTargetAction;
import com.skorulis.drack.actor.action.ActorAction;
import com.skorulis.drack.actor.attachments.HullAttachment;
import com.skorulis.drack.actor.attachments.Weapon;
import com.skorulis.drack.actor.building.composite.CompositeBuilding;
import com.skorulis.drack.def.attachment.HardPointDef;
import com.skorulis.drack.scene.DrackActorNode;
import com.skorulis.scene.UpdateInfo;

public class TurretBuilding extends CompositeBuilding {

	public TurretBuilding() {
	}
	
	@Override
	public void update(UpdateInfo ui) {
		super.update(ui);
		
		ActorAction action = this.actions.currentAction();
		if(action == null) {
			this.actions.addAction(new FindTargetAction(this));
		} else if(action instanceof FindTargetAction) {
			DrackActorNode node = ((FindTargetAction) action).discovered();
			this.actions.clearActions();
			if(node != null) {
				FaceAction face = new FaceAction(this, node.currentPosition());
				this.actions.addAction(face);
				
				AttackAction attack = new AttackAction(this, node);
				this.actions.addAction(attack);
			}
		}
		
	}
	
	@Override
	public void faceDirection(Vector3 dir) {
		Weapon weapon = this.turretWeapon();
		
		Vector3 loc = weapon.offset().getTranslation(new Vector3());
		weapon.offset().setToWorld(loc, dir, new Vector3(0,1,0));
	}
	
	public Weapon turretWeapon() {
		if(this.attachmentContainer().size() == 0) {
			return null;
		}
		HardPointDef hardPoint = this.attachmentContainer().getHardPoint(0);
		HullAttachment att = this.attachmentContainer().attachmentAt(hardPoint);
		return (Weapon) att;
	}
	
}

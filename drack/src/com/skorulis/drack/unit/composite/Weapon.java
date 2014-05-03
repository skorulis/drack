package com.skorulis.drack.unit.composite;

import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.def.attachment.WeaponDef;
import com.skorulis.drack.scene.DrackActorNode;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.UpdateInfo;

public abstract class Weapon extends HullAttachment {
	
	public void update(UpdateInfo info) {
		super.update(info);
	}
	
	public Vector3 turretPosition(DrackActorNode unit) {
		Vector3 pos = unit.currentPosition();
		
		Vector3 offset = this.hardPoint.loc.cpy().add(weaponDef().turretLoc);
		offset = offset.rot(unit.absTransform());
		
		return pos.add(offset);
	}
	
	public WeaponDef weaponDef() {
		return (WeaponDef) this.def;
	}
	
	public abstract boolean isFinished();
	public abstract void finishAttack();
	public abstract void startAttack(SKAssetManager assets, DrackActorNode unit, DrackActorNode target);
	
}

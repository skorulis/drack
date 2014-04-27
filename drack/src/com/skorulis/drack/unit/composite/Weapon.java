package com.skorulis.drack.unit.composite;

import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.def.attachment.HullAttachmentDef;
import com.skorulis.drack.def.attachment.WeaponDef;
import com.skorulis.drack.effects.LaserEffect;
import com.skorulis.drack.unit.Unit;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.UpdateInfo;

public class Weapon extends HullAttachment {
	
	private LaserEffect currentEffect;
	
	public void setDef(HullAttachmentDef def) {
		this.def = def;
	}
	
	public void render(RenderInfo ri) {
		super.render(ri);
		if(currentEffect != null) {
			currentEffect.render(ri);
		}
	}
	
	public void update(UpdateInfo info) {
		super.update(info);
	}
	
	public boolean isFinished() {
		return currentEffect == null;
	}
	
	public void finishAttack() {
		currentEffect = null;
	}
	
	public Vector3 turretPosition(Unit unit) {
		Vector3 pos = unit.currentPosition();
		
		Vector3 offset = this.hardPoint.loc.cpy().add(weaponDef().turretLoc);
		
		System.out.println("Offset " + offset);
		
		offset = offset.rot(unit.absTransform());
		
		System.out.println("Offset " + offset);
		
		return pos.add(offset);
	}
	
	public void startAttack(SKAssetManager assets, Unit unit, Unit target) {
		Vector3 start = turretPosition(unit);
		Vector3 end = target.currentPosition();
		end.y += 0.5f;
		currentEffect = new LaserEffect(assets, start, end);
	}
	
	public WeaponDef weaponDef() {
		return (WeaponDef) this.def;
	}
	
	
}

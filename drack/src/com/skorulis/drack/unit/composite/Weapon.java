package com.skorulis.drack.unit.composite;

import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.def.attachment.HullAttachmentDef;
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
	
	public void startAttack(SKAssetManager assets, Unit unit, Unit target) {
		Vector3 start = unit.currentPosition();
		Vector3 end = target.currentPosition();
		start.y += 0.5f;
		end.y += 0.5f;
		currentEffect = new LaserEffect(assets, start, end);
		System.out.println("STart attack");
	}
	
	
}

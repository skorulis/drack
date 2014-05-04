package com.skorulis.drack.unit.composite;

import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.effects.LaserEffect;
import com.skorulis.drack.scene.DrackActorNode;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.UpdateInfo;

public class LaserWeapon extends Weapon {

	private LaserEffect currentEffect;
	
	public void render(RenderInfo ri) {
		super.render(ri);
		if(currentEffect != null) {
			currentEffect.render(ri);
		}
	}
	
	public void update(UpdateInfo ui) {
		super.update(ui);
		if(this.target != null) {
			this.target.takeDamage(ui.delta);
		}
	}
	
	public boolean isFinished() {
		return currentEffect == null;
	}
	
	public void finishAttack() {
		super.finishAttack();
		currentEffect = null;
	}
	
	public void startAttack(SKAssetManager assets, DrackActorNode unit, DrackActorNode target) {
		super.startAttack(assets, unit, target);
		Vector3 start = turretPosition(unit);
		Vector3 end = target.currentPosition();
		end.y += 0.5f;
		currentEffect = new LaserEffect(assets, start, end);
	}
	
}

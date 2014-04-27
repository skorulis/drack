package com.skorulis.drack.unit.composite;

import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.effects.LaserEffect;
import com.skorulis.drack.unit.Unit;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;

public class LaserWeapon extends Weapon {

	private LaserEffect currentEffect;
	
	public void render(RenderInfo ri) {
		super.render(ri);
		if(currentEffect != null) {
			currentEffect.render(ri);
		}
	}
	
	public boolean isFinished() {
		return currentEffect == null;
	}
	
	public void finishAttack() {
		currentEffect = null;
	}
	
	public void startAttack(SKAssetManager assets, Unit unit, Unit target) {
		Vector3 start = turretPosition(unit);
		Vector3 end = target.currentPosition();
		end.y += 0.5f;
		currentEffect = new LaserEffect(assets, start, end);
	}
	
}

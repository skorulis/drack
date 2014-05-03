package com.skorulis.drack.unit.composite;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.effects.BulletEffect;
import com.skorulis.drack.scene.DrackActorNode;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.UpdateInfo;

public class BulletWeapon extends Weapon {

	private Set<BulletEffect> effects;
	
	public BulletWeapon() {
		effects = new HashSet<BulletEffect>();
	}
	
	public void render(RenderInfo ri) {
		super.render(ri);
		for(BulletEffect be: effects) {
			be.render(ri);
		}
	}
	
	public void update(UpdateInfo ui) {
		super.update(ui);
		Iterator<BulletEffect> it = effects.iterator();
		while(it.hasNext()) {
			BulletEffect be = it.next();
			be.update(ui);
			if(!be.isAlive()) {
				it.remove();
			}
		}
	}
	
	@Override
	public boolean isFinished() {
		return effects.size() == 0;
	}

	@Override
	public void finishAttack() {
		effects.clear();
	}

	@Override
	public void startAttack(SKAssetManager assets, DrackActorNode unit, DrackActorNode target) {
		Vector3 start = turretPosition(unit);
		Vector3 end = target.currentPosition();
		
		BulletEffect effect = new BulletEffect(assets, start, end);
		effects.add(effect);
		
		System.out.println("Start attack");
	}

}

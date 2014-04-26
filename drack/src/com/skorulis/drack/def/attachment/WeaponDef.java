package com.skorulis.drack.def.attachment;

import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.unit.composite.Weapon;

public class WeaponDef extends HullAttachmentDef {

	public enum WeaponType {
		WEAPON_LASER,
		WEAPON_BULLET,
		WEAPON_MISSILE
	}
	
	public Vector3 turretLoc;
	public WeaponType type;
	
	public WeaponDef(String name) {
		super(name);
		this.attachmentClass = Weapon.class;
	}

}

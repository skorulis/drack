package com.skorulis.drack.def.unit;

import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.unit.composite.Weapon;

public class WeaponDef extends HullAttachmentDef {

	public Vector3 turretLoc;
	
	public WeaponDef(String name) {
		super(name);
		this.attachmentClass = Weapon.class;
	}

}

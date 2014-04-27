package com.skorulis.drack.unit.composite;

import java.util.Set;
import com.skorulis.drack.composite.AttachmentContainer;
import com.skorulis.drack.composite.CompositeObject;
import com.skorulis.drack.def.unit.CompositeUnitDef;
import com.skorulis.drack.def.unit.UnitDef;
import com.skorulis.drack.unit.Unit;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.UpdateInfo;

public class CompositeUnit extends Unit implements CompositeObject {

	private AttachmentContainer attContainer;
	
	public CompositeUnit() {
		attContainer = new AttachmentContainer(this);
	}
	
	public CompositeUnitDef compDef() {
		return (CompositeUnitDef) this.def;
	}
	
	public void setDef(UnitDef def) {
		super.setDef(def);
		attContainer.setHardPoints(compDef().hull.hardPoints);
	}
	
	public AttachmentContainer attachmentContainer() {
		return attContainer;
	}
	
	public void render(RenderInfo ri) {
		super.render(ri);
		attContainer.render(ri);
	}
	
	public void update(UpdateInfo ui) {
		super.update(ui);
		attContainer.update(ui);
	}
	
	public Set<Weapon> allWeapons() {
		return attContainer.allWeapons();
	}

}

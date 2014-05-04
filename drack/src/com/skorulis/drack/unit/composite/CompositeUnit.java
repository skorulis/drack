package com.skorulis.drack.unit.composite;

import java.util.Set;

import com.skorulis.drack.actor.attachments.Weapon;
import com.skorulis.drack.actor.unit.Unit;
import com.skorulis.drack.composite.AttachmentContainer;
import com.skorulis.drack.composite.CompositeObject;
import com.skorulis.drack.def.unit.CompositeUnitDef;
import com.skorulis.drack.def.unit.UnitDef;
import com.skorulis.drack.serialisation.LoadData;
import com.skorulis.drack.serialisation.unit.CompositeUnitJson;
import com.skorulis.drack.serialisation.unit.UnitJson;
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
	
	public void load(UnitJson json, LoadData ld) {
		super.load(json, ld);
		CompositeUnitJson cuj = (CompositeUnitJson) json;
		attContainer.load(cuj.attachments, ld);
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
	
	public UnitJson getSerialisation() {
		CompositeUnitJson json = new CompositeUnitJson();
		setBasicJsonFields(json);
		json.attachments = attContainer.getSerialisation();
		return json;
	}

}

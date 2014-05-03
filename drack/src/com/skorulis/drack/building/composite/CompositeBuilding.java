package com.skorulis.drack.building.composite;

import java.util.Set;
import com.skorulis.drack.building.Building;
import com.skorulis.drack.composite.AttachmentContainer;
import com.skorulis.drack.composite.CompositeObject;
import com.skorulis.drack.def.building.BuildingDef;
import com.skorulis.drack.def.building.CompositeBuildingDef;
import com.skorulis.drack.serialisation.LoadData;
import com.skorulis.drack.serialisation.building.BuildingJson;
import com.skorulis.drack.serialisation.building.CompositeBuildingJson;
import com.skorulis.drack.unit.composite.Weapon;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.UpdateInfo;

public class CompositeBuilding extends Building implements CompositeObject {

	private AttachmentContainer attContainer;
	
	public CompositeBuilding() {
		attContainer = new AttachmentContainer(this);
	}
	
	public void load(BuildingJson json, LoadData ld) {
		super.load(json, ld);
		CompositeBuildingJson cbj = (CompositeBuildingJson) json;
		attContainer.load(cbj.attachments, ld);
	}
	
	public AttachmentContainer attachmentContainer() {
		return attContainer;
	}
	
	public CompositeBuildingDef compDef() {
		return (CompositeBuildingDef) this.def;
	}
	
	public void setDef(BuildingDef def) {
		super.setDef(def);
		attContainer.setHardPoints(compDef().hardPoints);
	}
	
	public void render(RenderInfo ri) {
		super.render(ri);
		attContainer.render(ri);
	}
	
	public void update(UpdateInfo ui) {
		super.update(ui);
		attContainer.update(ui);
	}
	
	public CompositeBuildingJson getSerialisation() {
		CompositeBuildingJson json = new CompositeBuildingJson();
		setBasicJsonFields(json);
		json.attachments = attContainer.getSerialisation();
		return json;
	}
	
	public Set<Weapon> allWeapons() {
		return attContainer.allWeapons();
	}
	
}

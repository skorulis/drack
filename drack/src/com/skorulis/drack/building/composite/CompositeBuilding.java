package com.skorulis.drack.building.composite;

import com.skorulis.drack.building.Building;
import com.skorulis.drack.composite.AttachmentContainer;
import com.skorulis.drack.composite.CompositeObject;
import com.skorulis.drack.def.building.BuildingDef;
import com.skorulis.drack.def.building.CompositeBuildingDef;

public class CompositeBuilding extends Building implements CompositeObject {

	private AttachmentContainer attContainer;
	
	public CompositeBuilding() {
		attContainer = new AttachmentContainer(this);
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
	
}

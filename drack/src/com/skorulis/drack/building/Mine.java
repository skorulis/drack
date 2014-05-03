package com.skorulis.drack.building;

import com.skorulis.drack.def.ResourceDef;
import com.skorulis.drack.resource.ResourceBatch;
import com.skorulis.drack.resource.ResourceQuantity;
import com.skorulis.drack.serialisation.LoadData;
import com.skorulis.drack.serialisation.building.BuildingJson;
import com.skorulis.drack.serialisation.building.MineBuildingJson;

public class Mine extends Building {

	private ResourceBatch resources;
	
	public Mine() {
		resources = new ResourceBatch();
	}
	
	public void load(BuildingJson json, LoadData ld) {
		super.load(json, ld);
		MineBuildingJson mbj = (MineBuildingJson) json;
		resources = new ResourceBatch(mbj.resources, ld.def);
	}
	
	public ResourceBatch mine(int rate) {
		ResourceQuantity rq = chooseRandom();
		return new ResourceBatch(new ResourceQuantity(rq.resource(), rq.quantity() * rate));
	}
	
	public void addResource(ResourceQuantity rq) {
		resources.add(rq);
	}
	
	public void addResource(ResourceDef def, int qty) {
		ResourceQuantity rq = new ResourceQuantity(def, qty);
		addResource(rq);
	}
	
	private ResourceQuantity chooseRandom() {
		int index = (int) (Math.random() * resources.count());
		return resources.allResources().get(index);
	}
	
	public BuildingJson getSerialisation() {
		MineBuildingJson json = new MineBuildingJson();
		setBasicJsonFields(json);
		json.resources = resources.getSerialisation();
		return json;
	}
	
}

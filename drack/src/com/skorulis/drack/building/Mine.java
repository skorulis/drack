package com.skorulis.drack.building;

import com.skorulis.drack.def.ResourceDef;
import com.skorulis.drack.resource.ResourceBatch;
import com.skorulis.drack.resource.ResourceQuantity;

public class Mine extends Building {

	private ResourceBatch resources;
	
	public Mine() {
		resources = new ResourceBatch();
	}
	
	public ResourceBatch mine(float rate) {
		ResourceBatch batch = new ResourceBatch();
		for(ResourceQuantity res : resources.allResources()) {
			ResourceQuantity amount = new ResourceQuantity(res.resource(), res.quantity() * rate);
			batch.add(amount);
		}
		return batch;
	}
	
	public void addResource(ResourceQuantity rq) {
		resources.add(rq);
	}
	
	public void addResource(ResourceDef def, float qty) {
		ResourceQuantity rq = new ResourceQuantity(def, qty);
		addResource(rq);
	}
	
}

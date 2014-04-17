package com.skorulis.drack.building;

import com.skorulis.drack.def.ResourceDef;
import com.skorulis.drack.resource.ResourceBatch;
import com.skorulis.drack.resource.ResourceQuantity;

public class Mine extends Building {

	private ResourceBatch resources;
	
	public Mine() {
		resources = new ResourceBatch();
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
	
}

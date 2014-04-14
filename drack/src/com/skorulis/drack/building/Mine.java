package com.skorulis.drack.building;

import java.util.ArrayList;

import com.skorulis.drack.resource.ResourceBatch;
import com.skorulis.drack.resource.ResourceQuantity;

public class Mine extends Building {

	private ArrayList<ResourceQuantity> resources;
	
	public Mine() {
		resources = new ArrayList<ResourceQuantity>();
	}
	
	public ResourceBatch mine(float rate) {
		ResourceBatch batch = new ResourceBatch();
		for(ResourceQuantity res : resources) {
			ResourceQuantity amount = new ResourceQuantity(res.resource(), res.quantity() * rate);
			batch.add(amount);
		}
		return batch;
	}
	
}

package com.skorulis.drack.building;

import java.util.ArrayList;

import com.skorulis.drack.resource.ResourceQuantity;

public class Mine extends Building {

	private ArrayList<ResourceQuantity> resources;
	
	public Mine() {
		resources = new ArrayList<ResourceQuantity>();
	}
	
	public ResourceQuantity mine(float rate) {
		ResourceQuantity res = resources.get(0);
		return new ResourceQuantity(res.resource(), res.quantity() * rate);
	}
	
}

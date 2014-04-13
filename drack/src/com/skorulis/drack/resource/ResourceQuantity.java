package com.skorulis.drack.resource;

import com.skorulis.drack.def.ResourceDef;

public class ResourceQuantity {

	private ResourceDef resourceDef;
	private float quantity;
	
	public ResourceQuantity(ResourceDef def, float quantity) {
		this.resourceDef = def;
		this.quantity = quantity;
	}
	
	public ResourceDef resource() {
		return resourceDef;
	}
	
	public float quantity() {
		return quantity;
	}
	
}
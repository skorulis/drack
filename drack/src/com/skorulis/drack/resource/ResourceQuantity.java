package com.skorulis.drack.resource;

import com.skorulis.drack.def.ResourceDef;

public class ResourceQuantity {

	private final ResourceDef resourceDef;
	private int quantity;
	
	public ResourceQuantity(ResourceDef def, int quantity) {
		this.resourceDef = def;
		this.quantity = quantity;
	}
	
	public ResourceDef resource() {
		return resourceDef;
	}
	
	public int quantity() {
		return quantity;
	}
	
	public void add(int quantity) {
		this.quantity += quantity;
	}
	
	public void add(ResourceQuantity resQ) {
		this.quantity += resQ.quantity;
	}
	
	public String displayText() {
		return "+" + quantity + " " + resourceDef.name();
	}
}

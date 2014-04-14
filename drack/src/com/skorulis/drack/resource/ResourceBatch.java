package com.skorulis.drack.resource;

import java.util.ArrayList;
import java.util.HashMap;

public class ResourceBatch {
	
	private HashMap<String, ResourceQuantity> resources;
	
	public ResourceBatch() {
		resources = new HashMap<String, ResourceQuantity>();
	}
	
	public void add(ResourceQuantity res) {
		ResourceQuantity r = resources.get(res.resource().name());
		if(r == null) {
			resources.put(res.resource().name(), res);
		} else {
			r.add(res);
		}
	}
	
	public float quantity(String resource) {
		ResourceQuantity r = resources.get(resource);
		if(r == null) {
			return 0;
		}
		return r.quantity();
	}
	
	public ArrayList<ResourceQuantity> allResources() {
		return new ArrayList<ResourceQuantity>(resources.values());
	}
	
}

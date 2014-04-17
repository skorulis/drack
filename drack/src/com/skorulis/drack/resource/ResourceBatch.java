package com.skorulis.drack.resource;

import java.util.ArrayList;
import java.util.HashMap;

public class ResourceBatch {
	
	private HashMap<String, ResourceQuantity> resources;
	private int count;
	private int totalQuantity;
	
	public ResourceBatch() {
		resources = new HashMap<String, ResourceQuantity>();
		count = 0;
		totalQuantity = 0;
	}
	
	public ResourceBatch(ResourceQuantity rq) {
		this();
		add(rq);
	}
	
	public void add(ResourceQuantity res) {
		ResourceQuantity r = resources.get(res.resource().name());
		if(r == null) {
			resources.put(res.resource().name(), res);
			count ++;
		} else {
			r.add(res);
		}
		totalQuantity += res.quantity();
	}
	
	public void add(ResourceBatch batch) {
		ArrayList<ResourceQuantity> all = batch.allResources();
		for(ResourceQuantity rq : all) {
			add(rq);
		}
	}
	
	public int quantity(String resource) {
		ResourceQuantity r = resources.get(resource);
		if(r == null) {
			return 0;
		}
		return r.quantity();
	}
	
	public ArrayList<ResourceQuantity> allResources() {
		return new ArrayList<ResourceQuantity>(resources.values());
	}
	
	public int totalQuantity() {
		return totalQuantity;
	}
	
	public int count() {
		return count;
	}
	
	public String toString() {
		return "Batch " + resources.size();
	}
	
}

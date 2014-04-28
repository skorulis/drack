package com.skorulis.drack.resource;

import java.util.ArrayList;
import java.util.HashMap;
import com.skorulis.drack.def.DefManager;
import com.skorulis.drack.serialisation.ResourceBatchJson;

public class ResourceBatch {
	
	private HashMap<String, ResourceQuantity> resources;
	private int count;
	private int totalQuantity;
	private int maxQuantity;
	
	public ResourceBatch() {
		resources = new HashMap<String, ResourceQuantity>();
		maxQuantity = 0;
		clear();
	}
	
	public ResourceBatch(ResourceBatchJson json, DefManager def) {
		this();
		for(String s : json.values.keySet()) {
			ResourceQuantity rq = new ResourceQuantity(def.getResource(s), json.values.get(s));
			add(rq);
		}
	}
	
	public ResourceBatch(ResourceQuantity rq) {
		this();
		add(rq);
	}
	
	public int add(ResourceQuantity res) {
		ResourceQuantity r = resources.get(res.resource().name());
		int amount = res.quantity();
		if(maxQuantity > 0) {
			int space =  maxQuantity - totalQuantity;
			amount = Math.min(space, amount);
			System.out.println("Adding " + amount);
			
			if(amount <= 0) {
				return 0;
			}
		}
		
		
		if(r == null) {
			resources.put(res.resource().name(), new ResourceQuantity(res.resource(), amount));
			count ++;
		} else {
			r.add(amount);
		}
		totalQuantity += amount;
		return amount;
	}
	
	public int add(ResourceBatch batch) {
		int totalAdded = 0;
		ArrayList<ResourceQuantity> all = batch.allResources();
		for(ResourceQuantity rq : all) {
			totalAdded+=add(rq);
		}
		return totalAdded;
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
	
	public void clear() {
		this.resources.clear();
		count = 0;
		totalQuantity = 0;
	}
	
	public int totalQuantity() {
		return totalQuantity;
	}
	
	public int count() {
		return count;
	}
	
	public int maxQuantity() {
		return maxQuantity;
	}
	
	public void setMaxQuantity(int max) {
		this.maxQuantity = max;
	}
	
	public String toString() {
		return "Batch " + resources.size();
	}
	
	public boolean full() {
		if(this.maxQuantity <= 0) {
			return false;
		}
		return totalQuantity >= maxQuantity;
	}
	
	public ResourceBatchJson getSerialisation() {
		ResourceBatchJson ret = new ResourceBatchJson();
		
		for(ResourceQuantity rq : allResources()) {
			ret.values.put(rq.resource().name(), rq.quantity());
		}
		
		return ret;
	}
	
}

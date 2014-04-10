package com.skorulis.gdx;

import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;


public class SKAssetManager extends AssetManager {

	public SKAssetManager() {
		
	}
	
	public <T> void addAsset (final String fileName, Class<T> type, T asset) {
		super.addAsset(fileName, type, asset);
	}
	
	public void addAllModels(Map<String, Model> models) {
		for(String s : models.keySet()) {
        	Model m = models.get(s);
        	addAsset(s, Model.class, m);
        }
	}
	
	public synchronized <T> void loadAll(Set<String> items,Class<T> type) {
		for(String s : items) {
			super.load(s, type);
		}
	}
	
	
}

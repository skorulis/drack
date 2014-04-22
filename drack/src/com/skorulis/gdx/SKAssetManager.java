package com.skorulis.gdx;

import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;


public class SKAssetManager extends AssetManager {
	
	private static String MODEL_DIR = "data/models/";
	private static String TEXTURE_DIR = "data/textures/";
	private static String MODEL_SUFFIX = "g3db";
	
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
	
	public synchronized <T> void loadAllModels(Set<String> items) {
		for(String s : items) {
			String modelName = MODEL_DIR + s + "." + MODEL_SUFFIX;
			super.load(modelName, Model.class);
		}
	}
	
	public synchronized <T> void loadAllTextures(Set<String> items) {
		for(String s : items) {
			String textureName = TEXTURE_DIR + s;
			super.load(textureName, Texture.class);
		}
	}
	
	public synchronized Model getModel(String shortName) {
		String modelName = MODEL_DIR + shortName + "." + MODEL_SUFFIX;
		return super.get(modelName, Model.class);
	}
	
	public synchronized Texture getTexture(String shortName) {
		String textureName = TEXTURE_DIR + shortName;
		return super.get(textureName, Texture.class);
	}
	
	
}

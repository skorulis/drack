package com.skorulis.drack.def;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

public class DefManager {

	public Map<String, Model> buildModels(AssetManager assets) {
		HashMap<String, Model> ret = new HashMap<String, Model>();
		
		ModelBuilder builder = new ModelBuilder();
		Material material = new Material();
		Texture texture = assets.get("data/floor.png",Texture.class);
		material.set(new TextureAttribute(TextureAttribute.Diffuse, texture));
		Model blockModel = builder.createBox(1, 1.0f, 1, material, Usage.Position | Usage.Normal | Usage.TextureCoordinates);
		
		ret.put("block", blockModel);
		
		return ret;
	}
	
	public Set<String> allTextures() {
		HashSet<String> textures = new HashSet<String>();
		textures.add("data/floor.png");
		
		return textures;
	}
	
	public Set<String> allModels() {
		HashSet<String> models = new HashSet<String>();
		models.add("data/cube1.g3db");
		models.add("data/sphere.g3db");
		models.add("data/hull1.g3db");
		models.add("data/cone.g3db");
		
		return models;
	}
	
	
	
}

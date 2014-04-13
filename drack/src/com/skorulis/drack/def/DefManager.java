package com.skorulis.drack.def;

import java.util.ArrayList;
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
import com.skorulis.drack.building.CommandCentre;
import com.skorulis.drack.building.Mine;
import com.skorulis.drack.building.Tower;
import com.skorulis.drack.building.Tree;
import com.skorulis.drack.building.Vault;

public class DefManager {

	public Map<String, BuildingDef> buildings;
	public Map<String, ResourceDef> resources;
	
	public DefManager() {
		buildings = new HashMap<String, BuildingDef>();
		resources = new HashMap<String, ResourceDef>();
		createResources();
		createBuildings();
	}
	
	private void createBuildings() {
		BuildingDef def = new BuildingDef("command");
		def.buildingClass = CommandCentre.class;
		def.modelName = "data/cone.g3db";
		def.isBuildable = true;
		addDef(def);
		
		def = new BuildingDef("tree");
		def.buildingClass = Tree.class;
		def.modelName = "data/tree1.g3db";
		addDef(def);
		
		def = new BuildingDef("vault");
		def.width = def.depth = 2;
		def.buildingClass = Vault.class;
		def.modelName = "data/vault.g3db";
		def.replacesTerrain = true;
		def.isBuildable = true;
		
		addDef(def);
		
		def = new BuildingDef("tower");
		def.width = def.depth = 2;
		def.buildingClass = Tower.class;
		def.modelName = "data/tower.g3db";
		def.isBuildable = true;
		addDef(def);
		
		def = new BuildingDef("mine");
		def.buildingClass = Mine.class;
		def.modelName = "data/mine.g3db";
		def.replacesTerrain = true;
		addDef(def);
		
	}
	
	private void createResources() {
		ResourceDef resource = new ResourceDef("iron");
		
		addDef(resource);
	}
	
	private void addDef(BaseDef def) {
		if(def instanceof BuildingDef) {
			buildings.put(def.name(), (BuildingDef)def);
		} else if(def instanceof ResourceDef) {
			resources.put(def.name(), (ResourceDef)def);
		}
		else {
			throw new IllegalArgumentException("Don't know where to put " + def);
		}
	}
	
	public Map<String, Model> buildModels(AssetManager assets) {
		HashMap<String, Model> ret = new HashMap<String, Model>();
		
		ret.put("block", buildGroundBlock(assets));
		
		return ret;
	}
	
	private Model buildGroundBlock(AssetManager assets) {
		ModelBuilder builder = new ModelBuilder();
		Material material = new Material();
		Texture texture = assets.get("data/floor.png",Texture.class);
		material.set(new TextureAttribute(TextureAttribute.Diffuse, texture));
		Model blockModel = builder.createBox(1, 1.0f, 1, material, Usage.Position | Usage.Normal | Usage.TextureCoordinates);
		return blockModel;
	}
	
	public BuildingDef getBuilding(String name) {
		BuildingDef def = buildings.get(name);
		if(def == null) {
			throw new IllegalArgumentException("No building named " + name);
		}
		return def;
	}
	
	public ResourceDef getResource(String name) {
		ResourceDef def = resources.get(name);
		if(def == null) {
			throw new IllegalArgumentException("No resource named " + name);
		}
		return def;
	}
	
	public Set<String> allTextures() {
		HashSet<String> textures = new HashSet<String>();
		textures.add("data/floor.png");
		textures.add("data/field.png");
		textures.add("data/field2.png");
		return textures;
	}
	
	public Set<String> allTextureAtlases() {
		HashSet<String> textures = new HashSet<String>();
		textures.add("data/ui.png.atlas");
		return textures;
	}
	
	public Set<String> allModels() {
		HashSet<String> models = new HashSet<String>();

		for(BuildingDef d : buildings.values()) {
			models.add(d.modelName);
		}
		
		models.add("data/cube1.g3db");
		models.add("data/sphere.g3db");
		models.add("data/hull1.g3db");
		models.add("data/field1.g3db");
		models.add("data/corner.g3db");
		models.add("data/models/craft1.g3db");
		models.add("data/wall_corner.g3db");
		
		return models;
	}
	
	public ArrayList<BuildingDef> buildableBuildings() {
		ArrayList<BuildingDef> ret = new ArrayList<BuildingDef>();
		for(BuildingDef def : buildings.values()) {
			if(def.isBuildable()) {
				ret.add(def);
			}
		}
		return ret;
	}
	
	
	
}

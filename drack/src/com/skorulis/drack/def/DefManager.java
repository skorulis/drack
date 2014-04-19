package com.skorulis.drack.def;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.skorulis.drack.building.Barracks;
import com.skorulis.drack.building.Building;
import com.skorulis.drack.building.CommandCentre;
import com.skorulis.drack.building.DungeonTower;
import com.skorulis.drack.building.Mine;
import com.skorulis.drack.building.Tower;
import com.skorulis.drack.building.Tree;
import com.skorulis.drack.building.Vault;
import com.skorulis.drack.def.unit.CompositeUnitDef;
import com.skorulis.drack.ui.building.BarracksUI;
import com.skorulis.drack.ui.building.MineUI;

public class DefManager {

	private Map<Class<? extends BaseDef>, Map<String, ? extends BaseDef>> typeMapping;
	private Map<String, BuildingDef> buildings;
	private Map<String, ResourceDef> resources;
	private Map<String, HullDef> hulls;
	private Map<String, UnitDef> units;
	private Map<String, CompositeUnitDef> compositeUnits;
	
	public DefManager() {
		buildings = new HashMap<String, BuildingDef>();
		resources = new HashMap<String, ResourceDef>();
		units = new HashMap<String, UnitDef>();
		compositeUnits = new HashMap<String, CompositeUnitDef>();
		hulls = new HashMap<String, HullDef>();
		
		typeMapping = new HashMap<Class<? extends BaseDef>, Map<String,? extends BaseDef>>();
		typeMapping.put(BuildingDef.class, buildings);
		typeMapping.put(ResourceDef.class, resources);
		typeMapping.put(UnitDef.class, units);
		typeMapping.put(HullDef.class, hulls);
		typeMapping.put(CompositeUnitDef.class, compositeUnits);
		
		createResources();
		createBuildings();
		createUnits();
		createCompositeUnits();
		createHulls();
		createCompositeUnits();
	}
	
	private void createHulls() {
		HullDef hull = new HullDef("cube");
		hull.modelName = "cube_drone";
		
		addDef(hull);
	}
	
	private void createCompositeUnits() {
		
	}
	
	private void createUnits() {
		UnitDef def = new UnitDef("avatar");
		def.setModelName("craft1");
		def.setSpeed(10);
		addDef(def);
		
		def = new UnitDef("truck");
		def.setModelName("truck");
		def.setResourceCapacity(5);
		def.setSpeed(5);
		addDef(def);
		
		def = new UnitDef("enemy");
		def.setModelName("cube_experiment");
		def.setResourceCapacity(5);
		def.setSpeed(5);
		addDef(def);
	}
	
	private void createBuildings() {
		BuildingDef def = new BuildingDef("command");
		def.buildingClass = CommandCentre.class;
		def.modelName = "cone";
		def.isBuildable = true;
		addDef(def);
		
		def = new BuildingDef("tree");
		def.buildingClass = Tree.class;
		def.modelName = "tree1";
		addDef(def);
		
		def = new BuildingDef("vault");
		def.width = def.depth = 2;
		def.buildingClass = Vault.class;
		def.modelName = "vault";
		def.replacesTerrain = true;
		def.isBuildable = true;
		
		addDef(def);
		
		def = new BuildingDef("tower");
		def.width = def.depth = 2;
		def.buildingClass = Tower.class;
		def.modelName = "tower";
		def.isBuildable = true;
		addDef(def);
		
		def = new BuildingDef("mine");
		def.buildingClass = Mine.class;
		def.uiClass = MineUI.class;
		def.modelName = "mine";
		def.replacesTerrain = true;
		addDef(def);
		
		def = new BuildingDef("smelter");
		def.buildingClass = Building.class;
		def.modelName = "cone";
		def.isBuildable = true;
		addDef(def);
		
		def = new BuildingDef("barracks");
		def.buildingClass = Barracks.class;
		def.uiClass = BarracksUI.class;
		def.modelName = "house";
		def.width = def.depth = 2;
		def.isBuildable = true;
		addDef(def);
		
		def = new BuildingDef("wall");
		def.buildingClass = Building.class;
		def.modelName = "wall_corner";
		def.isBuildable = true;
		addDef(def);
		
		def = new BuildingDef("round tower");
		def.buildingClass = DungeonTower.class;
		def.modelName = "round_tower";
		def.width = def.depth = 2;
		addDef(def);
		
	}
	
	private void createResources() {
		ResourceDef resource = new ResourceDef("iron");
		addDef(resource);
		
		resource = new ResourceDef("gold");
		addDef(resource);
		
		resource = new ResourceDef("copper");
		addDef(resource);
		
		resource = new ResourceDef("silver");
		addDef(resource);
		
		resource = new ResourceDef("uranium");
		addDef(resource);
		
		resource = new ResourceDef("titanium");
		addDef(resource);
		
		resource = new ResourceDef("mercury");
		addDef(resource);
		
		resource = new ResourceDef("cobalt");
		addDef(resource);
	}
	
	private <T extends BaseDef> void addDef(T def) {
		@SuppressWarnings("unchecked")
		Map<String, T> map = (Map<String, T>) typeMapping.get(def.getClass());
		if(map == null) {
			throw new IllegalArgumentException("Don't know where to put " + def);
		}
		map.put(def.name(), def);
	}

	public BuildingDef getBuilding(String name) {
		return get(name, BuildingDef.class);
	}
	
	public ResourceDef getResource(String name) {
		return get(name,ResourceDef.class);
	}
	
	public UnitDef getUnit(String name) {
		return get(name,UnitDef.class);
	}
	
	public CompositeUnitDef getCompositeUnit(String name) {
		return get(name,CompositeUnitDef.class);
	}
	
	public <T extends BaseDef> T get(String name, Class<T> type) {
		@SuppressWarnings("unchecked")
		HashMap<String, T> map = (HashMap<String, T>) typeMapping.get(type);
		
		T def = map.get(name);
		if(def == null) {
			throw new IllegalArgumentException("No " + type + " named " + name);
		}
		
		return def;
	}
	
	public Set<String> allTextures() {
		HashSet<String> textures = new HashSet<String>();

		return textures;
	}
	
	public Set<String> allTextureAtlases() {
		HashSet<String> textures = new HashSet<String>();

		return textures;
	}
	
	public Set<String> allModels() {
		HashSet<String> models = new HashSet<String>();

		for(BuildingDef d : buildings.values()) {
			models.add(d.modelName);
		}
		for(UnitDef d : units.values()) {
			models.add(d.modelName());
		}
		
		for(HullDef d : hulls.values()) {
			models.add(d.modelName);
		}
		
		models.add("cube1");
		models.add("sphere");
		models.add("hull1");
		models.add("field1");
		models.add("corner");
		models.add("wall_corner");
		models.add("block");
		
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

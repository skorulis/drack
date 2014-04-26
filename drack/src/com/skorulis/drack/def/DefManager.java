package com.skorulis.drack.def;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.building.Barracks;
import com.skorulis.drack.building.Building;
import com.skorulis.drack.building.CommandCentre;
import com.skorulis.drack.building.DungeonTower;
import com.skorulis.drack.building.Mine;
import com.skorulis.drack.building.Tower;
import com.skorulis.drack.building.Tree;
import com.skorulis.drack.building.TurretBuilding;
import com.skorulis.drack.building.Vault;
import com.skorulis.drack.def.attachment.HullAttachmentDef;
import com.skorulis.drack.def.attachment.HullPointDef;
import com.skorulis.drack.def.attachment.WeaponDef;
import com.skorulis.drack.def.attachment.HullPointDef.HullPointType;
import com.skorulis.drack.def.attachment.WeaponDef.WeaponType;
import com.skorulis.drack.def.building.BuildingDef;
import com.skorulis.drack.def.unit.CompositeUnitDef;
import com.skorulis.drack.def.unit.HullDef;
import com.skorulis.drack.def.unit.BasicUnitDef;
import com.skorulis.drack.effects.BulletEffect;
import com.skorulis.drack.effects.LaserEffect;
import com.skorulis.drack.ui.building.BarracksUI;
import com.skorulis.drack.ui.building.CommandUI;
import com.skorulis.drack.ui.building.MineUI;
import com.skorulis.drack.ui.building.TurretUI;

public class DefManager {

	private Map<Class<? extends BaseDef>, Map<String, ? extends BaseDef>> typeMapping;
	private Map<String, BuildingDef> buildings;
	private Map<String, ResourceDef> resources;
	private Map<String, HullDef> hulls;
	private Map<String, BasicUnitDef> units;
	private Map<String, CompositeUnitDef> compositeUnits;
	private Map<String, HullAttachmentDef> attachments;
	
	public DefManager() {
		buildings = new HashMap<String, BuildingDef>();
		resources = new HashMap<String, ResourceDef>();
		units = new HashMap<String, BasicUnitDef>();
		compositeUnits = new HashMap<String, CompositeUnitDef>();
		hulls = new HashMap<String, HullDef>();
		attachments = new HashMap<String, HullAttachmentDef>();
		
		typeMapping = new HashMap<Class<? extends BaseDef>, Map<String,? extends BaseDef>>();
		typeMapping.put(BuildingDef.class, buildings);
		typeMapping.put(ResourceDef.class, resources);
		typeMapping.put(BasicUnitDef.class, units);
		typeMapping.put(HullDef.class, hulls);
		typeMapping.put(CompositeUnitDef.class, compositeUnits);
		typeMapping.put(HullAttachmentDef.class, attachments);
		
		createAttachments();
		createWeapons();
		createResources();
		createBuildings();
		createUnits();
		createHulls();
		createCompositeUnits();
	}
	
	private void createWeapons() {
		WeaponDef def = new WeaponDef("gun");
		def.turretLoc = new Vector3(0,0,0.675f);
		def.modelName = "gun1";
		def.type = WeaponType.WEAPON_MISSILE;
		addDef(def);
		
		def = new WeaponDef("beam");
		def.turretLoc = new Vector3(0,0,0.675f);
		def.modelName = "beam1";
		def.type = WeaponType.WEAPON_LASER;
		addDef(def);
	}
	
	private void createAttachments() {
		
	}
	
	private void createHulls() {
		HullDef hull = new HullDef("cube");
		hull.modelName = "cube_drone";
		hull.baseSpeed =  10;
		hull.baseCapacity = 5;
		
		hull.addPoint(new HullPointDef(new Vector3(0.497f,0.7f,0), 270, HullPointType.SMALL));
		hull.addPoint(new HullPointDef(new Vector3(-0.497f,0.7f,0), 90, HullPointType.SMALL));
		
		addDef(hull);
	}
	
	private void createCompositeUnits() {
		CompositeUnitDef def = new CompositeUnitDef("base");
		def.hull = getHull("cube");
		addDef(def);
	}
	
	private void createUnits() {
		BasicUnitDef def = new BasicUnitDef("avatar");
		def.setModelName("craft1");
		def.setSpeed(10);
		addDef(def);
		
		def = new BasicUnitDef("truck");
		def.setModelName("truck");
		def.setResourceCapacity(5);
		def.setSpeed(5);
		addDef(def);
		
		def = new BasicUnitDef("enemy");
		def.setModelName("cube_experiment");
		def.setResourceCapacity(5);
		def.setSpeed(5);
		addDef(def);
	}
	
	private void createBuildings() {
		BuildingDef def = new BuildingDef("command");
		def.buildingClass = CommandCentre.class;
		def.uiClass = CommandUI.class;
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
		
		def = new BuildingDef("turret");
		def.buildingClass = TurretBuilding.class;
		def.uiClass = TurretUI.class;
		def.modelName = "turret";
		def.isBuildable = true;
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
		Map<String, T> map = (Map<String, T>) typeMapping.get(def.getTypeClass());
		if(map == null) {
			throw new IllegalArgumentException("Don't know where to put " + def);
		}
		map.put(def.name(), def);
	}
	
	public HullAttachmentDef getAttachment(String name) {
		return get(name,HullAttachmentDef.class);
	}
	
	public WeaponDef getWeapon(String name) {
		return (WeaponDef) get(name,HullAttachmentDef.class);
	}
	
	public HullDef getHull(String name) {
		return get(name,HullDef.class);
	}

	public BuildingDef getBuilding(String name) {
		return get(name, BuildingDef.class);
	}
	
	public ResourceDef getResource(String name) {
		return get(name,ResourceDef.class);
	}
	
	public BasicUnitDef getUnit(String name) {
		return get(name,BasicUnitDef.class);
	}
	
	public CompositeUnitDef getCompositeUnit(String name) {
		return get(name,CompositeUnitDef.class);
	}
	
	public <T extends BaseDef> T get(String name, Class<T> type) {
		@SuppressWarnings("unchecked")
		HashMap<String, T> map = (HashMap<String, T>) typeMapping.get(type);
		
		T def = map.get(name);
		if(def == null) {
			System.out.println("Map contains: " + map.keySet());
			throw new IllegalArgumentException("No " + type + " named " + name);
		}
		
		return def;
	}
	
	public Set<String> allTextures() {
		HashSet<String> textures = new HashSet<String>();
		textures.addAll(LaserEffect.textures());
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
		for(BasicUnitDef d : units.values()) {
			models.add(d.modelName());
		}
		
		for(HullDef d : hulls.values()) {
			models.add(d.modelName);
		}
		
		for(HullAttachmentDef d: attachments.values()) {
			models.add(d.modelName);
		}
		models.addAll(BulletEffect.models());
		
		models.add("cube1");
		models.add("cube_transparent");
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
	
	public Collection<HullAttachmentDef> allAttachments() {
		return attachments.values();
	}
	
	
	
}

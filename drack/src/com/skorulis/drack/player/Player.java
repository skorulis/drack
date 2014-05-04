package com.skorulis.drack.player;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.actor.building.Building;
import com.skorulis.drack.actor.unit.Unit;
import com.skorulis.drack.def.DefManager;
import com.skorulis.drack.resource.ResourceBatch;
import com.skorulis.drack.serialisation.PlayerJson;
import com.skorulis.scene.SceneHelper;
import com.skorulis.scene.SceneNode;
import com.skorulis.scene.UpdateInfo;

public class Player {

	private final String playerId;
	private ResourceBatch resources;
	private Unit controllingUnit;
	private Set<Unit> ownedUnits;
	private Set<Building> ownedBuildings;
	private PlayerDelegate delegate;
	
	public Player(String playerId) {
		this.playerId = playerId;
		resources = new ResourceBatch();
		ownedUnits = new HashSet<Unit>();
		ownedBuildings = new HashSet<Building>();
	}
	
	public Player(PlayerJson json, DefManager def) {
		this.playerId = json.playerId;
		this.resources = new ResourceBatch(json.resources,def);
		ownedUnits = new HashSet<Unit>();
		ownedBuildings = new HashSet<Building>();
	}
	
	public void addUnit(Unit unit) {
		this.ownedUnits.add(unit);
	}
	
	public void addBuilding(Building building) {
		ownedBuildings.add(building);
		building.setOwner(this);
	}
	
	public Unit controllUnit() {
		return controllingUnit;
	}
	
	public void setControllingUnit(Unit unit) {
		this.controllingUnit = unit;
	}
	
	public ResourceBatch resources() {
		return resources;
	}
	
	public Set<Unit> ownedUnits() {
		return ownedUnits;
	}
	
	public Unit freeUnit() {
		for(Unit u : ownedUnits) {
			if(u != controllingUnit) {
				return u;
			}
		}
		return null;
	}
	
	public Building findBuilding(String name, Vector3 near) {
		Building best = null;
		float bestDist = Float.MAX_VALUE;
		for(Building b: ownedBuildings) {
			if(b.def().name().equals(name)) {
				float dist = SceneHelper.dist(b, near);
				if(dist < bestDist) {
					best = b;
				}
			}
		}
		return best;
	}
	
	public void update(UpdateInfo ui) {
		Iterator<Unit> it = ownedUnits.iterator();
		while(it.hasNext()) {
			Unit u = it.next();
			if(!u.isAlive()) {
				it.remove();
			}
		}
	}
	
	public String playerId() {
		return playerId;
	}
	
	public PlayerJson getSerialisation() {
		PlayerJson json = new PlayerJson();
		json.playerId = playerId;
		json.resources = this.resources.getSerialisation();
		return json;
	}
	
	public void setDelegate(PlayerDelegate delegate) {
		this.delegate = delegate;
	}
	
	public void addResources(ResourceBatch batch, SceneNode at) {
		this.resources.add(batch);
		Vector3 loc = at.absTransform().getTranslation(new Vector3());
		this.delegate.resourcesAdded(this, loc, batch);
	}
	
}

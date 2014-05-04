package com.skorulis.drack.actor.building;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.actor.action.ActionContainer;
import com.skorulis.drack.actor.attachments.Weapon;
import com.skorulis.drack.def.building.BuildingDef;
import com.skorulis.drack.map.MapSquare;
import com.skorulis.drack.player.Player;
import com.skorulis.drack.resource.ResourceBatch;
import com.skorulis.drack.scene.DrackActorNode;
import com.skorulis.drack.serialisation.LoadData;
import com.skorulis.drack.serialisation.building.BuildingJson;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.IntersectionList;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.UpdateInfo;

public class Building implements DrackActorNode {

	protected ModelInstance buildingInstance;
	protected BuildingDef def;
	protected boolean beingPlaced;
	protected Player owner;
	protected Set<MapSquare> coveredSquares;
	protected ActionContainer actions;
	
	public Building() {
		coveredSquares = new HashSet<MapSquare>();
		actions = new ActionContainer(this);
	}
	
	public void load(BuildingJson json, LoadData ld) {
		actions.load(json.actions, ld);
	}
	
	public void loadModel(SKAssetManager assets) {
		buildingInstance = new ModelInstance(assets.getModel(def.modelName));
	}
	
	public void setTranslation(Vector3 translation) {
		buildingInstance.transform.setToTranslation(translation.x, 0, translation.z);
	}
	
	@Override
	public Matrix4 absTransform() {
		return buildingInstance.transform;
	}

	@Override
	public Matrix4 relTransform() {
		return buildingInstance.transform;
	}

	@Override
	public void render(RenderInfo ri) {
		ri.batch.render(buildingInstance,ri.environment);
	}

	@Override
	public boolean intersect(IntersectionList list) {
		return false;
	}

	@Override
	public void update(UpdateInfo info) {
		actions.update(info);
	}
	
	public BuildingDef def() {
		return def;
	}
	
	public void setDef(BuildingDef def) {
		this.def = def;
	}
	
	public ModelInstance buildingInstance() {
		return buildingInstance;
	}
	
	public void setOwner(Player player) {
		this.owner = player;
	}
	
	public Player owner() {
		return owner;
	}
	
	public void addCoveredSquare(MapSquare ms) {
		this.coveredSquares.add(ms);
	}
	
	public Set<MapSquare> coveredSquares() {
		return this.coveredSquares;
	}
	
	public boolean isAlive() {
		return true;
	}

	public BuildingJson getSerialisation() {
		BuildingJson json = new BuildingJson();
		setBasicJsonFields(json);
		return json;
	}
	
	protected void setBasicJsonFields(BuildingJson json) {
		json.defName = this.def.name();
		if(this.owner != null) {
			json.playerId = this.owner.playerId();
		}
		json.actions = actions.getSerialisation();
	}
	
	public MapSquare mainSquare() {
		for(MapSquare ms : coveredSquares) {
			if(ms.building() == this) {
				return ms;
			}
		}
		throw new IllegalStateException("Building does not have a main square " + this);
	}

	@Override
	public Set<Weapon> allWeapons() {
		return new HashSet<Weapon>();
	}

	@Override
	public ResourceBatch resources() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector3 currentPosition() {
		return absTransform().getTranslation(new Vector3());
	}

	@Override
	public void addResources(ResourceBatch batch) {
		
	}

	@Override
	public void takeDamage(float damage) {
		
	}
	
}

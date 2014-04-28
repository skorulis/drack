package com.skorulis.drack.building;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.skorulis.drack.def.building.BuildingDef;
import com.skorulis.drack.map.MapSquare;
import com.skorulis.drack.player.Player;
import com.skorulis.drack.serialisation.BuildingJson;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.SceneNode;
import com.skorulis.scene.UpdateInfo;

public class Building implements SceneNode{

	protected ModelInstance buildingInstance;
	protected BuildingDef def;
	protected boolean beingPlaced;
	protected Player owner;
	protected Set<MapSquare> coveredSquares;
	
	public Building() {
		coveredSquares = new HashSet<MapSquare>();
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
	public SceneNode intersect(Ray ray, Vector3 point) {
		return null;
	}

	@Override
	public void update(UpdateInfo info) {
		
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
		json.defName = this.def.name();
		if(this.owner != null) {
			json.playerId = this.owner.playerId();
		}
		
		return json;
	}
	
}

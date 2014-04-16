package com.skorulis.drack.unit;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.skorulis.drack.def.UnitDef;
import com.skorulis.drack.pathfinding.MapPath;
import com.skorulis.drack.player.Player;
import com.skorulis.drack.resource.ResourceBatch;
import com.skorulis.drack.resource.ResourceQuantity;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.SceneNode;

public class Unit implements SceneNode {

	private ModelInstance instance;
	private UnitAction action;
	
	private float speed = 10;
	private ResourceBatch resources;
	private UnitDelegate delegate;
	private Player owner;
	
	public Unit(SKAssetManager assets, Player owner, UnitDef def) {
		instance = new ModelInstance(assets.getModel(def.modelName));
		resources = new ResourceBatch();
		this.owner = owner;
		
		this.owner.addUnit(this);
	}
	
	public void setPath(MapPath path) {
		if(path == null || path.length() == 0) {
			return;
		}
		if(action != null && action instanceof MovementAction) {
			((MovementAction)action).setPath(path);
		} else {
			action = new MovementAction(this,path);
		}
	}
	
	@Override
	public Matrix4 absTransform() {
		return instance.transform;
	}

	@Override
	public Matrix4 relTransform() {
		return instance.transform;
	}

	@Override
	public void render(RenderInfo ri) {
		ri.batch.render(instance,ri.environment);
	}
	
	public void update(float delta) {
		if(action != null) {
			action.update(delta);
			if(action.finished()) {
				action = null;
			}
		}
	}

	@Override
	public SceneNode intersect(Ray ray, Vector3 point) {
		return null;
	}
	
	public Vector3 currentPosition() {
		if(action != null && action instanceof MovementAction) {
			return ((MovementAction) action).movingTo();
		}
		return absTransform().getTranslation(new Vector3());
	}
	
	public ResourceBatch resources() {
		return resources;
	}
	
	public ArrayList<ResourceQuantity> allResources() {
		return resources.allResources();
	}
	
	public void setAction(UnitAction action) {
		this.action = action;
	}
	
	public void addResources(ResourceBatch batch) {
		this.resources.add(batch);
		ArrayList<ResourceQuantity> all = batch.allResources();
		for(ResourceQuantity rq : all) {
			this.delegate.resourceAdded(this,rq);
		}
	}
	
	public void setDelegate(UnitDelegate delegate) {
		this.delegate = delegate;
	}
	
	public float speed() {
		return speed;
	}
	
	public Player owner() {
		return owner;
	}

}

package com.skorulis.drack.unit;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.skorulis.drack.def.unit.UnitDef;
import com.skorulis.drack.pathfinding.MapPath;
import com.skorulis.drack.player.Player;
import com.skorulis.drack.resource.ResourceBatch;
import com.skorulis.drack.resource.ResourceQuantity;
import com.skorulis.drack.unit.action.MovementAction;
import com.skorulis.drack.unit.action.UnitAction;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.SceneNode;
import com.skorulis.scene.UpdateInfo;

public class Unit implements SceneNode {

	private ModelInstance instance;
	private ArrayList<UnitAction> actions;
	
	private ResourceBatch resources;
	private UnitDelegate delegate;
	protected Player owner;
	protected UnitDef def;
	
	public Unit() {
		resources = new ResourceBatch();
		this.actions = new ArrayList<UnitAction>();
	}
	
	public Unit(SKAssetManager assets, Player owner, UnitDef def) {
		this();
		
		setDef(def);
		setOwner(owner);
		loadAssets(assets);
	}
	
	public void loadAssets(SKAssetManager assets) { 
		instance = new ModelInstance(assets.getModel(def.modelName()));
	}
	
	public void setOwner(Player owner) {
		this.owner = owner;
		this.owner.addUnit(this);
	}
	
	public void setDef(UnitDef def) {
		this.def = def;
		resources.setMaxQuantity(def.resourceCapacity());
	}
	
	public void setPath(MapPath path) {
		if(path == null || path.length() == 0) {
			return;
		}
		UnitAction action = currentAction();
		if(action != null && action instanceof MovementAction) {
			((MovementAction)action).setPath(path);
		} else {
			UnitAction newAction = new MovementAction(this,path);
			addAction(newAction);
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
	
	public void update(UpdateInfo info) {
		UnitAction action = currentAction();
		if(action != null) {
			action.update(info.delta);
			if(action.finished()) {
				actions.remove(0);
				ArrayList<UnitAction> following = action.followingActions(info);
				if(following != null) {
					actions.addAll(0, following);
				}
			}
		}
	}

	@Override
	public SceneNode intersect(Ray ray, Vector3 point) {
		Vector3 center = absTransform().getTranslation(new Vector3());
		if(Intersector.intersectRaySphere(ray, center, 1, point)) {
			return this;
		}
		return null;
	}
	
	public Vector3 currentPosition() {
		UnitAction action = currentAction();
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
	
	public void addAction(UnitAction action) {
		if(action.shouldReplace()) {
			this.actions.clear();
		}
		this.actions.add(action);
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
		return def.speed();
	}
	
	public Player owner() {
		return owner;
	}
	
	public UnitAction currentAction() {
		if(actions.size() > 0) {
			return actions.get(0);
		}
		return null;
	}

}

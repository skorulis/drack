package com.skorulis.drack.unit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
import com.skorulis.drack.serialisation.UnitJson;
import com.skorulis.drack.ui.effects.HealthBar;
import com.skorulis.drack.unit.action.AttackAction;
import com.skorulis.drack.unit.action.FaceAction;
import com.skorulis.drack.unit.action.MovementAction;
import com.skorulis.drack.unit.action.UnitAction;
import com.skorulis.drack.unit.composite.Weapon;
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
	protected HealthBar healthBar;
	protected float currentHealth;
	
	public Unit() {
		resources = new ResourceBatch();
		this.actions = new ArrayList<UnitAction>();
		this.currentHealth = maxHealth();
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
			action.update(info);
			if(action.finished()) {
				action.stopAction();
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
			clearActions();
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
	
	public void clearActions() {
		UnitAction action = this.currentAction();
		if(action != null) {
			action.stopAction();
		}
		this.actions.clear();
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
	
	public int maxHealth() {
		return 100;
	}
	
	public float health() {
		return currentHealth;
	}
	
	public void setHealthBar(HealthBar healthBar) {
		this.healthBar = healthBar;
	}
	
	public void attack(Unit unit) {
		FaceAction face = new FaceAction(this, unit.currentPosition());
		this.addAction(face);
		AttackAction action = new AttackAction(this, unit);
		this.addAction(action);
	}
	
	public void takeDamage(float damange) {
		currentHealth -= damange;
		healthBar.setPct(health() / maxHealth());
	}

	public boolean isAlive() {
		return currentHealth > 0;
	}
	
	public Set<Weapon> allWeapons() {
		return new HashSet<Weapon>();
	}
	
	public UnitJson getSerialisation() {
		UnitJson json = new UnitJson();
		json.defName = def.name();
		json.playerId = owner.playerId();
		
		Vector3 loc = currentPosition();
		json.x = (int)loc.x;
		json.z = (int)loc.z;
		
		json.controlled = this.owner.controllUnit() == this;
		
		return json;
	}
	
}

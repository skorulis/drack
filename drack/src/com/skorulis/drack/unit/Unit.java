package com.skorulis.drack.unit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.actor.action.ActionContainer;
import com.skorulis.drack.actor.action.AttackAction;
import com.skorulis.drack.actor.action.FaceAction;
import com.skorulis.drack.actor.action.MovementAction;
import com.skorulis.drack.actor.action.ActorAction;
import com.skorulis.drack.attachments.Weapon;
import com.skorulis.drack.def.unit.UnitDef;
import com.skorulis.drack.pathfinding.MapPath;
import com.skorulis.drack.player.Player;
import com.skorulis.drack.resource.ResourceBatch;
import com.skorulis.drack.resource.ResourceQuantity;
import com.skorulis.drack.scene.DrackMoveableActor;
import com.skorulis.drack.serialisation.LoadData;
import com.skorulis.drack.serialisation.unit.UnitJson;
import com.skorulis.drack.ui.effects.HealthBar;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.IntersectionList;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.UpdateInfo;

public class Unit implements DrackMoveableActor {

	private ModelInstance instance;
	private ActionContainer actions;
	private ResourceBatch resources;
	protected Player owner;
	protected UnitDef def;
	protected HealthBar healthBar;
	protected float currentHealth;
	
	public Unit() {
		resources = new ResourceBatch();
		this.actions = new ActionContainer(this);
		this.currentHealth = maxHealth();
	}
	
	public void loadAssets(SKAssetManager assets) { 
		instance = new ModelInstance(assets.getModel(def.modelName()));
	}
	
	public void load(UnitJson json, LoadData ld) {
		actions.load(json.actions, ld);
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
		ActorAction action = actions.currentAction();
		if(action != null && action instanceof MovementAction) {
			((MovementAction)action).setPath(path);
		} else {
			ActorAction newAction = new MovementAction(this,path);
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
		actions.update(info);
	}

	@Override
	public boolean intersect(IntersectionList list) {
		Vector3 center = absTransform().getTranslation(new Vector3());
		if(Intersector.intersectRaySphere(list.ray(), center, 1, list.tmpPoint)) {
			list.addIntersection(this, list.tmpPoint);
			return true;
		}
		return false;
	}
	
	public Vector3 currentPosition() {
		ActorAction action = actions.currentAction();
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
	
	public void addAction(ActorAction action) {
		actions.addAction(action);
	}
	
	@Override
	public void addResources(ResourceBatch batch) {
		if(this.owner.controllUnit() == this) {
			this.owner.addResources(batch, this);
		} else {
			this.resources.add(batch);
		}
	}
	
	public float speed() {
		return def.speed();
	}
	
	public Player owner() {
		return owner;
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
		setBasicJsonFields(json);
		return json;
	}
	
	public void setBasicJsonFields(UnitJson json) {
		json.defName = def.name();
		json.playerId = owner.playerId();
		
		Vector3 loc = currentPosition();
		json.x = (int)loc.x;
		json.z = (int)loc.z;
		
		json.controlled = this.owner.controllUnit() == this;
		
		json.actions = this.actions.getSerialisation();
	}
	
	
}

package com.skorulis.drack.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Disposable;
import com.skorulis.drack.building.BuildingPlacement;
import com.skorulis.drack.def.DefManager;
import com.skorulis.drack.def.building.BuildingDef;
import com.skorulis.drack.effects.Effect2DLayer;
import com.skorulis.drack.map.GameMap;
import com.skorulis.drack.map.MapSquare;
import com.skorulis.drack.player.Player;
import com.skorulis.drack.resource.ResourceQuantity;
import com.skorulis.drack.serialisation.GameSceneJson;
import com.skorulis.drack.unit.Unit;
import com.skorulis.drack.unit.UnitDelegate;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.SceneNode;
import com.skorulis.scene.UpdateInfo;

public class GameScene implements SceneNode, Disposable, UnitDelegate {

	private ArrayList<Unit> units;
	private Set<Player> players;
	private GameMap map;
	private Matrix4 transform;
	private BuildingPlacement placingBuilding;
	private SKAssetManager assets;
	private Effect2DLayer effects2D;
	private DefManager def;
	
	public GameScene(DefManager def, SKAssetManager assets,GameMap map, Effect2DLayer effects2D) {
		this.assets = assets;
		this.map = map;
		this.effects2D = effects2D;
		this.def = def;
		
		transform = new Matrix4();		
		units = new ArrayList<Unit>();
		players = new HashSet<Player>();
	}

	@Override
	public Matrix4 absTransform() {
		return transform;
	}

	@Override
	public Matrix4 relTransform() {
		return transform;
	}

	@Override
	public void render(RenderInfo ri) {
		map.render(ri);
		for(Unit unit : units) {
			unit.render(ri);
		}
		if(placingBuilding != null) {
			placingBuilding.render(ri);
		}
	}
	
	public void update(UpdateInfo info) {
		Iterator<Unit> it = units.iterator();
		while(it.hasNext()) {
			Unit u = it.next();
			u.update(info);
			if(!u.isAlive()) {
				it.remove();
			}
		}
		
		Iterator<Player> playerIt = players.iterator();
		while(playerIt.hasNext()) {
			playerIt.next().update(info);
		}
		
		if(placingBuilding != null) {
			placingBuilding.update(info);
		}
		map.update(info);
	}
	
	public SceneNode intersect(Ray ray, Vector3 point) {
		for(Unit unit : units) {
			SceneNode n = unit.intersect(ray, point);
			
			if(n != null) {
				return n;
			}
		}
		return map.intersect(ray, point);
	}

	@Override
	public void dispose() {
		
	}
	
	public void clearPlacingBuilding() {
		this.placingBuilding = null;
	}
	
	public void setPlacingBuilding(BuildingDef def, Vector3 pos) {
		this.placingBuilding = new BuildingPlacement(assets,map,def,pos);
	}
	
	public void movePlacementBuilding(float x, float z) {
		this.placingBuilding.move(x,z);
	}
	
	public BuildingPlacement placingBuilding() {
		return placingBuilding;
	}
	
	public void placeBuilding() {
		this.placingBuilding.place();
		this.placingBuilding = null;
	}

	@Override
	public void resourceAdded(Unit avatar, ResourceQuantity rq) {
		effects2D.addTextEffect(avatar.absTransform().getTranslation(new Vector3()), rq.displayText());
	}
	
	public GameMap map() {
		return map;
	}
	
	public void addUnit(Unit u, MapSquare sq) {
		this.units.add(u);
		u.setDelegate(this);
		u.absTransform().setTranslation(sq.getCentreLoc());
		
		u.setHealthBar(effects2D.createHealthBar(u));
	}
	
	public void addUnit(Unit u, int x ,int z) {
		MapSquare sq = map.squareAt(x, z);
		addUnit(u,sq);
	}
	
	public DefManager def() {
		return def;
	}
	
	public SKAssetManager assets() {
		return assets;
	}
	
	public boolean isAlive() {
		return true;
	}
	
	public GameSceneJson getSerialisation() {
		GameSceneJson json = new GameSceneJson();
		json.map = map.getSerialisation();
		for(Player p : players) {
			json.players.add(p.getSerialisation());
		}
		for(Unit u : units) {
			json.units.add(u.getSerialisation());
		}
		
		return json;
	}
	
	public void addPlayer(Player player) {
		this.players.add(player);
	}
	
	public Player findPlayer(String id) {
		for(Player p : players) {
			if(p.playerId().equals(id)) {
				return p;
			}
		}
		return null;
	}

}

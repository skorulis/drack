package com.skorulis.drack.game;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.skorulis.drack.building.BuildingPlacement;
import com.skorulis.drack.def.DefManager;
import com.skorulis.drack.def.building.BuildingDef;
import com.skorulis.drack.effects.Effect2DLayer;
import com.skorulis.drack.map.GameMap;
import com.skorulis.drack.map.MapSquare;
import com.skorulis.drack.player.Player;
import com.skorulis.drack.player.PlayerContainer;
import com.skorulis.drack.serialisation.GameSceneJson;
import com.skorulis.drack.unit.Unit;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.IntersectionList;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.SceneNode;
import com.skorulis.scene.UpdateInfo;

public class GameScene implements SceneNode, Disposable {

	private ArrayList<Unit> units;
	public ArrayList<SceneNode> effects;
	private GameMap map;
	private Matrix4 transform;
	private BuildingPlacement placingBuilding;
	private SKAssetManager assets;
	private Effect2DLayer effects2D;
	private DefManager def;
	private PlayerContainer players;
	
	public GameScene(DefManager def, SKAssetManager assets,GameMap map, Effect2DLayer effects2D, PlayerContainer players) {
		this.assets = assets;
		this.map = map;
		this.effects2D = effects2D;
		this.def = def;
		
		transform = new Matrix4();		
		units = new ArrayList<Unit>();
		effects = new ArrayList<SceneNode>();
		this.players = players;
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
		for(SceneNode node : effects) {
			node.render(ri);
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
		
		Iterator<SceneNode> effectIt = effects.iterator();
		while(effectIt.hasNext()) {
			SceneNode node = effectIt.next();
			node.update(info);
			if(!node.isAlive()) {
				effectIt.remove();
			}
		}
		
		players.update(info);
		
		if(placingBuilding != null) {
			placingBuilding.update(info);
		}
		map.update(info);
	}
	
	@Override
	public boolean intersect(IntersectionList list) {
		int oldCount = list.intersectionCount();
		for(Unit unit : units) {
			unit.intersect(list);
		}
		map.intersect(list);
		return list.intersectionCount() > oldCount;
	}

	@Override
	public void dispose() {
		
	}
	
	public void clearPlacingBuilding() {
		this.placingBuilding = null;
	}
	
	public void setPlacingBuilding(BuildingDef def, Vector3 pos) {
		this.placingBuilding = new BuildingPlacement(assets,map,def,pos);
		this.placingBuilding.building().setOwner(this.players.humanPlayer());
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

	public GameMap map() {
		return map;
	}
	
	public void addUnit(Unit u, MapSquare sq) {
		this.units.add(u);
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
		
		json.players = players.getSerialisation();
		
		for(Unit u : units) {
			json.units.add(u.getSerialisation());
		}
		
		return json;
	}
	
	public PlayerContainer players() {
		return players;
	}
	
	public Unit findEnemyUnit(Player player) {
		for(Unit unit : this.units) {
			if(player != unit.owner()) {
				return unit;
			}
		}
		return null;
	}

}

package com.skorulis.drack.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Disposable;
import com.skorulis.drack.avatar.Avatar;
import com.skorulis.drack.building.Building;
import com.skorulis.drack.building.BuildingPlacement;
import com.skorulis.drack.def.BuildingDef;
import com.skorulis.drack.map.GameMap;
import com.skorulis.drack.map.MapSquare;
import com.skorulis.drack.pathfinding.MapPath;
import com.skorulis.drack.pathfinding.PathFinder;
import com.skorulis.scene.SceneNode;

public class GameScene implements SceneNode, Disposable {

	private Avatar avatar;
	private GameMap map;
	private Matrix4 transform;
	private BuildingPlacement placingBuilding;
	private AssetManager assets;
	
	public GameScene(AssetManager assets,GameMap map) {
		transform = new Matrix4();
		this.assets = assets;
		this.map = map;
		avatar = new Avatar(assets);
	}

	public void nodeSelected(SceneNode node) {
		if(node instanceof MapSquare) {
			MapSquare sq = (MapSquare) node;
			MapSquare current = map.squareAt(avatar.absTransform().getTranslation(new Vector3()));
			if(sq != current && sq.isPassable()) {
				PathFinder finder = new PathFinder(map, current, sq);
				MapPath path = finder.generatePath();
				avatar.setPath(path);
			} else if(sq.building() != null) {
				
			}
		}
	}
	
	public void moveAvatar(int x ,int z) {
		avatar.relTransform().setTranslation(x, 0, z);
	}
	
	public GameMap map() {
		return map;
	}
	
	public Avatar avatar() {
		return avatar;
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
	public void render(ModelBatch batch, Environment environment) {
		map.render(batch, environment);
		avatar.render(batch, environment);
		if(placingBuilding != null) {
			placingBuilding.render(batch, environment);
		}
	}
	
	public void update(float delta) {
		avatar.update(delta);
		if(placingBuilding != null) {
			placingBuilding.update(delta);
		}
	}
	
	public SceneNode intersect(Ray ray, Vector3 point) {
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
	
}

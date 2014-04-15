package com.skorulis.drack.game;

import java.util.ArrayList;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Disposable;
import com.skorulis.drack.avatar.Avatar;
import com.skorulis.drack.avatar.AvatarDelegate;
import com.skorulis.drack.building.BuildingPlacement;
import com.skorulis.drack.def.BuildingDef;
import com.skorulis.drack.effects.Effect2DLayer;
import com.skorulis.drack.map.GameMap;
import com.skorulis.drack.map.MapSquare;
import com.skorulis.drack.pathfinding.MapPath;
import com.skorulis.drack.pathfinding.PathFinder;
import com.skorulis.drack.resource.ResourceQuantity;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.SceneNode;

public class GameScene implements SceneNode, Disposable, AvatarDelegate {

	private Avatar avatar;
	private GameMap map;
	private Matrix4 transform;
	private BuildingPlacement placingBuilding;
	private SKAssetManager assets;
	private GameDelegate delegate;
	private Effect2DLayer effects2D;
	
	public GameScene(SKAssetManager assets,GameMap map, Effect2DLayer effects2D) {
		this.assets = assets;
		this.map = map;
		this.effects2D = effects2D;
		
		transform = new Matrix4();
		avatar = new Avatar(assets);
		avatar.setDelegate(this);
	}

	public void nodeSelected(SceneNode node) {
		if(node instanceof MapSquare) {
			MapSquare sq = (MapSquare) node;
			MapSquare current = map.squareAt(avatar.currentPosition());
			if(sq == current) {
				return;
			}
			ArrayList<MapSquare> near = null;
			if(sq.isPassable()) {
				
			} else if(sq.building() != null) {
				near = map.adjacentSquares(sq);
			}
			
			PathFinder finder = new PathFinder(map, current, sq, near);
			MapPath path = finder.generatePath();
			avatar.setPath(path);
			this.delegate.playerMoved();
			
			if(sq.building() != null) {
				this.delegate.buildingSelected(sq.building());
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
	public void render(RenderInfo ri) {
		map.render(ri);
		avatar.render(ri);
		if(placingBuilding != null) {
			placingBuilding.render(ri);
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
	
	public Avatar playerAvatar() {
		return avatar;
	}
	
	public void setDelegate(GameDelegate delegate) {
		this.delegate = delegate;
	}

	@Override
	public void resourceAdded(Avatar avatar, ResourceQuantity rq) {
		effects2D.addTextEffect(avatar.absTransform().getTranslation(new Vector3()), rq.displayText());
	}
	
}

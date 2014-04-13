package com.skorulis.drack.building;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.skorulis.drack.def.BuildingDef;
import com.skorulis.drack.map.GameMap;
import com.skorulis.drack.map.MapSquare;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.SceneNode;

public class BuildingPlacement implements SceneNode{
	
	private Building building;
	private ModelInstance cube;
	private Vector3 location;
	private GameMap map;
	
	public BuildingPlacement(SKAssetManager assets, GameMap map,  BuildingDef def, Vector3 pos) {
		this.map = map;
		this.building = def.create(assets);
		this.location = pos;
		this.location.x = Math.round(this.location.x);
		this.location.z = Math.round(this.location.z);
		this.cube = new ModelInstance(assets.get("data/cube1.g3db", Model.class));
		this.cube.transform.scale(def.width, 1, def.depth);
		this.move(0, 0);
	}
	
	@Override
	public Matrix4 absTransform() {
		return building.absTransform();
	}
	@Override
	public Matrix4 relTransform() {
		return building.relTransform();
	}
	@Override
	public void render(RenderInfo ri) {
		building.render(ri);
		ri.batch.render(cube,ri.environment);
	}
	@Override
	public SceneNode intersect(Ray ray, Vector3 point) {
		return null;
	}
	@Override
	public void update(float delta) {
		
	}
	
	public void place() {
		Vector3 rounded = roundAndClip(location);
		
		for(int i = 0; i < building.def().width; ++i) {
			for(int j = 0; j < building.def().depth; ++j) {
				MapSquare sq = map.squareAt((int)rounded.x + j, (int)rounded.z + i);
				if(i == 0 && j == 0) {
					sq.setBuilding(building);
				} else {
					sq.setSharedBuilding(building);
				}
			}
		}
	}
	
	public Building building() {
		return building;
	}
	
	public Vector3 location() {
		return location;
	}
	
	public void move(float x, float z) {
		location.x += x;
		location.z += z;
		
		Vector3 rounded = roundAndClip(location);
		
		rounded.x += (building.def.width - 1)*0.5f;
		rounded.z += (building.def.depth - 1)*0.5f;
		
		building.absTransform().setTranslation(rounded);
		cube.transform.setTranslation(rounded);
		
		if(canPlace()) {
			this.cube.materials.get(0).set(ColorAttribute.createDiffuse(Color.GREEN));
		} else {
			this.cube.materials.get(0).set(ColorAttribute.createDiffuse(Color.RED));
		}
	}
	
	public Vector3 roundAndClip(Vector3 location) {
		Vector3 rounded = new Vector3(Math.round(location.x), 0, Math.round(location.z));
		if(rounded.x < 0) {
			rounded.x = 0;
		}
		if(rounded.z < 0) {
			rounded.z = 0;
		}
		
		if(rounded.x >= map.width() - building.def.width) {
			rounded.x = map.width() - building.def.width;
		}
		if(rounded.z >= map.depth() - building.def.depth) {
			rounded.z = map.depth() - building.def.depth;
		}
		return rounded;
	}
	
	public boolean canPlace() {
		return map.canPlaceBuilding(building,roundAndClip(location));
	}
	
}

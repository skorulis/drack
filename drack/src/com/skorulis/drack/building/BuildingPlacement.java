package com.skorulis.drack.building;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.skorulis.drack.def.BuildingDef;
import com.skorulis.drack.map.GameMap;
import com.skorulis.drack.map.MapSquare;
import com.skorulis.scene.SceneNode;

public class BuildingPlacement implements SceneNode{
	
	private Building building;
	private ModelInstance cube;
	private Vector3 location;
	private GameMap map;
	
	public BuildingPlacement(AssetManager assets, GameMap map,  BuildingDef def, Vector3 pos) {
		this.building = def.create(assets);
		this.location = pos;
		this.location.x = Math.round(this.location.x);
		this.location.z = Math.round(this.location.z);
		this.cube = new ModelInstance(assets.get("data/cube1.g3db", Model.class));
		this.update(0);
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
	public void render(ModelBatch batch, Environment environment) {
		building.render(batch, environment);
		batch.render(cube,environment);
		this.cube.materials.get(0).set(ColorAttribute.createDiffuse(Color.RED));
	}
	@Override
	public SceneNode intersect(Ray ray, Vector3 point) {
		return null;
	}
	@Override
	public void update(float delta) {
		Vector3 rounded = new Vector3(Math.round(location.x), 0, Math.round(location.z));
		building.absTransform().setTranslation(rounded);
		cube.transform.setTranslation(rounded);
	}
	
	public void place() {
		MapSquare sq = map.squareAt(location());
		sq.setBuilding(building());
	}
	
	public Building building() {
		return building;
	}
	
	public Vector3 location() {
		return location;
	}
	
	
}

package com.skorulis.drack.building;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.skorulis.scene.SceneNode;

public class Building implements SceneNode{

	private ModelInstance buildingInstance;
	
	public Building(AssetManager assets) {
		buildingInstance = new ModelInstance(assets.get("data/cone.g3db",Model.class));
	}
	
	public Building(Vector3 translation, AssetManager assets) {
		this(assets);
		setTranslation(translation);
	}
	
	public void setTranslation(Vector3 translation) {
		buildingInstance.transform.setToTranslation(translation.x, 0, translation.z);
	}
	@Override
	public Matrix4 absTransform() {
		return buildingInstance.transform;
	}

	@Override
	public Matrix4 relTransform() {
		return buildingInstance.transform;
	}

	@Override
	public void render(ModelBatch batch, Environment environment) {
		batch.render(buildingInstance,environment);
	}

	@Override
	public SceneNode intersect(Ray ray, Vector3 point) {
		return null;
	}

	@Override
	public void update(float delta) {
		
	}

}

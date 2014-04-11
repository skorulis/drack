package com.skorulis.drack.map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.skorulis.scene.SceneNode;

public class ForceField implements SceneNode{

	private ModelInstance fieldInstance;
	
	public ForceField(AssetManager assets) {
		fieldInstance = new ModelInstance(assets.get("data/cube1.g3db", Model.class));
	}
	
	public void setPosition(Vector3 loc) {
		fieldInstance.transform.setTranslation(loc);
	}
	
	@Override
	public Matrix4 absTransform() {
		return fieldInstance.transform;
	}

	@Override
	public Matrix4 relTransform() {
		return fieldInstance.transform;
	}

	@Override
	public void render(ModelBatch batch, Environment environment) {
		batch.render(fieldInstance,environment);
	}

	@Override
	public SceneNode intersect(Ray ray, Vector3 point) {
		return null;
	}

	@Override
	public void update(float delta) {

	}

}

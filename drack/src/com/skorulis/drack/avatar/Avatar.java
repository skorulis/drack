package com.skorulis.drack.avatar;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.skorulis.scene.SceneNode;

public class Avatar implements SceneNode{

	private ModelInstance instance;
	
	public Avatar(AssetManager assets) {
		instance = new ModelInstance(assets.get("data/cone.g3db", Model.class));
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
	public void render(ModelBatch batch, Environment environment) {
		batch.render(instance,environment);
	}

	@Override
	public SceneNode intersect(Ray ray, Vector3 point) {
		return null;
	}
	

}

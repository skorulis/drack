package com.skorulis.drack.map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.skorulis.scene.SceneNode;

public class MapSquare implements SceneNode {

	private ModelInstance instance;
	private final int x;
	private final int z;
	
	public MapSquare(AssetManager assets,int x, int z) {
		this.x = x;
		this.z = z;
		instance = new ModelInstance(assets.get("block", Model.class));
		instance.transform.setToTranslation(new Vector3(x, 0.5f, z));
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

	public int x() {
		return x;
	}
	
	public int z() {
		return z;
	}
	
}

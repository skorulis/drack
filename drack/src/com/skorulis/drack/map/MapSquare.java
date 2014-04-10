package com.skorulis.drack.map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;
import com.skorulis.scene.SceneNode;

public class MapSquare implements SceneNode {

	private ModelInstance groundInstance;
	private ModelInstance buildingInstance;
	private BoundingBox boundingBox;
	private final int x;
	private final int z;
	
	public MapSquare(AssetManager assets,int x, int z) {
		this.x = x;
		this.z = z;
		groundInstance = new ModelInstance(assets.get("block", Model.class));
		groundInstance.transform.setToTranslation(new Vector3(x, -0.5f, z));
		boundingBox = new BoundingBox(new Vector3(x-0.5f,-1,z-0.5f), new Vector3(x+0.5f,0,z+0.5f));
		
		if(Math.random() > 0.9) {
			buildingInstance = new ModelInstance(assets.get("data/cone.g3db",Model.class));
			buildingInstance.transform.setToTranslation(x, 0, z);
		}
		
	}
	
	@Override
	public Matrix4 absTransform() {
		return groundInstance.transform;
	}

	@Override
	public Matrix4 relTransform() {
		return groundInstance.transform;
	}

	@Override
	public void render(ModelBatch batch, Environment environment) {
		batch.render(groundInstance,environment);
		if(buildingInstance != null) {
			batch.render(buildingInstance, environment);
		}
	}
	
	public void update(float delta) {
		
	}

	public int x() {
		return x;
	}
	
	public int z() {
		return z;
	}
	
	public BoundingBox boundingBox() {
		return boundingBox;
	}
	
	public String toString() {
		return "MS " + x + "," + z;
	}
	
	public SceneNode intersect(Ray ray, Vector3 point) {
		if(Intersector.intersectRayBounds(ray, boundingBox(), point)) {
			return this;
		}
		return null;
	}
	
	public boolean isPassable() {
		return buildingInstance == null;
	}
	
	public Vector3 getCentreLoc() {
		return new Vector3(x(), 0, z());
	}
	
	
	
	
}

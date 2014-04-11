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
import com.skorulis.drack.building.Building;
import com.skorulis.scene.SceneNode;

public class MapSquare implements SceneNode {

	private ModelInstance groundInstance;
	private Building building;
	private ForceField field;
	private BoundingBox boundingBox;
	private final int x;
	private final int z;
	
	public MapSquare(AssetManager assets,int x, int z) {
		this.x = x;
		this.z = z;
		Vector3 translation = new Vector3(x, -0.5f, z);
		groundInstance = new ModelInstance(assets.get("block", Model.class));
		groundInstance.transform.setToTranslation(translation);
		boundingBox = new BoundingBox(new Vector3(x-0.5f,-1,z-0.5f), new Vector3(x+0.5f,0,z+0.5f));
		
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
		if(building != null) {
			building.render(batch, environment);
		}
		if(field != null) {
			field.render(batch, environment);
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
		return building == null;
	}
	
	public Vector3 getCentreLoc() {
		return new Vector3(x(), 0, z());
	}
	
	public void setBuilding(Building building) {
		this.building = building;
		this.building.setTranslation(groundInstance.transform.getTranslation(new Vector3()));
	}
	
	
}

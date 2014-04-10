package com.skorulis.drack.avatar;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.skorulis.drack.pathfinding.MapPath;
import com.skorulis.drack.pathfinding.MovementInfo;
import com.skorulis.scene.SceneNode;

public class Avatar implements SceneNode{

	private ModelInstance instance;
	private MapPath path;
	private MovementInfo movement;
	
	private float speed = 10;
	
	public Avatar(AssetManager assets) {
		instance = new ModelInstance(assets.get("data/cube1.g3db", Model.class));
	}
	
	public void setPath(MapPath path) {
		this.path = path;
		if (movement == null) {
			movement = path.getMovement(speed);
		}
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
	
	public void update(float delta) {
		if(movement == null) {
			return;
		}
		instance.transform.setTranslation(movement.update(delta));
		if (movement.finished()) {
			if (path.finished()) {
				movement = null;
				path = null;
			} else {
				if (movement.destSquare == path.nextNode()) {
					movement = path.next(speed);
				} else {
					movement = path.getMovement(speed);
				}
			}
		}
	}

	@Override
	public SceneNode intersect(Ray ray, Vector3 point) {
		return null;
	}
	

}

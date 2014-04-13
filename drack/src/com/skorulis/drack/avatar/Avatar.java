package com.skorulis.drack.avatar;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.skorulis.drack.pathfinding.MapPath;
import com.skorulis.drack.pathfinding.MovementInfo;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.SceneNode;

public class Avatar implements SceneNode{

	private ModelInstance instance;
	private MapPath path;
	private MovementInfo movement;
	
	private float speed = 10;
	
	public Avatar(AssetManager assets) {
		instance = new ModelInstance(assets.get("data/models/craft1.g3db", Model.class));
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
	public void render(RenderInfo ri) {
		ri.batch.render(instance,ri.environment);
	}
	
	public void update(float delta) {
		if(movement == null) {
			return;
		}
		instance.transform.setToWorld(movement.update(delta), movement.direction(),new Vector3(0,1,0));
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
	
	public Vector3 currentPosition() {
		if(movement == null) {
			return absTransform().getTranslation(new Vector3());
		}
		return movement.endLoc;
	}
	

}

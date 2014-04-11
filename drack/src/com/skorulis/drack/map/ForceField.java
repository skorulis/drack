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
	
	private enum FieldType {
		MIDDLE,
		EDGE,
		CORNER
	}
	
	private ModelInstance fieldInstance;
	private FieldType type;
	
	public ForceField() {
		type = FieldType.MIDDLE;
	}
	
	public void setAdjacent(boolean top, boolean right, boolean bottom, boolean left) {
		int count = (top ? 1 : 0) + (right ? 1 : 0) + (bottom ? 1 : 0) + (left ? 1 : 0);
		if(count == 4) {
			type = FieldType.MIDDLE;
		} else if(count == 2) {
			type = FieldType.CORNER;
		} else {
			type = FieldType.EDGE;
		}
	}
	
	public void buildModel(AssetManager assets) {
		if(type == FieldType.EDGE) {
			fieldInstance = new ModelInstance(assets.get("field", Model.class));
		}
	}
	
	public void setPosition(Vector3 loc) {
		if(fieldInstance != null) {
			fieldInstance.transform.setTranslation(loc.x, 0, loc.z);
		}
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
		if(fieldInstance != null) {
			batch.render(fieldInstance,environment);
		}
	}

	@Override
	public SceneNode intersect(Ray ray, Vector3 point) {
		return null;
	}

	@Override
	public void update(float delta) {

	}

}

package com.skorulis.drack.map;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.IntersectionList;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.SceneNode;
import com.skorulis.scene.UpdateInfo;

public class ForceField implements SceneNode{ 
	
	private enum FieldType {
		MIDDLE,
		EDGE,
		CORNER
	}
	
	private ModelInstance fieldInstance;
	private FieldType type;
	private int rotation;
	private Vector3 offset;
	
	public ForceField() {
		type = FieldType.MIDDLE;
		offset = new Vector3();
	}
	
	public void setAdjacent(boolean top, boolean right, boolean bottom, boolean left) {
		int count = (top ? 1 : 0) + (right ? 1 : 0) + (bottom ? 1 : 0) + (left ? 1 : 0);
		if(count == 4) {
			type = FieldType.MIDDLE;
		} else if(count == 2) {
			type = FieldType.CORNER;
			if(top && right) {
				rotation = 270;
				offset.x = -0.4f;
			} else if(top && left) {
				rotation = 0;
				offset.z = 0.4f;
			} else if(bottom && right) {
				rotation = 180;
				offset.z = -0.4f;
			} else if(bottom && left) {
				rotation = 90;
				offset.x = 0.4f;
			}
		} else {
			type = FieldType.EDGE;
			if(!top) {
				rotation = 0;
				offset.z = -0.4f;
			} else if(!right) {
				rotation = 90;
				offset.x = 0.4f;
			} else if(!bottom) {
				rotation = 180;
				offset.z = 0.4f;
			} else if(!left) {
				rotation = 270;
				offset.x = -0.4f;
			}
		}
	}
	
	public void buildModel(SKAssetManager assets) {
		if(type == FieldType.EDGE) {
			fieldInstance = new ModelInstance(assets.getModel("field1"));
		} else if(type == FieldType.CORNER) {
			fieldInstance = new ModelInstance(assets.getModel("wall_corner"));
		}
	}
	
	public void setPosition(Vector3 loc) {
		if(fieldInstance != null) {
			fieldInstance.transform.setTranslation(loc.x + offset.x, 0, loc.z + offset.z);
			fieldInstance.transform.rotate(new Vector3(0,1,0), rotation);
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
	public void render(RenderInfo ri) {
		if(fieldInstance != null) {
			ri.batch.render(fieldInstance,ri.environment);
		}
	}

	@Override
	public boolean intersect(IntersectionList list) {
		return false;
	}

	@Override
	public void update(UpdateInfo info) {

	}
	
	public boolean isAlive() {
		return true;
	}

}

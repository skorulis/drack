package com.skorulis.drack.map;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.skorulis.drack.actor.building.Building;
import com.skorulis.drack.serialisation.MapSquareJson;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.IntersectionList;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.SceneNode;
import com.skorulis.scene.UpdateInfo;

public class MapSquare implements SceneNode {

	private ModelInstance groundInstance;
	private Building building;
	private Building sharedBuilding;
	private ForceField field;
	private BoundingBox boundingBox;
	private final int x;
	private final int z;
	
	public MapSquare(SKAssetManager assets,int x, int z) {
		this.x = x;
		this.z = z;
		Vector3 translation = new Vector3(x, -0.5f, z);
		groundInstance = new ModelInstance(assets.getModel("block"));
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
	public void render(RenderInfo ri) {
		renderBlock(ri);
		if(building != null) {
			building.render(ri);
		}
		if(field != null) {
			field.render(ri);
		}
	}
	
	private boolean shouldDrawBlock() {
		if(this.building != null && this.building.def().replacesTerrain) {
			return false;
		}
		if(this.sharedBuilding != null && this.sharedBuilding.def().replacesTerrain) {
			return false;
		}
		return true;
	}
	
	public void renderBlock(RenderInfo ri) {
		if(shouldDrawBlock()) {
			ri.batch.render(groundInstance,ri.environment);
		}
	}
	
	@Override
	public void update(UpdateInfo info) {
		if(this.building != null) {
			this.building.update(info);
		}
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
	
	@Override
	public boolean intersect(IntersectionList list) {
		if(Intersector.intersectRayBounds(list.ray(), boundingBox(), list.tmpPoint)) {
			list.addIntersection(this, list.tmpPoint);
			return true;
		}
		return false;
	}
	
	public boolean isPassable() {
		return building == null && sharedBuilding == null;
	}
	
	public Vector3 getCentreLoc() {
		return new Vector3(x(), 0, z());
	}
	
	public void setBuilding(Building building) {
		this.building = building;
		building.addCoveredSquare(this);
		Vector3 translation = groundInstance.transform.getTranslation(new Vector3());
		translation.x += (building.def().width - 1) * 0.5f;
		translation.z += (building.def().depth - 1) * 0.5f;
		this.building.setTranslation(translation);
	}
	
	public void setForceField(ForceField field) {
		this.field = field;
		field.setPosition(getCentreLoc());
	}
	
	public ForceField field() {
		return this.field;
	}
	
	public Building building() {
		return this.building;
	}
	
	public Building sharedBuilding() {
		return sharedBuilding;
	}
	
	public Building anyBuilding() {
		if(this.building != null) {
			return this.building;
		}
		return sharedBuilding;
	}
	
	public void setSharedBuilding(Building b) {
		this.sharedBuilding = b;
		b.addCoveredSquare(this);
	}
	
	public boolean isAlive() {
		return true;
	}
	
	public MapSquareJson getSerialisation() {
		MapSquareJson json = new MapSquareJson();
		
		if(building != null) {
			json.building = building.getSerialisation();
		}
		
		return json;
	}
	
}

package com.skorulis.drack;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.skorulis.drack.game.GameScene;
import com.skorulis.scene.SceneNode;

public class GameEventListener implements GestureListener {

	private IsoPerspectiveCamera camera;
	private GameScene scene;
	
	public GameEventListener(IsoPerspectiveCamera camera) {
		this.camera = camera;
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		Ray ray = camera.cam().getPickRay(x, y);
		SceneNode node = scene.intersect(ray, new Vector3());
		if(node != null) {
			scene.nodeSelected(node);
		}
		
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		if(scene.placingBuilding() != null) {
			if(deltaX == 0 && deltaY == 0) {
				return false;
			}
			
			Vector3 v1 = scene.map().groundIntersection(camera.cam().getPickRay(x-deltaX, y-deltaY));
			Vector3 v2 = scene.map().groundIntersection(camera.cam().getPickRay(x, y));
			
			float diffX = v2.x - v1.x;
			float diffZ = v2.z - v1.z;
			
			scene.placementLocation().x += diffX;
			scene.placementLocation().z += diffZ;
		}
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void setScene(GameScene scene) {
		this.scene = scene;
		
	}

}

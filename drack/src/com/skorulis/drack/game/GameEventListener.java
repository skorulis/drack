package com.skorulis.drack.game;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.skorulis.scene.SceneNode;

public class GameEventListener implements GestureListener {

	private GameLogic logic;
	
	public GameEventListener() {

	}
	
	public GameEventListener(GameLogic logic) {
		this.logic = logic;
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		Ray ray = logic.isoCam().cam().getPickRay(x, y);
		SceneNode node = logic.scene().intersect(ray, new Vector3());
		if(node != null) {
			logic.nodeSelected(node);
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
		if(logic.scene().placingBuilding() != null) {
			if(deltaX == 0 && deltaY == 0) {
				return false;
			}
			
			Vector3 v1 = logic.map().groundIntersection(logic.isoCam().cam().getPickRay(x-deltaX, y-deltaY));
			Vector3 v2 = logic.map().groundIntersection(logic.isoCam().cam().getPickRay(x, y));
			
			logic.scene().movePlacementBuilding(v2.x - v1.x, v2.z - v1.z);
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
	
	public void setLogic(GameLogic logic) {
		this.logic = logic;
	}

}

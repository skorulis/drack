package com.skorulis.drack;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.Ray;
import com.skorulis.drack.map.GameMap;
import com.skorulis.drack.map.MapSquare;

public class GameEventListener implements GestureListener {

	private IsoPerspectiveCamera camera;
	private GameMap map;
	
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
		MapSquare square = map.intersectRay(ray);
		System.out.println("RAY " + ray);
		System.out.println("SQ " + square);
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
		// TODO Auto-generated method stub
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
	
	public void setMap(GameMap map) {
		this.map = map;
	}

}

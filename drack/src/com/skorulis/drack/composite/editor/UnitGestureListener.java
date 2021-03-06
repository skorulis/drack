package com.skorulis.drack.composite.editor;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.skorulis.scene.IntersectionList;

public class UnitGestureListener implements GestureListener {

	private CompositeEditor editor;
	
	public UnitGestureListener(CompositeEditor editor) {
		this.editor = editor;
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		Ray ray = editor.cam().getPickRay(x, y);
		IntersectionList list = new IntersectionList(ray);
		for(HardPointNode hpn : editor.pointNodes) {
			if(hpn.intersect(list)) {
				hpn.setSelected(true);
				
			} else {
				hpn.setSelected(false);
			}
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
		editor.cam().rotateAround(new Vector3(), new Vector3(0,1,0), -deltaX);
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

}

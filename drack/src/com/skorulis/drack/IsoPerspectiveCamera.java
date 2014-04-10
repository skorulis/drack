package com.skorulis.drack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;

public class IsoPerspectiveCamera {

	private PerspectiveCamera cam;
	
	public IsoPerspectiveCamera() {
		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	public void resize(int width, int height) {
		cam = new PerspectiveCamera(45, width, height);
        cam.position.set(10f, 10f, 10f);
        cam.lookAt(0,0,0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();
	}
	
	public void update(float delta) {
		cam.update();
	}
	
	public Camera cam() {
		return cam;
	}
	
}

package com.skorulis.drack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.skorulis.scene.SceneNode;

public class IsoPerspectiveCamera {

	private PerspectiveCamera cam;
	private SceneNode tracking;
	
	public IsoPerspectiveCamera() {
		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	public void resize(int width, int height) {
		cam = new PerspectiveCamera(45, width, height);
        cam.position.set(7.07f, 10f, 7.07f);
        cam.lookAt(0,0,0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();
	}
	
	public void update(float delta) {
		if(tracking != null) {
			Vector3 pos = tracking.absTransform().getTranslation(new Vector3()); 
			cam.position.set(pos.x + 7.07f, pos.y + 10, pos.z + 7.07f);
			cam.lookAt(pos);
		}
		cam.update();
	}
	
	public Camera cam() {
		return cam;
	}
	
	public void setTracking(SceneNode tracking) {
		this.tracking = tracking;
	}
	
	public Ray centreRay() {
		return cam.getPickRay(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
	}
	
}

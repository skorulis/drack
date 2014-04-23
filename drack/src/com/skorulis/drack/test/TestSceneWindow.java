package com.skorulis.drack.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.effects.LaserEffect;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.SceneWindow;

public class TestSceneWindow implements SceneWindow {

	private TestGestureListener tgl;
	private PerspectiveCamera cam;
	private ModelBatch modelBatch;
	private Environment environment;
	private LaserEffect laser;
	
	public TestSceneWindow(SKAssetManager assets) {
		tgl = new TestGestureListener(this);
		resized(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		modelBatch = new ModelBatch();
        
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
        
        laser = new LaserEffect(assets, new Vector3(2,0,0), new Vector3(-2,0,0));
	}
	
	@Override
	public void draw() {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(0.4f, 0.4f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		modelBatch.begin(cam);
		RenderInfo ri = new RenderInfo(modelBatch, environment, cam);
		laser.render(ri);
		
		modelBatch.end();
	}

	@Override
	public void update(float delta) {
		cam.update();
	}

	@Override
	public void resized(int width, int height) {
		cam = new PerspectiveCamera(67, width,height);
        cam.position.set(2f, 2f, 2f);
        cam.lookAt(0,0,0);
        cam.near = 0.1f;
        cam.far = 300f;
        cam.update();
	}

	@Override
	public GestureListener gestureListener() {
		return tgl;
	}
	
	public Camera cam() {
		return cam;
	}

}

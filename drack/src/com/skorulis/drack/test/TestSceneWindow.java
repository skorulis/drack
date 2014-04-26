package com.skorulis.drack.test;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.effects.BulletEffect;
import com.skorulis.drack.effects.LaserEffect;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.SceneWindow;
import com.skorulis.scene.UpdateInfo;

public class TestSceneWindow implements SceneWindow {

	private TestGestureListener tgl;
	private PerspectiveCamera cam;
	private ModelBatch modelBatch;
	private Environment environment;
	private ArrayList<LaserEffect> lasers;
	private ArrayList<BulletEffect> bullets;
	private SKAssetManager assets;
	private ModelInstance sphere;
	
	public TestSceneWindow(SKAssetManager assets) {
		this.assets = assets;
		tgl = new TestGestureListener(this);
		resized(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		modelBatch = new ModelBatch();
        
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
        
        lasers = new ArrayList<LaserEffect>();
        bullets = new ArrayList<BulletEffect>();
        addLaser(new Vector3(1,0,0), new Vector3(4,0,0));
        //addLaser(new Vector3(0,0,1), new Vector3(0,0,4));
        //addLaser(new Vector3(0,0,1), new Vector3(0,0,4));
        //addLaser(new Vector3(1.2f,0,0), new Vector3(-2,0,5));
        
        sphere = new ModelInstance(assets.getModel("cube1"));
        
        addBullet(new Vector3(0,0,5) ,new Vector3());
        
	}
	
	private void addLaser(Vector3 start, Vector3 end) {
		LaserEffect laser = new LaserEffect(assets, start, end );
		lasers.add(laser);
	}
	
	private void addBullet(Vector3 start, Vector3 end) {
		BulletEffect bullet = new BulletEffect(assets, start, end);
		bullets.add(bullet);
	}
	
	@Override
	public void draw() {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(0.4f, 0.4f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		modelBatch.begin(cam);
		RenderInfo ri = new RenderInfo(modelBatch, environment, cam);
		for(LaserEffect l : lasers) {
			l.render(ri);
		}
		for(BulletEffect b : bullets) {
			b.render(ri);
		}
		
		modelBatch.render(sphere,ri.environment);
		modelBatch.end();
	}

	@Override
	public void update(float delta) {
		cam.update();
		UpdateInfo ui = new UpdateInfo(delta, null);
		ui.cam = cam;
		
		for(LaserEffect l : lasers) {
			l.update(ui);
		}
		for(BulletEffect b : bullets) {
			b.update(ui);
		}
	}

	@Override
	public void resized(int width, int height) {
		cam = new PerspectiveCamera(67, width,height);
        cam.position.set(8f, 8f, 8f);
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

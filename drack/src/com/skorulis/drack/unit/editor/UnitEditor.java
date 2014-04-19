package com.skorulis.drack.unit.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.input.GestureDetector;
import com.skorulis.gdx.SKAssetManager;

public class UnitEditor {
	
	private PerspectiveCamera cam;
	private ModelInstance instance;
	private ModelBatch modelBatch;
	private Environment environment;
	private UnitGestureListener ugl;
	private GestureDetector gestureDetector;
	
	public UnitEditor(SKAssetManager assets) {
		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(2f, 2f, 2f);
        cam.lookAt(0,0,0);
        cam.near = 0.1f;
        cam.far = 300f;
        cam.update();
        
        instance = new ModelInstance(assets.getModel("cube_drone"));
        modelBatch = new ModelBatch();
        
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
        
        ugl = new UnitGestureListener(this);
        gestureDetector = new GestureDetector(ugl);
	}
	
	public void update() {
		cam.update();
	}
	
	public void draw() {
		modelBatch.begin(cam);
		modelBatch.render(instance,environment);
		modelBatch.end();
	}
	
	public PerspectiveCamera cam() {
		return cam;
	}
	
	public GestureDetector gestureDetector() {
		return gestureDetector;
	}
	
}
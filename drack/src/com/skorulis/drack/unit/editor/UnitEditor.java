package com.skorulis.drack.unit.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.input.GestureDetector;
import com.skorulis.drack.def.DefManager;
import com.skorulis.drack.player.Player;
import com.skorulis.drack.unit.composite.CompositeUnit;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;

public class UnitEditor {
	
	private PerspectiveCamera cam;
	private CompositeUnit unit;
	private ModelBatch modelBatch;
	private Environment environment;
	private UnitGestureListener ugl;
	private GestureDetector gestureDetector;
	private Player player;
	
	public UnitEditor(SKAssetManager assets, DefManager def) {
		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(2f, 2f, 2f);
        cam.lookAt(0,0,0);
        cam.near = 0.1f;
        cam.far = 300f;
        cam.update();
        player = new Player();
        unit = new CompositeUnit(assets, player, def.getCompositeUnit("base"));
        
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
		RenderInfo ri = new RenderInfo(modelBatch, environment, cam);
		unit.render(ri);
		modelBatch.end();
	}
	
	public PerspectiveCamera cam() {
		return cam;
	}
	
	public GestureDetector gestureDetector() {
		return gestureDetector;
	}
	
}

package com.skorulis.drack.unit.editor;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.input.GestureDetector;
import com.skorulis.drack.def.DefManager;
import com.skorulis.drack.def.unit.HullPointDef;
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
	private DefManager def;
	public Set<HullPointNode> pointNodes;
	private SKAssetManager assets;
	
	public UnitEditor(SKAssetManager assets, DefManager def) {
		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(2f, 2f, 2f);
        cam.lookAt(0,0,0);
        cam.near = 0.1f;
        cam.far = 300f;
        cam.update();
        player = new Player();
        unit = new CompositeUnit(assets, player, def.getCompositeUnit("base"));
        
        pointNodes = new HashSet<HullPointNode>();
        for(HullPointDef hpd : unit.compDef().hull.points) {
        	pointNodes.add(new HullPointNode(assets, hpd));
        }
        
        modelBatch = new ModelBatch();
        
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
        
        ugl = new UnitGestureListener(this);
        gestureDetector = new GestureDetector(ugl);
        this.assets = assets;
        this.def = def;
	}
	
	public void update() {
		cam.update();
	}
	
	public void draw() {
		Gdx.gl.glClear(GL20.GL_DEPTH_BUFFER_BIT);
		modelBatch.begin(cam);
		RenderInfo ri = new RenderInfo(modelBatch, environment, cam);
		unit.render(ri);
		for(HullPointNode hpn : pointNodes) {
			hpn.render(ri);
		}
		modelBatch.end();
	}
	
	public PerspectiveCamera cam() {
		return cam;
	}
	
	public GestureDetector gestureDetector() {
		return gestureDetector;
	}
	
	public CompositeUnit unit() {
		return unit;
	}
	
	public SKAssetManager assets() {
		return assets;
	}
	
	public DefManager def() {
		return def;
	}
}

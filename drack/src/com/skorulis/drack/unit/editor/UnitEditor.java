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
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Matrix4;
import com.skorulis.drack.def.DefManager;
import com.skorulis.drack.def.unit.HullPointDef;
import com.skorulis.drack.unit.composite.CompositeUnit;
import com.skorulis.drack.unit.composite.HullAttachment;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.SceneWindow;

public class UnitEditor implements SceneWindow {
	
	private PerspectiveCamera cam;
	private CompositeUnit unit;
	private ModelBatch modelBatch;
	private Environment environment;
	private UnitGestureListener ugl;
	private DefManager def;
	public Set<HullPointNode> pointNodes;
	private SKAssetManager assets;
	private Matrix4 oldTransform;
	
	public UnitEditor(SKAssetManager assets, DefManager def, CompositeUnit unit) {
		resized(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.unit = unit;
        oldTransform = unit.relTransform().cpy();
        unit.relTransform().idt();
        
        pointNodes = new HashSet<HullPointNode>();
        for(HullPointDef hpd : unit.compDef().hull.points) {
        	HullPointNode hpn = new HullPointNode(assets, hpd);
        	HullAttachment att = unit.attachmentAt(hpd);
        	hpn.setHidden(att != null);
        	pointNodes.add(hpn);
        }
        
        modelBatch = new ModelBatch();
        
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
        
        ugl = new UnitGestureListener(this);
        this.assets = assets;
        this.def = def;
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
	
	public CompositeUnit unit() {
		return unit;
	}
	
	public SKAssetManager assets() {
		return assets;
	}
	
	public DefManager def() {
		return def;
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
		return ugl;
	}
	
	public Matrix4 oldTransform() {
		return oldTransform;
	}
	
	public void returnUnitToMap() {
		unit.relTransform().set(oldTransform);
	}
	
	public HullPointNode selectedNode() {
		for(HullPointNode hpn : pointNodes) {
			if(hpn.isSelected()) {
				return hpn;
			}
		}
		return null;
	}
}

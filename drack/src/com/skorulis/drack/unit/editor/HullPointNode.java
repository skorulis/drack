package com.skorulis.drack.unit.editor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.skorulis.drack.def.unit.HullPointDef;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.SceneNode;
import com.skorulis.scene.UpdateInfo;

public class HullPointNode implements SceneNode {

	private HullPointDef def;
	private ModelInstance instance;
	
	public HullPointNode(SKAssetManager assets, HullPointDef def) {
		this.def = def;
		this.instance = new ModelInstance(assets.getModel("sphere"));
		this.instance.transform.setTranslation(this.def.loc);
		this.instance.transform.scl(def.type.size());
	}

	@Override
	public Matrix4 absTransform() {
		return instance.transform;
	}

	@Override
	public Matrix4 relTransform() {
		return instance.transform;
	}

	@Override
	public void render(RenderInfo ri) {
		ri.batch.render(instance,ri.environment);
	}

	@Override
	public SceneNode intersect(Ray ray, Vector3 point) {
		if(Intersector.intersectRaySphere(ray, this.def.loc, this.def.type.size(), point)) {
			return this;
		}
		return null;
	}

	@Override
	public void update(UpdateInfo delta) {
		
	}
	
	public void setSelected(boolean selected) {
		if(selected) {
			this.instance.materials.get(0).set(new ColorAttribute(ColorAttribute.Diffuse,Color.RED));
		} else {
			this.instance.materials.get(0).set(new ColorAttribute(ColorAttribute.Diffuse,Color.BLACK));
		}
		
	}
	
}

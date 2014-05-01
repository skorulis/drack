package com.skorulis.drack.composite.editor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Matrix4;
import com.skorulis.drack.def.attachment.HardPointDef;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.IntersectionList;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.SceneNode;
import com.skorulis.scene.UpdateInfo;

public class HardPointNode implements SceneNode {

	private HardPointDef def;
	private ModelInstance instance;
	private boolean hidden;
	private boolean isSelected;
	
	public HardPointNode(SKAssetManager assets, HardPointDef def) {
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
		if(!hidden) {
			ri.batch.render(instance,ri.environment);
		}
	}

	@Override
	public boolean intersect(IntersectionList list) {
		if(Intersector.intersectRaySphere(list.ray(), this.def.loc, this.def.type.size(), list.tmpPoint)) {
			list.addIntersection(this, list.tmpPoint);
			return true;
		}
		return false;
	}

	@Override
	public void update(UpdateInfo delta) {
		
	}
	
	public void setSelected(boolean selected) {
		this.isSelected = selected;
		if(selected) {
			this.instance.materials.get(0).set(new ColorAttribute(ColorAttribute.Diffuse,Color.RED));
		} else {
			this.instance.materials.get(0).set(new ColorAttribute(ColorAttribute.Diffuse,Color.BLACK));
		}
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	
	public HardPointDef def() {
		return def;
	}
	
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	
	public boolean isAlive() {
		return true;
	}
	
}

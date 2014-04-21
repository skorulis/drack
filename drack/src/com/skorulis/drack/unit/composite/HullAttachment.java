package com.skorulis.drack.unit.composite;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.skorulis.drack.def.unit.HullAttachmentDef;
import com.skorulis.drack.def.unit.HullPointDef;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.SceneNode;
import com.skorulis.scene.UpdateInfo;

public class HullAttachment implements SceneNode{

	private HullPointDef hardPoint;
	private HullAttachmentDef def;
	private ModelInstance modelInstance;
	
	public void loadAssets(SKAssetManager assets) {
		modelInstance = new ModelInstance(assets.getModel(def.modelName));
	}
	
	@Override
	public Matrix4 absTransform() {
		return modelInstance.transform;
	}

	@Override
	public Matrix4 relTransform() {
		return modelInstance.transform;
	}

	@Override
	public void render(RenderInfo ri) {
		ri.batch.render(modelInstance,ri.environment);
	}

	@Override
	public SceneNode intersect(Ray ray, Vector3 point) {
		return null;
	}

	@Override
	public void update(UpdateInfo delta) {
		// TODO Auto-generated method stub
	}
	
	public void setDef(HullAttachmentDef def) {
		this.def = def;
	}
	
	public void setHullPoint(HullPointDef hpd) {
		this.hardPoint = hpd;
		if(this.modelInstance != null && hpd != null) {
			this.modelInstance.transform.setTranslation(hpd.loc);
			this.modelInstance.transform.rotate(def.forwardAxis, hpd.rotation);
		}
	}
	
	public HullPointDef hardPoint() {
		return hardPoint;
	}
	
	

}

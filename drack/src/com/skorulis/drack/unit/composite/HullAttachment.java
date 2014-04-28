package com.skorulis.drack.unit.composite;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.skorulis.drack.def.attachment.HullAttachmentDef;
import com.skorulis.drack.def.attachment.HardPointDef;
import com.skorulis.drack.serialisation.AttachmentJson;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.SceneNode;
import com.skorulis.scene.UpdateInfo;

public class HullAttachment implements SceneNode {

	protected HardPointDef hardPoint;
	protected HullAttachmentDef def;
	protected ModelInstance modelInstance;
	protected Matrix4 offset;
	
	public void loadAssets(SKAssetManager assets) {
		modelInstance = new ModelInstance(assets.getModel(def.modelName));
		offset = new Matrix4();
	}
	
	@Override
	public Matrix4 absTransform() {
		return modelInstance.transform;
	}

	@Override
	public Matrix4 relTransform() {
		return offset;
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
	
	public void updatePosition(SceneNode parent) {
		this.modelInstance.transform = parent.absTransform().cpy().mul(offset);
	}
	
	public void setDef(HullAttachmentDef def) {
		this.def = def;
	}
	
	public void setHullPoint(HardPointDef hpd) {
		this.hardPoint = hpd;
		if(hpd != null) {
			this.offset.setTranslation(hpd.loc);
			this.offset.rotate(def.forwardAxis, hpd.rotation);
		}
	}
	
	public HardPointDef hardPoint() {
		return hardPoint;
	}
	
	public boolean isAlive() {
		return true;
	}
	
	public AttachmentJson getSerialisation() {
		AttachmentJson ret = new AttachmentJson();
		ret.hardPoint = hardPoint.number;
		ret.defName = def.name();
		return ret;
	}

}

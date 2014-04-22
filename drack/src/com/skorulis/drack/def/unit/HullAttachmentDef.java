package com.skorulis.drack.def.unit;

import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.def.BaseDef;
import com.skorulis.drack.unit.composite.HullAttachment;
import com.skorulis.gdx.SKAssetManager;

public class HullAttachmentDef extends BaseDef {

	public String modelName;
	public Class<? extends HullAttachment> attachmentClass;
	public Vector3 forwardAxis;
	
	public HullAttachmentDef(String name) {
		super(name);
		attachmentClass = HullAttachment.class;
		forwardAxis = new Vector3(0,0,1);
	}
	
	public HullAttachment create(SKAssetManager assets, HullPointDef hullPoint) {
		try {
			HullAttachment ret = attachmentClass.newInstance();
			ret.setDef(this);
			ret.loadAssets(assets);
			ret.setHullPoint(hullPoint);
			return ret;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Class<? extends BaseDef> getTypeClass() {
		return HullAttachmentDef.class;
	}

}

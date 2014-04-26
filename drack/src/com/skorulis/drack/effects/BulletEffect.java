package com.skorulis.drack.effects;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.math.Vector3;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.UpdateInfo;

public class BulletEffect {

	private final Vector3 startPos;
	private final Vector3 endPos;
	private final Vector3 dir;
	private ModelInstance instance;
	
	public BulletEffect(SKAssetManager assets, Vector3 start, Vector3 end) {
		this.startPos = start;
		this.endPos = end;
		dir = end.cpy().sub(start).nor();
		instance = new ModelInstance(assets.getModel("cone_helper"));
		instance.transform.setToTranslation(startPos);
		instance.materials.get(0).set(new BlendingAttribute());
	}
	
	public void render(RenderInfo ri) {
		ri.batch.render(instance, ri.environment);
	}
	
	public void update(UpdateInfo ui) {
		Vector3 loc = instance.transform.getTranslation(new Vector3());
		Vector3 camDir = ui.cam.position.cpy().sub(loc).scl(-1);
		
		
	}
	
	public static Set<String> models() {
		HashSet<String> ret = new HashSet<String>();
		ret.add("ball_sprite");
		ret.add("floor_sprite");
		ret.add("cone_helper");
		return ret;
	}
	
}

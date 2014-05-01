package com.skorulis.drack.effects;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.IntersectionList;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.SceneNode;
import com.skorulis.scene.UpdateInfo;

public class BulletEffect implements SceneNode {

	private final Vector3 startPos;
	private final Vector3 endPos;
	private final Vector3 dir;
	private ModelInstance instance;
	private float life;
	private float speed = 10;
	private float timePos = 0;
	
	public BulletEffect(SKAssetManager assets, Vector3 start, Vector3 end) {
		this.startPos = start;
		this.endPos = end;
		dir = end.cpy().sub(start);
		life = dir.len() / speed; 
		dir.nor();
		
		instance = new ModelInstance(assets.getModel("ball_sprite"));
		instance.transform.setToTranslation(startPos);
		instance.materials.get(0).set(new BlendingAttribute());
	}
	
	public void render(RenderInfo ri) {
		ri.batch.render(instance);
	}
	
	public void update(UpdateInfo ui) {
		timePos += ui.delta;
		
		Vector3 loc = startPos.cpy().add(dir.cpy().scl(timePos*speed));
		
		Vector3 camDir = ui.cam.position.cpy().sub(loc).nor();
		
		Vector3 standard = new Vector3(-1,0,0);
		
		float angle = standard.dot(camDir);
		
		angle = (float) Math.acos(angle);
		Vector3 cross = standard.crs(camDir);
		instance.transform.setToTranslation(loc);
		instance.transform.rotateRad(cross, angle);
		
	}
	
	public static Set<String> models() {
		HashSet<String> ret = new HashSet<String>();
		ret.add("ball_sprite");
		ret.add("floor_sprite");
		ret.add("cone_helper");
		return ret;
	}
	
	public boolean isAlive() {
		return timePos < life;
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
	public boolean intersect(IntersectionList list) {
		return false;
	}
	
}

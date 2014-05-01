package com.skorulis.scene;

import com.badlogic.gdx.math.Matrix4;

public interface SceneNode {

	public Matrix4 absTransform();
	public Matrix4 relTransform();
	public void render(RenderInfo ri);
	public boolean intersect(IntersectionList list);
	public void update(UpdateInfo delta);
	public boolean isAlive();
	
}


package com.skorulis.scene;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Matrix4;

public interface SceneNode {

	public Matrix4 absTransform();
	public Matrix4 relTransform();
	public void render(ModelBatch batch, Environment environment);
	
}


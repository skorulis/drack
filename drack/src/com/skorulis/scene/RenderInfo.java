package com.skorulis.scene;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;

public class RenderInfo {

	public ModelBatch batch;
	public Environment environment;
	public Camera cam;
	
	public RenderInfo(ModelBatch batch, Environment environment, Camera cam) {
		this.batch = batch;
		this.environment = environment;
		this.cam = cam;
	}
	
}

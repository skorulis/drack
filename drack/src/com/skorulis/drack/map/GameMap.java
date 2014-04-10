package com.skorulis.drack.map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Matrix4;
import com.skorulis.scene.SceneNode;

public class GameMap implements SceneNode{
	
	public GameMap(AssetManager assets) {
		
	}

	public void render(ModelBatch batch, Environment env) {
		
	}

	@Override
	public Matrix4 absTransform() {
		return new Matrix4();
	}

	@Override
	public Matrix4 relTransform() {
		return new Matrix4();
	}
	
}

package com.skorulis.drack.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Disposable;
import com.skorulis.drack.avatar.Avatar;
import com.skorulis.drack.map.GameMap;
import com.skorulis.scene.SceneNode;

public class GameScene implements SceneNode, Disposable {

	private Avatar avatar;
	private GameMap map;
	private Matrix4 transform;
	
	public GameScene(AssetManager assets) {
		transform = new Matrix4();
		map = new GameMap(50,50,assets);
	}

	@Override
	public Matrix4 absTransform() {
		return transform;
	}

	@Override
	public Matrix4 relTransform() {
		return transform;
	}

	@Override
	public void render(ModelBatch batch, Environment environment) {
		map.render(batch, environment);
	}
	
	public GameMap map() {
		return map;
	}
	
	public SceneNode intersect(Ray ray, Vector3 point) {
		return map.intersect(ray, point);
	}

	@Override
	public void dispose() {
		
	}
	
}

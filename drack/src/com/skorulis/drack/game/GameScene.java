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
import com.skorulis.drack.map.MapSquare;
import com.skorulis.scene.SceneNode;

public class GameScene implements SceneNode, Disposable {

	private Avatar avatar;
	private GameMap map;
	private Matrix4 transform;
	
	public GameScene(AssetManager assets) {
		transform = new Matrix4();
		map = new GameMap(50,50,assets);
		avatar = new Avatar(assets);
	}

	public void nodeSelected(SceneNode node) {
		if(node instanceof MapSquare) {
			MapSquare sq = (MapSquare) node;
			moveAvatar(sq.x(), sq.z());
		}
	}
	
	public void moveAvatar(int x ,int z) {
		avatar.relTransform().setTranslation(x, 0, z);
	}
	
	public GameMap map() {
		return map;
	}
	
	public Avatar avatar() {
		return avatar;
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
		avatar.render(batch, environment);
	}
	
	public SceneNode intersect(Ray ray, Vector3 point) {
		return map.intersect(ray, point);
	}

	@Override
	public void dispose() {
		
	}
	
}

package com.skorulis.drack.map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.skorulis.scene.SceneNode;

public class GameMap implements SceneNode, Disposable{

	private Matrix4 transform;

	private int width,depth;
	private MapSquare[][] squares;
	
	public GameMap(int width, int depth, AssetManager assets) {
		this.width = width;
		this.depth = depth;
		squares = new MapSquare[depth][width];
		for(int i = 0; i < depth; ++i) {
			for(int j = 0; j < width; ++j) {
				squares[i][j] = new MapSquare(assets,j,i);
			}
		}
		
		transform = new Matrix4();
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
		for(int i = 0; i < depth; ++i) {
			for(int j = 0; j < width; ++j) {
				squares[i][j].render(batch, environment);
			}
		}
	}

	@Override
	public void dispose() {
		
	}

	public Vector3 center() {
		return new Vector3(this.width/2.0f, 0, this.depth/2.0f);
	}
	
}

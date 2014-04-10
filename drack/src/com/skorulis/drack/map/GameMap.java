package com.skorulis.drack.map;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.skorulis.scene.SceneNode;

public class GameMap implements SceneNode, Disposable{

	private Matrix4 transform;
	private Model blockModel;
	
	private ArrayList<ModelInstance> walls;
	
	private int width,depth;
	private MapSquare[][] squares;
	
	public GameMap(int width, int depth, AssetManager assets) {
		this.width = width;
		this.depth = depth;
		squares = new MapSquare[depth][width];
		for(int i = 0; i < depth; ++i) {
			for(int j = 0; j < width; ++j) {
				squares[i][j] = new MapSquare(assets);
			}
		}
		
		transform = new Matrix4();
		
		blockModel = assets.get("block", Model.class);
		
		walls = new ArrayList<ModelInstance>();
		for(int i = -1; i < width + 1; ++i) {
			ModelInstance wallInstance = new ModelInstance(blockModel);
			wallInstance.transform.setToTranslation(new Vector3(i + 0.5f, 0.5f, -0.5f));
			walls.add(wallInstance);
			
			wallInstance = new ModelInstance(blockModel);
			wallInstance.transform.setToTranslation(new Vector3(i + 0.5f, 0.5f, depth + 0.5f));
			walls.add(wallInstance);
		}
		
		for(int i = 0; i < depth; ++i) {
			ModelInstance wallInstance = new ModelInstance(blockModel);
			wallInstance.transform.setToTranslation(new Vector3(-0.5f, 0.5f, i + 0.5f));
			walls.add(wallInstance);
			
			wallInstance = new ModelInstance(blockModel);
			wallInstance.transform.setToTranslation(new Vector3(width + 0.5f, 0.5f, i + 0.5f));
			walls.add(wallInstance);
		}
		
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
		batch.render(walls,environment);
		
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

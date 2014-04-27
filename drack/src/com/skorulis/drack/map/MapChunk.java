package com.skorulis.drack.map;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.SceneNode;
import com.skorulis.scene.UpdateInfo;

public class MapChunk implements SceneNode {
	
	private Vector2 offset;
	private MapSquare[][] squares;
	private Matrix4 transform;
	public static final int CHUNK_SIZE = 32;
	public BoundingBox boundingBox;
	
	public MapChunk(Vector2 offset, SKAssetManager assets) {
		this.offset = offset;
		squares = new MapSquare[CHUNK_SIZE][CHUNK_SIZE];
		
		for(int i = 0; i < CHUNK_SIZE; ++i) {
			for(int j = 0; j < CHUNK_SIZE; ++j) {
				squares[i][j] = new MapSquare(assets,(int)offset.x + j,(int)offset.y + i);
			}
		}
		transform = new Matrix4();
		boundingBox = new BoundingBox(new Vector3(offset.x,-1,offset.y), new Vector3(offset.x + CHUNK_SIZE, 5, offset.y + CHUNK_SIZE));
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
	public void render(RenderInfo ri) {
		for(int i = 0; i < CHUNK_SIZE; ++i) {
			for(int j = 0; j < CHUNK_SIZE; ++j) {
				squares[i][j].render(ri);
			}
		}
	}

	@Override
	public SceneNode intersect(Ray ray, Vector3 point) {
		Vector3 point2 = new Vector3();
		if(!Intersector.intersectRayBounds(ray, boundingBox, point2)) {
			return null;
		}
		
		float bestDist = 100000;
		MapSquare best = null;
		
		for(int i = 0; i < CHUNK_SIZE; ++i) {
			for(int j = 0; j < CHUNK_SIZE; ++j) {
				if(Intersector.intersectRayBounds(ray, squares[i][j].boundingBox(), point2)) {
					float dist = point.sub(ray.origin).len();
					if(dist < bestDist) {
						bestDist = dist;
						best = squares[i][j];
						point.set(point2);
					}
				}
				
			}
		}
		return best;
	}

	@Override
	public void update(UpdateInfo info) {
		for(int i = 0; i < CHUNK_SIZE; ++i) {
			for(int j = 0; j < CHUNK_SIZE; ++j) {
				squares[i][j].update(info);
			}
		}
	}

	@Override
	public boolean isAlive() {
		return true;
	}

}

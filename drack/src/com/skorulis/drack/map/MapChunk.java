package com.skorulis.drack.map;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;
import com.skorulis.drack.serialisation.MapChunkJson;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.SceneNode;
import com.skorulis.scene.UpdateInfo;

public class MapChunk implements SceneNode {
	
	private int offsetX;
	private int offsetZ;
	private MapSquare[][] squares;
	private Matrix4 transform;
	public static final int CHUNK_SIZE = 32;
	public BoundingBox boundingBox;
	
	public MapChunk(Vector2 offset, SKAssetManager assets) {
		this.offsetX = (int) offset.x;
		this.offsetZ = (int) offset.y;
		squares = new MapSquare[CHUNK_SIZE][CHUNK_SIZE];
		
		for(int i = 0; i < CHUNK_SIZE; ++i) {
			for(int j = 0; j < CHUNK_SIZE; ++j) {
				squares[i][j] = new MapSquare(assets,offsetX + j,offsetZ + i);
			}
		}
		transform = new Matrix4();
		boundingBox = new BoundingBox(new Vector3(offsetX - 0.5f,-1,offsetZ - 0.5f), new Vector3(offsetX + CHUNK_SIZE, 5, offsetZ + CHUNK_SIZE));
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
		if(!ri.cam.frustum.boundsInFrustum(boundingBox)) {
			return;
		}
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
		SceneNode best = null;
		
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
	
	public MapSquare squareAt(int x, int z) {
		return squares[z - offsetZ][x - offsetX];
	}
	
	public MapSquare squareAtIndex(int index) {
		if(index >= CHUNK_SIZE * CHUNK_SIZE) {
			throw new IndexOutOfBoundsException("Index " + index + " out of chunk bounds");
		}
		int z = index / CHUNK_SIZE;
		int x = index - z * CHUNK_SIZE;
		return squares[z][x];
	}

	@Override
	public boolean isAlive() {
		return true;
	}
	
	public MapChunkJson getSerialisation() {
		MapChunkJson json = new MapChunkJson();
		json.offsetX = offsetX;
		json.offsetZ = offsetZ;
		
		for(int i = 0; i < CHUNK_SIZE; ++i) {
			for(int j = 0; j < CHUNK_SIZE; ++j) {
				json.squares.add(squares[i][j].getSerialisation());
			}
		}
		
		return json;
		
	}

}

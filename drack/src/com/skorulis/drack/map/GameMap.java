package com.skorulis.drack.map;

import java.util.ArrayList;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
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
	
	public void update(float delta) {
		
	}
	
	public MapSquare intersectRay(Ray ray) {
		float bestDist = 100000;
		MapSquare best = null;
		Vector3 point = new Vector3();
		for(int i = 0; i < depth; ++i) {
			for(int j = 0; j < width; ++j) {
				if(Intersector.intersectRayBounds(ray, squares[i][j].boundingBox(), point)) {
					float dist = point.sub(ray.origin).len();
					if(dist < bestDist) {
						bestDist = dist;
						best = squares[i][j];
					}
				}
				
			}
		}
		return best;
	}
	
	public SceneNode intersect(Ray ray, Vector3 point) {
		float bestDist = 100000;
		Vector3 bestPoint = null;
		MapSquare best = null;
		for(int i = 0; i < depth; ++i) {
			for(int j = 0; j < width; ++j) {
				if(Intersector.intersectRayBounds(ray, squares[i][j].boundingBox(), point)) {
					float dist = point.sub(ray.origin).len();
					if(dist < bestDist) {
						bestDist = dist;
						bestPoint = point.cpy();
						best = squares[i][j];
					}
				}
				
			}
		}
		if(best != null) {
			point.set(bestPoint);
		}
		
		return best;
	}
	
	public ArrayList<MapSquare> adjacentSquares(MapSquare square) {
		int x = square.x();
		int y = square.z();
		
		ArrayList<MapSquare> ret = new ArrayList<MapSquare>();
		
		for(int i = y - 1; i <= y + 1; ++i) {
			for(int j = x - 1; j <= x + 1; ++j) {
				if(i < 0 || i >= depth || j < 0 || j >= width) {
					continue;
				}
				if(i == y && j == x) {
					continue;
				}
				ret.add(squares[i][j]);
			}
		}
		return ret;
	}

	@Override
	public void dispose() {
		
	}

	public Vector3 center() {
		return new Vector3(this.width/2.0f, 0, this.depth/2.0f);
	}
	
}

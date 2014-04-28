package com.skorulis.drack.map;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Disposable;
import com.skorulis.drack.building.Building;
import com.skorulis.drack.serialisation.MapJson;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.SceneNode;
import com.skorulis.scene.UpdateInfo;

public class GameMap implements SceneNode, Disposable{

	private Matrix4 transform;
	private int width,depth;
	private SKAssetManager assets;
	private MapChunk[][] chunks;
	
	public GameMap(int width, int depth, SKAssetManager assets) {
		this.width = width;
		this.depth = depth;
		this.assets = assets;
		
		chunks = new MapChunk[depth / MapChunk.CHUNK_SIZE][width / MapChunk.CHUNK_SIZE];
		for(int i = 0; i < chunks.length; ++i) {
			for(int j = 0; j < chunks[i].length; ++j) {
				chunks[i][j] = new MapChunk(new Vector2(j * MapChunk.CHUNK_SIZE, i * MapChunk.CHUNK_SIZE), assets);
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
	
	public Vector3 groundIntersection(Ray ray) {
		Plane plane = new Plane(new Vector3(0, 1, 0), 0);
		Vector3 ret = new Vector3();
		Intersector.intersectRayPlane(ray, plane, ret);
		return ret;
	}

	@Override
	public void render(RenderInfo ri) {
		for(int i = 0; i < chunks.length; ++i) {
			for(int j = 0; j < chunks[i].length; ++j) {
				chunks[i][j].render(ri);
			}
		}
	}
	
	public void update(UpdateInfo info) {
		for(int i = 0; i < chunks.length; ++i) {
			for(int j = 0; j < chunks[i].length; ++j) {
				chunks[i][j].update(info);
			}
		}
	}

	public SceneNode intersect(Ray ray, Vector3 point) {
		float bestDist = 100000;
		Vector3 bestPoint = null;
		SceneNode best = null;
		for(int i = 0; i < chunks.length; ++i) {
			for(int j = 0; j < chunks[i].length; ++j) {
				SceneNode n = chunks[i][j].intersect(ray, point);
				if(n != null) {
					float dist = point.sub(ray.origin).len();
					if(dist < bestDist) {
						bestDist = dist;
						bestPoint = point.cpy();
						best = n;
					}
				}
			}
		}
		if(best != null) {
			point.set(bestPoint);
		}
		
		return best;
	}
	
	public Set<MapSquare> adjacentSquares(MapSquare square) {
		int x = square.x();
		int y = square.z();
		
		HashSet<MapSquare> ret = new HashSet<MapSquare>();
		
		for(int i = y - 1; i <= y + 1; ++i) {
			for(int j = x - 1; j <= x + 1; ++j) {
				if(i < 0 || i >= depth || j < 0 || j >= width) {
					continue;
				}
				if(i == y && j == x) {
					continue;
				}
				ret.add(this.squareAt(j, i));
			}
		}
		return ret;
	}
	
	public Set<MapSquare> squaresAround(Building building) {
		Set<MapSquare> covered = building.coveredSquares();
		HashSet<MapSquare> ret = new HashSet<MapSquare>();
		for(MapSquare ms: covered) {
			ret.addAll(adjacentSquares(ms));
		}
		ret.removeAll(covered);
		return ret;
	}
	
	public MapSquare[][] getSquareAround(Vector3 loc, int size) {
		MapSquare[][] block = new MapSquare[size*2 + 1][size*2 + 1];
		int x = Math.round(loc.x);
		int z = Math.round(loc.z);
		for(int i = z - size; i <= z + size; ++i) {
			for(int j = x - size; j <= x + size; ++j) {
				if(i >= 0 && j >= 0 && i < depth && j < width) {
					block[i - z + size][j - x + size] = squareAt(j, i);
				}
			}
		}
		
		return block;
	}

	@Override
	public void dispose() {
		
	}

	public Vector3 center() {
		return new Vector3(this.width/2.0f, 0, this.depth/2.0f);
	}
	
	public SKAssetManager assets() {
		return assets;
	}
	
	public MapSquare squareAt(int x, int z) {
		if(x >= 0 && z >= 0 && x < width && z < depth) {
			int cx = x / MapChunk.CHUNK_SIZE;
			int cz = z / MapChunk.CHUNK_SIZE;
			
			MapChunk chunk = chunks[cz][cx];
			return chunk.squareAt(x, z);
		}
		return null;
	}
	
	public MapSquare squareAt(Vector3 loc) {
		int x = Math.round(loc.x);
		int z = Math.round(loc.z);
		
		if(x >= 0 && z >= 0 && x < width && z < depth) {
			int cx = x / MapChunk.CHUNK_SIZE;
			int cz = z / MapChunk.CHUNK_SIZE;
			
			MapChunk chunk = chunks[cz][cx];
			return chunk.squareAt(x, z);
		}
		return null;
	}
	
	public boolean canPlaceBuilding(Building b, Vector3 at) {
		for(int i = 0; i < b.def().width; ++i) {
			for(int j = 0; j < b.def().depth; ++j) {
				MapSquare sq = this.squareAt((int)at.x + j, (int)at.z + i);
				if(sq == null || !sq.isPassable()) {
					return false;
				}
			}
		}
		return true;
	}
	
	public int width() {
		return width;
	}
	
	public int depth() {
		return depth;
	}
	
	public boolean isAlive() {
		return true;
	}	
	
	public MapJson getSerialisation() {
		MapJson ret = new MapJson();
		ret.width = width;
		ret.depth = depth;
		for(int i = 0; i < chunks.length; ++i) {
			for(int j = 0; j < chunks[i].length; ++j) {
				ret.chunks.add(chunks[i][j].getSerialisation());
			}
		}
		return ret;
	}
}

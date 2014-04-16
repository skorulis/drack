package com.skorulis.drack.game;

import java.util.Set;

import com.skorulis.drack.map.GameMap;
import com.skorulis.drack.map.MapSquare;
import com.skorulis.drack.pathfinding.MapPath;
import com.skorulis.drack.pathfinding.PathFinder;
import com.skorulis.scene.SceneNode;

public class GameLogic {

	private GameScene scene;
	private GameDelegate delegate;
	
	public GameLogic(GameScene scene, GameDelegate delegate) {
		this.scene = scene;
		this.delegate = delegate;
	}
	
	public void nodeSelected(SceneNode node) {
		if(node instanceof MapSquare) {
			MapSquare sq = (MapSquare) node;
			MapSquare current = map().squareAt(scene.player().controllUnit().currentPosition());
			if(sq == current) {
				return;
			}
			Set<MapSquare> near = null;
			if(sq.isPassable()) {
				
			} else if(sq.anyBuilding() != null) {
				near = map().adjacentSquares(sq);
			}
			
			PathFinder finder = new PathFinder(map(), current, sq, near);
			MapPath path = finder.generatePath();
			scene.player().controllUnit().setPath(path);
			this.delegate.playerMoved();
			
			if(sq.anyBuilding() != null) {
				this.delegate.buildingSelected(sq.anyBuilding());
			}
		}
	}
	
	public GameMap map() {
		return scene.map();
	}
	
	public GameScene scene() {
		return scene;
	}
	
}

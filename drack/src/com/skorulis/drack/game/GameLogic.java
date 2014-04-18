package com.skorulis.drack.game;

import com.skorulis.drack.map.GameMap;
import com.skorulis.drack.map.MapSquare;
import com.skorulis.drack.pathfinding.MapPath;
import com.skorulis.drack.pathfinding.PathFinder;
import com.skorulis.drack.player.Player;
import com.skorulis.scene.SceneNode;

public class GameLogic {

	private GameScene scene;
	private GameDelegate delegate;
	private Player player;
	
	public GameLogic(GameScene scene, GameDelegate delegate, Player player) {
		this.scene = scene;
		this.delegate = delegate;
		this.player = player;
	}
	
	public void nodeSelected(SceneNode node) {
		if(node instanceof MapSquare) {
			MapSquare sq = (MapSquare) node;
			MapSquare current = map().squareAt(player.controllUnit().currentPosition());
			if(sq == current) {
				return;
			}
			
			PathFinder finder = new PathFinder(map());
			MapPath path = finder.navigate(current, sq);
			player.controllUnit().setPath(path);
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
	
	public Player player() {
		return player;
	}
	
}

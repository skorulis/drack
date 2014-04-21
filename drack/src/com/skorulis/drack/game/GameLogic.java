package com.skorulis.drack.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.utils.Disposable;
import com.skorulis.drack.map.GameMap;
import com.skorulis.drack.map.MapSquare;
import com.skorulis.drack.pathfinding.MapPath;
import com.skorulis.drack.pathfinding.PathFinder;
import com.skorulis.drack.player.Player;
import com.skorulis.drack.unit.editor.UnitEditor;
import com.skorulis.scene.SceneNode;

public class GameLogic implements Disposable {

	private GameScene scene;
	private GameDelegate delegate;
	private Player player;
	private UnitEditor unitEditor;
	private IsoPerspectiveCamera isoCam;
	private ModelBatch modelBatch;
	private Environment environment;
	
	public GameLogic(GameScene scene, GameDelegate delegate, Player player) {
		this.scene = scene;
		this.delegate = delegate;
		this.player = player;
		modelBatch = new ModelBatch();
		this.unitEditor = new UnitEditor(scene.assets(),scene.def());
		modelBatch = new ModelBatch();
		createEnvironment();
		isoCam = new IsoPerspectiveCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),10);
		isoCam.setTracking(player.controllUnit());
	}
	
	private void createEnvironment() {
		environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
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
	
	public UnitEditor unitEditor() {
		return unitEditor;
	}
	
	public IsoPerspectiveCamera isoCam() {
		return isoCam;
	}
	
	public ModelBatch batch() {
		return modelBatch;
	}
	
	public Environment environment() {
		return environment;
	}
	
	public void dispose() {
		modelBatch.dispose();
	}
	
}

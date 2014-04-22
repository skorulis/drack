package com.skorulis.drack.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.skorulis.drack.effects.LaserEffect;
import com.skorulis.drack.map.GameMap;
import com.skorulis.drack.map.MapSquare;
import com.skorulis.drack.pathfinding.MapPath;
import com.skorulis.drack.pathfinding.PathFinder;
import com.skorulis.drack.player.Player;
import com.skorulis.drack.unit.Unit;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.SceneNode;
import com.skorulis.scene.SceneWindow;
import com.skorulis.scene.UpdateInfo;

public class GameLogic implements Disposable, SceneWindow {

	private GameScene scene;
	private GameDelegate delegate;
	private Player player;
	private IsoPerspectiveCamera isoCam;
	private ModelBatch modelBatch;
	private Environment environment;
	private GameEventListener eventListener;
	private LaserEffect laser;
	
	public GameLogic(GameScene scene, GameDelegate delegate, Player player) {
		this.scene = scene;
		this.delegate = delegate;
		this.player = player;
		modelBatch = new ModelBatch();
		createEnvironment();
		isoCam = new IsoPerspectiveCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),10);
		isoCam.setTracking(player.controllUnit());
		
		eventListener = new GameEventListener(this);
		laser = new LaserEffect(scene.assets(), new Vector3(10,2,10), new Vector3(14,2,10));
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
		} else if(node instanceof Unit) {
			System.out.println("UNIT");
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

	@Override
	public void draw() {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        
        RenderInfo ri = new RenderInfo(batch(), environment(), isoCam().cam());
        batch().begin(ri.cam);
        scene.render(ri);
        laser.render(ri);
        batch().end();
        
	}

	@Override
	public void update(float delta) {
		UpdateInfo info = new UpdateInfo(delta, this);
		isoCam().update(delta);
		scene.update(info);
	}

	@Override
	public void resized(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GestureListener gestureListener() {
		return eventListener;
	}
	
}

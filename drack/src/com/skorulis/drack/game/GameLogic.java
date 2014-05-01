package com.skorulis.drack.game;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.utils.Disposable;
import com.skorulis.drack.map.GameMap;
import com.skorulis.drack.map.MapSquare;
import com.skorulis.drack.pathfinding.MapPath;
import com.skorulis.drack.pathfinding.PathFinder;
import com.skorulis.drack.player.Player;
import com.skorulis.drack.unit.Unit;
import com.skorulis.scene.IntersectionResult;
import com.skorulis.scene.RenderInfo;
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
	
	public GameLogic(GameScene scene, GameDelegate delegate) {
		this.scene = scene;
		this.delegate = delegate;
		this.player = scene.players().humanPlayer();
		modelBatch = new ModelBatch();
		createEnvironment();
		isoCam = new IsoPerspectiveCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),10);
		isoCam.setTracking(player.controllUnit());
		
		eventListener = new GameEventListener(this);
	}
	
	private void createEnvironment() {
		environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
	}
	
	public void handleIntersections(ArrayList<IntersectionResult> hits) {
		for(IntersectionResult ir : hits) {
			if(this.handleHit(ir)) {
				return;
			}
		}
	}
	
	private boolean handleHit(IntersectionResult hit) {
		if(hit.node() instanceof Unit) {
			Unit unit = (Unit) hit.node();
			if(unit != player.controllUnit() && player.controllUnit().owner() != unit.owner()) {
				player.controllUnit().attack(unit);
				return true;
			}
			return false;
		} else if(hit.node() instanceof MapSquare) {
			MapSquare sq = (MapSquare) hit.node();
			MapSquare current = map().squareAt(player.controllUnit().currentPosition());
			if(sq == current) {
				return false;
			}
			
			PathFinder finder = new PathFinder(map());
			MapPath path = finder.navigate(current, sq);
			player.controllUnit().setPath(path);
			this.delegate.playerMoved();
			
			if(sq.anyBuilding() != null) {
				this.delegate.buildingSelected(sq.anyBuilding());
			}
			return true;
		}
		return false;
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
        batch().end();
	}

	@Override
	public void update(float delta) {
		isoCam().update(delta);
		UpdateInfo info = new UpdateInfo(delta, this);
		info.cam = isoCam.cam();
		scene.update(info);
	}

	@Override
	public void resized(int width, int height) {
		isoCam().resize(width, height);
	}

	@Override
	public GestureListener gestureListener() {
		return eventListener;
	}
	
}

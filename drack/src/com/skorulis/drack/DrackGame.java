package com.skorulis.drack;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.skorulis.drack.map.GameLevel;
import com.skorulis.drack.map.GameMap;

public class DrackGame implements ApplicationListener {
	
	private IsoPanCamera camera;
	private ModelBatch modelBatch;
    private Environment environment;
    private AssetManager assets;
    private boolean loading = true;
    private GameMap map;
    private InputMultiplexer inputPlexer;
    private GameLevel level;
    
    private ModelInstance mi;
	
	@Override
	public void create() {
		modelBatch = new ModelBatch();
		assets = new AssetManager();
		assets.load("data/cube1.g3db", Model.class);
		assets.load("data/hull1.g3db", Model.class);
        
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
        
		camera = new IsoPanCamera();
		
		inputPlexer = new InputMultiplexer(camera);
        Gdx.input.setInputProcessor(inputPlexer);
        
        level = new GameLevel(25,25);
	}
	
	private void finishedLoading() {
		loading = false;
		map = new GameMap(assets);
		
		mi = new ModelInstance(assets.get("data/hull1.g3db", Model.class));
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(loading) {
			if(assets.update()) {
				finishedLoading();
			} else {
				return;
			}
		}
		
		camera.cam().update();
		modelBatch.begin(camera.cam());
		//map.render(modelBatch, environment);
		//modelBatch.render(mi,environment);
		level.render(modelBatch, environment);
		modelBatch.end();
		
		update();
	}
	
	private void update() {
		float delta = Gdx.graphics.getDeltaTime();
		
		camera.update(delta);
	}

	@Override
	public void resize(int width, int height) {
		camera.resizeViewport();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
	@Override
	public void dispose() {
		
	}
}

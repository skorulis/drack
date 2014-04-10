package com.skorulis.drack;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.skorulis.drack.def.DefManager;
import com.skorulis.drack.map.GameMap;
import com.skorulis.gdx.SKAssetManager;

public class DrackGame implements ApplicationListener {
	
	private IsoPanCamera isoCam;
	private ModelBatch modelBatch;
	private Environment environment;
	private SKAssetManager assets;
	private boolean loading;
    
	private GameMap level;
	private DefManager def;
    
    public InputMultiplexer inputPlexer;
	
	@Override
	public void create() {
		modelBatch = new ModelBatch();
		def = new DefManager();
		assets = new SKAssetManager();
		assets.load("data/cube1.g3db", Model.class);
		assets.loadAll(def.allTextures(), Texture.class);
		
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
                
        loading = true;
        
        isoCam = new IsoPanCamera();
        
        inputPlexer = new InputMultiplexer(isoCam);
        Gdx.input.setInputProcessor(inputPlexer);
        
	}
	
	private void doneLoading() {
        loading = false;
        assets.addAllModels(def.buildModels(assets));
        level = new GameMap(25,25,assets);
    }

	@Override
	public void dispose() {
		modelBatch.dispose();
		level.dispose();
		assets.clear();
	}

	@Override
	public void render() {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		if(loading) {
			if(assets.update()) {
				doneLoading();
			} else {
				return;
			}
		}
		
		
		
        isoCam.cam().update();
        modelBatch.begin(isoCam.cam());
        level.render(modelBatch, environment);
        modelBatch.end();
        
        update();
	}
	
	private void update() {
		float delta = Gdx.graphics.getDeltaTime();
		
		isoCam.update(delta);
	}

	@Override
	public void resize(int width, int height) {
		isoCam.resizeViewport();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}

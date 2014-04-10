package com.skorulis.drack;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.skorulis.drack.map.GameLevel;

public class DrackGame implements ApplicationListener {
	
	public IsoPanCamera isoCam;
    public ModelBatch modelBatch;
    public Environment environment;
    public AssetManager assets;
    public boolean loading;
    
    public GameLevel level;
    
    public InputMultiplexer inputPlexer;
	
	@Override
	public void create() {
		modelBatch = new ModelBatch();
		assets = new AssetManager();
        
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        level = new GameLevel(25,25);
                
        loading = true;
        
        isoCam = new IsoPanCamera();
        
        inputPlexer = new InputMultiplexer(isoCam);
        Gdx.input.setInputProcessor(inputPlexer);
        
	}
	
	private void doneLoading() {
        loading = false;
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

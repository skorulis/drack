package com.skorulis.drack;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.skorulis.drack.map.GameMap;

public class DrackGame implements ApplicationListener {
	
	private OrthographicCamera camera;
	private ModelBatch modelBatch;
    private Environment environment;
    private AssetManager assets;
    private boolean loading = true;
    private GameMap map;
	
	@Override
	public void create() {
		modelBatch = new ModelBatch();
		assets = new AssetManager();
        
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
        
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(1, h/w);
	}
	
	private void finishedLoading() {
		loading = false;
		map = new GameMap();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(loading) {
			if(assets.update()) {
				finishedLoading();
			} else {
				return;
			}
		}
		
		modelBatch.begin(camera);
		map.render(modelBatch, environment);
		modelBatch.end();
		
		
	}

	@Override
	public void resize(int width, int height) {
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

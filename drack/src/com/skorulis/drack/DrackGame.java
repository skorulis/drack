package com.skorulis.drack;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.input.GestureDetector;
import com.skorulis.drack.def.DefManager;
import com.skorulis.drack.game.GameScene;
import com.skorulis.drack.map.MapGenerator;
import com.skorulis.drack.ui.UIManager;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;

public class DrackGame implements ApplicationListener {
	
	private IsoPerspectiveCamera isoCam;
	private ModelBatch modelBatch;
	private Environment environment;
	private SKAssetManager assets;
	private boolean loading;
   
	private DefManager def;
    
    private InputMultiplexer inputPlexer;
    private GameEventListener eventListener;
    private GameScene scene;
    private UIManager ui;
	
	@Override
	public void create() {
		modelBatch = new ModelBatch();
		def = new DefManager();
		assets = new SKAssetManager();
		loadAssets();
		
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
                
        loading = true;
        
        isoCam = new IsoPerspectiveCamera();
        eventListener = new GameEventListener(isoCam);
        
        
        
	}
	
	private void loadAssets() {
		assets.loadAll(def.allTextures(), Texture.class);
		assets.loadAllModels(def.allModels());
		assets.loadAll(def.allTextureAtlases(),TextureAtlas.class);
	}
	
	private void doneLoading() {
        loading = false;
        assets.addAllModels(def.buildModels(assets));
        MapGenerator mapGen = new MapGenerator(50, 50, assets,def);
        scene = new GameScene(assets,mapGen.map());
        eventListener.setScene(scene);
        isoCam.setTracking(scene.avatar());
        
        ui = new UIManager(assets,scene,def,isoCam);
        inputPlexer = new InputMultiplexer(ui.stage(), new GestureDetector(eventListener));
        Gdx.input.setInputProcessor(inputPlexer);
    }

	@Override
	public void dispose() {
		modelBatch.dispose();
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
        RenderInfo ri = new RenderInfo(modelBatch, environment, isoCam.cam());
        modelBatch.begin(isoCam.cam());
        scene.render(ri);
        modelBatch.end();
        
        ui.stage().draw();
        
        update();
	}
	
	private void update() {
		float delta = Gdx.graphics.getDeltaTime();
		
		isoCam.update(delta);
		scene.update(delta);
		ui.update(delta);
	}

	@Override
	public void resize(int width, int height) {
		isoCam.resize(width, height);
		if(ui != null) {
			ui.resized(width, height);
		}
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}

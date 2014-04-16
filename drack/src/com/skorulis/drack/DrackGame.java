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
import com.skorulis.drack.building.Building;
import com.skorulis.drack.def.DefManager;
import com.skorulis.drack.effects.Effect2DLayer;
import com.skorulis.drack.game.GameDelegate;
import com.skorulis.drack.game.GameScene;
import com.skorulis.drack.game.SceneGenerator;
import com.skorulis.drack.map.MapGenerator;
import com.skorulis.drack.ui.StyleManager;
import com.skorulis.drack.ui.UIManager;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;

public class DrackGame implements ApplicationListener, GameDelegate {
	
	private IsoPerspectiveCamera isoCam;
	private ModelBatch modelBatch;
	private Environment environment;
	private DefManager def;
	private SKAssetManager assets;
	private boolean loading = true;
   
    private InputMultiplexer inputPlexer;
    private GameEventListener eventListener;
    private GameScene scene;
    private UIManager ui;
    private TextureGenerator textureGen;
    private Effect2DLayer info;
    private StyleManager styleManager;
	
	@Override
	public void create() {
		modelBatch = new ModelBatch();
		def = new DefManager();
		assets = new SKAssetManager();
		loadAssets();
		
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
        
        isoCam = new IsoPerspectiveCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),10);
        eventListener = new GameEventListener(isoCam);
	}
	
	private void loadAssets() {
		assets.loadAll(def.allTextures(), Texture.class);
		assets.loadAllModels(def.allModels());
		assets.loadAll(def.allTextureAtlases(),TextureAtlas.class);
	}
	
	private void doneLoading() {
        loading = false;
        styleManager = new StyleManager();
        info = new Effect2DLayer(styleManager.defaultSkin(),isoCam);
        
        assets.addAllModels(def.buildModels(assets));
        MapGenerator mapGen = new MapGenerator(50, 50, assets,def);
        
        SceneGenerator sceneGen = new SceneGenerator(mapGen, info);
        
        scene = sceneGen.scene();
        scene.setDelegate(this);
        eventListener.setScene(scene);
        isoCam.setTracking(scene.player().controllUnit());
        
        ui = new UIManager(assets,scene,def,isoCam,styleManager);
        
        inputPlexer = new InputMultiplexer(ui.stage(), new GestureDetector(eventListener));
        Gdx.input.setInputProcessor(inputPlexer);
        
        textureGen = new TextureGenerator(assets, def);
    }

	@Override
	public void dispose() {
		modelBatch.dispose();
		assets.clear();
	}

	@Override
	public void render() {
		
		if(loading) {
			if(assets.update()) {
				doneLoading();
			} else {
				return;
			}
		}
		if(!textureGen.finished()) {
			textureGen.render(environment);
			return;
		}
		
        renderMain();
	}
	
	private void renderMain() {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        
        isoCam.cam().update();
        RenderInfo ri = new RenderInfo(modelBatch, environment, isoCam.cam());
        modelBatch.begin(isoCam.cam());
        scene.render(ri);
        modelBatch.end();
        
        info.stage().draw();
        
        ui.stage().draw();
        
        update();
	}
	
	private void update() {
		float delta = Gdx.graphics.getDeltaTime();
		
		isoCam.update(delta);
		scene.update(delta);
		ui.update(delta);
		info.update(delta);
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

	@Override
	public void buildingSelected(Building building) {
		ui.showBuildingUI(building);
	}
	
	public void playerMoved() {
		ui.clearBuildingUI();
	}
}

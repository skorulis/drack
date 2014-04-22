package com.skorulis.drack;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.input.GestureDetector;
import com.skorulis.drack.building.Building;
import com.skorulis.drack.def.DefManager;
import com.skorulis.drack.effects.Effect2DLayer;
import com.skorulis.drack.game.GameDelegate;
import com.skorulis.drack.game.GameEventListener;
import com.skorulis.drack.game.GameLogic;
import com.skorulis.drack.game.GameScene;
import com.skorulis.drack.game.IsoPerspectiveCamera;
import com.skorulis.drack.game.SceneGenerator;
import com.skorulis.drack.map.MapGenerator;
import com.skorulis.drack.player.Player;
import com.skorulis.drack.ui.StyleManager;
import com.skorulis.drack.ui.UIManager;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.UpdateInfo;

public class DrackGame implements ApplicationListener, GameDelegate {
	
	private DefManager def;
	private SKAssetManager assets;
	private boolean loading = true;
   
    private InputMultiplexer inputPlexer;
    private GameEventListener eventListener;
    private GameScene scene;
    private UIManager ui;
    private TextureGenerator textureGen;
    private Effect2DLayer effects2D;
    private StyleManager styleManager;
    private GameLogic logic;
    private boolean showingEditor;
	
	@Override
	public void create() {
		def = new DefManager();
		assets = new SKAssetManager();
		loadAssets();
		
        eventListener = new GameEventListener();
	}
	
	private void loadAssets() {
		assets.loadAll(def.allTextures(), Texture.class);
		assets.loadAllModels(def.allModels());
	}
	
	private void doneLoading() {
        loading = false;
        styleManager = new StyleManager();
        Player player = new Player();
        effects2D = new Effect2DLayer(styleManager.defaultSkin());
        
        MapGenerator mapGen = new MapGenerator(50, 50, assets, def, player);
        
        SceneGenerator sceneGen = new SceneGenerator(mapGen, effects2D, player);
        
        scene = sceneGen.scene();
        
        logic = new GameLogic(scene, this, player);
        eventListener.setLogic(logic);
        
        effects2D.setCam(logic.isoCam());
        ui = new UIManager(assets,logic,def,styleManager,this);
        
        inputPlexer = new InputMultiplexer(ui.stage(), new GestureDetector(eventListener));
        inputPlexer.addProcessor(logic.unitEditor().gestureDetector());
        Gdx.input.setInputProcessor(inputPlexer);
        
        textureGen = new TextureGenerator(assets, def);
    }

	@Override
	public void dispose() {
		logic.dispose();
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
			textureGen.render(logic.environment());
			return;
		}
		
        renderMain();
	}
	
	private void renderMain() {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        
        update();
        drawGame();
        if(showingEditor) {
        	logic.unitEditor().draw();
        }
	}
	
	private void drawGame() {
		logic.isoCam().cam().update();
        RenderInfo ri = new RenderInfo(logic.batch(), logic.environment(), logic.isoCam().cam());
        logic.batch().begin(logic.isoCam().cam());
        scene.render(ri);
        logic.batch().end();
        
        effects2D.stage().draw();
        
        ui.stage().draw();
	}
	
	private void update() {
		UpdateInfo info = new UpdateInfo(Gdx.graphics.getDeltaTime(), logic);
		logic.isoCam().update(info.delta);
		scene.update(info);
		ui.update(info.delta);
		this.effects2D.update(info.delta);
		
		logic.unitEditor().update();
	}

	@Override
	public void resize(int width, int height) {
		
		if(ui != null) {
			ui.resized(width, height);
		}
		if(logic != null) {
			logic.isoCam().resize(width, height);
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
	
	public void showUnitEditor() {
		showingEditor = true;
	}
}

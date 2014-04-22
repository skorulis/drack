package com.skorulis.drack;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.input.GestureDetector;
import com.skorulis.drack.building.Building;
import com.skorulis.drack.def.DefManager;
import com.skorulis.drack.effects.Effect2DLayer;
import com.skorulis.drack.game.GameDelegate;
import com.skorulis.drack.game.GameLogic;
import com.skorulis.drack.game.GameScene;
import com.skorulis.drack.game.SceneGenerator;
import com.skorulis.drack.map.MapGenerator;
import com.skorulis.drack.player.Player;
import com.skorulis.drack.ui.StyleManager;
import com.skorulis.drack.ui.UIManager;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.SceneWindow;

public class DrackGame implements ApplicationListener, GameDelegate {
	
	private DefManager def;
	private SKAssetManager assets;
	private boolean loading = true;
   
    private InputMultiplexer inputPlexer;
    
    private UIManager ui;
    private TextureGenerator textureGen;
    private Effect2DLayer effects2D;
    private StyleManager styleManager;
    private GameLogic logic;
    private SceneWindow subScene;
	
	@Override
	public void create() {
		def = new DefManager();
		assets = new SKAssetManager();
		inputPlexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(inputPlexer);
		loadAssets();
	}
	
	private void loadAssets() {
		assets.loadAll(def.allTextures(), Texture.class);
		assets.loadAllModels(def.allModels());
	}
	
	private void doneLoading() {
        loading = false;
        styleManager = new StyleManager();
        Player player = new Player();
        effects2D = new Effect2DLayer(styleManager.gameSkin());
        
        MapGenerator mapGen = new MapGenerator(50, 50, assets, def, player);
        
        SceneGenerator sceneGen = new SceneGenerator(mapGen, effects2D, player);
        
        GameScene scene = sceneGen.scene();
        
        logic = new GameLogic(scene, this, player);
        
        effects2D.setCam(logic.isoCam());
        ui = new UIManager(assets,logic,def,styleManager,this);
        
        updateInputProcessor();
        
        textureGen = new TextureGenerator(assets, def);
    }

	private void updateInputProcessor() {
		inputPlexer.clear();
		inputPlexer.addProcessor(ui.stage());
		if(logic != null && subScene == null) {
			inputPlexer.addProcessor(new GestureDetector(logic.gestureListener()));
		}
		if(subScene != null) {
			inputPlexer.addProcessor(new GestureDetector(subScene.gestureListener()));
		}
	}
	
	@Override
	public void dispose() {
		if(logic != null) {
			logic.dispose();
		}
		
		assets.dispose();
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
		update();
		
        drawGame();
	}
	
	private void drawGame() {
        logic.draw();
        
        effects2D.stage().draw();
        ui.stage().draw();
        
        if(subScene != null) {
        	subScene.draw();
        }
	}
	
	private void update() {
		float delta = Gdx.graphics.getDeltaTime();
		
		logic.update(delta);
		if(subScene != null) {
			subScene.update(delta);
		}
		ui.update(delta);
		this.effects2D.update(delta);
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
	
	public void setSubScene(SceneWindow sw) {
		this.subScene = sw;
		updateInputProcessor();
	}
}

package com.skorulis.drack;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.skorulis.drack.building.Building;
import com.skorulis.drack.def.DefManager;
import com.skorulis.drack.effects.Effect2DLayer;
import com.skorulis.drack.game.GameDelegate;
import com.skorulis.drack.game.GameLogic;
import com.skorulis.drack.game.GameScene;
import com.skorulis.drack.game.SceneGenerator;
import com.skorulis.drack.map.MapGenerator;
import com.skorulis.drack.player.PlayerContainer;
import com.skorulis.drack.serialisation.GameSerialiser;
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
    
    private SceneWindow mainScene;
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
		assets.loadAllTextures(def.allTextures());
		assets.loadAllModels(def.allModels());
	}
	
	private void doneLoading() {
        loading = false;
        styleManager = new StyleManager();
        
        effects2D = new Effect2DLayer(styleManager.gameSkin());
        
        GameSerialiser serialiser = new GameSerialiser(def,assets,effects2D);
        
        GameScene scene = serialiser.read();
        if(scene == null) {
        	PlayerContainer players = new PlayerContainer();
        	MapGenerator mapGen = new MapGenerator(64, 64, assets, def, players);
        	mapGen.generateMap();
            SceneGenerator sceneGen = new SceneGenerator(mapGen, effects2D, players);
            scene = sceneGen.scene();
        }
        
        logic = new GameLogic(scene, this);
        
        effects2D.setCam(logic.isoCam());
        ui = new UIManager(assets,logic,def,styleManager,this);
        
        mainScene = logic;
        //mainScene = new com.skorulis.drack.test.TestSceneWindow(assets);
        updateInputProcessor();
        
        textureGen = new TextureGenerator(assets, def);
        
    }

	private void updateInputProcessor() {
		inputPlexer.clear();
		inputPlexer.addProcessor(ui.stage());
		if(mainScene != null && subScene == null) {
			inputPlexer.addProcessor(new GestureDetector(mainScene.gestureListener()));
		}
		if(subScene != null) {
			inputPlexer.addProcessor(new GestureDetector(subScene.gestureListener()));
		}
	}
	
	@Override
	public void dispose() {
		GameSerialiser serialiser = new GameSerialiser(def,assets,effects2D);
		serialiser.save(logic.scene());
		
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
        if(mainScene != null) {
        	mainScene.draw();
        }
        
        effects2D.stage().draw();
        ui.stage().draw();
        
        if(subScene != null) {
        	subScene.draw();
        }
	}
	
	private void update() {
		float delta = Gdx.graphics.getDeltaTime();
		
		if(mainScene != null) {
			mainScene.update(delta);
		}
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
		if(mainScene != null) {
			mainScene.resized(width, height);
		}
		if(subScene != null) {
			subScene.resized(width, height);
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

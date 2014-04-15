package com.skorulis.drack.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.skorulis.drack.IsoPerspectiveCamera;
import com.skorulis.drack.building.Building;
import com.skorulis.drack.def.DefManager;
import com.skorulis.drack.game.GameScene;
import com.skorulis.drack.ui.building.BuildingUI;

public class UIManager {

	private Stage stage;
	private StyleManager style;
	private DebugUI debugUI;
	private PlayerUI playerUI;
	private GameScene game;
	private DefManager def;
	private AssetManager assets;
	private IsoPerspectiveCamera camera;
	private BuildingUI buildingUI;
	
	public UIManager(AssetManager assets,GameScene game,DefManager def, IsoPerspectiveCamera camera) {
		this.assets = assets;
		this.game = game;
		this.def = def;
		this.camera = camera;
		stage = new Stage(new ScreenViewport());
		style = new StyleManager();  
		debugUI = new DebugUI(style);
		playerUI = new PlayerUI(this);

		stage.addActor(playerUI);
		
		stage.addActor(debugUI);
		resized(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	public void update(float delta) {
		stage.act(delta);
	}
	
	public Stage stage() {
		return stage;
	}
	
	public void resized(int width, int height) {
		stage.getViewport().update(width, height, true);
	}
	
	public AssetManager assets() {
		return assets;
	}
	
	public GameScene game() {
		return game;
	}
	
	public DefManager def() {
		return def;
	}
	
	public StyleManager style() {
		return style;
	}
	
	public IsoPerspectiveCamera camera() {
		return camera;
	}
	
	public void showBuildingUI(Building building) {
		clearBuildingUI();
		buildingUI = BuildingUI.uiForBuilding(building);
		buildingUI.init(style.defaultSkin(), this);
		buildingUI.setBuilding(building);
		
		stage.addActor(buildingUI);
	}
	
	public void clearBuildingUI() {
		if(buildingUI != null) {
			buildingUI.getParent().removeActor(buildingUI);
		}
		buildingUI = null;
	}
	
}

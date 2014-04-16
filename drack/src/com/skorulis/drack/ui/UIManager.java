package com.skorulis.drack.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.skorulis.drack.IsoPerspectiveCamera;
import com.skorulis.drack.building.Building;
import com.skorulis.drack.def.DefManager;
import com.skorulis.drack.game.GameScene;
import com.skorulis.drack.ui.building.BuildingUI;
import com.skorulis.gdx.SKAssetManager;

public class UIManager {

	private final Stage stage;
	private final StyleManager style;
	private DebugUI debugUI;
	private PlayerUI playerUI;
	private GameScene game;
	private DefManager def;
	private SKAssetManager assets;
	private IsoPerspectiveCamera camera;
	private BuildingUI buildingUI;
	
	public UIManager(SKAssetManager assets,GameScene game,DefManager def, IsoPerspectiveCamera camera, StyleManager style) {
		this.assets = assets;
		this.game = game;
		this.def = def;
		this.camera = camera;
		
		this.style = new StyleManager();
		stage = new Stage(new ScreenViewport());
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
	
	public SKAssetManager assets() {
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
		buildingUI = building.def().createUI();
		if(buildingUI != null) {
			buildingUI.init(style.gameSkin(), this);
			buildingUI.setBuilding(building);
			stage.addActor(buildingUI);
		}
		
	}
	
	public void clearBuildingUI() {
		if(buildingUI != null) {
			buildingUI.getParent().removeActor(buildingUI);
		}
		buildingUI = null;
	}
	
}

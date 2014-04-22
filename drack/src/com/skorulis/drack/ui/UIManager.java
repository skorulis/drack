package com.skorulis.drack.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.skorulis.drack.building.Building;
import com.skorulis.drack.def.DefManager;
import com.skorulis.drack.game.GameDelegate;
import com.skorulis.drack.game.GameLogic;
import com.skorulis.drack.game.GameScene;
import com.skorulis.drack.game.IsoPerspectiveCamera;
import com.skorulis.drack.ui.building.BuildingUI;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.gdx.ui.LayoutHelper;

public class UIManager extends WidgetGroup {

	private final Stage stage;
	private final StyleManager style;
	private DebugUI debugUI;
	private PlayerUI playerUI;
	private GameLogic logic;
	private DefManager def;
	private SKAssetManager assets;
	private BuildingUI buildingUI;
	private GameDelegate delegate;
	private LayoutHelper helper;
	
	public UIManager(SKAssetManager assets,GameLogic logic,DefManager def, StyleManager style, GameDelegate delegate) {
		this.assets = assets;
		this.logic = logic;
		this.def = def;
		this.delegate = delegate;
		helper = new LayoutHelper(this);
		
		this.style = new StyleManager();
		stage = new Stage(new ScreenViewport());
		debugUI = new DebugUI(style);
		playerUI = new PlayerUI(this);
		
		stage.addActor(this);
		
		this.addActor(playerUI);
		this.addActor(debugUI);
		
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
		this.setWidth(width);
		this.setHeight(height - 40);
	}
	
	public SKAssetManager assets() {
		return assets;
	}
	
	public GameScene game() {
		return logic.scene();
	}
	
	public GameLogic logic() {
		return logic;
	}
	
	public DefManager def() {
		return def;
	}
	
	public StyleManager style() {
		return style;
	}
	
	public IsoPerspectiveCamera camera() {
		return logic.isoCam();
	}
	
	public void showBuildingUI(Building building) {
		clearBuildingUI();
		buildingUI = building.def().createUI();
		if(buildingUI != null) {
			buildingUI.init(style.gameSkin(), this);
			buildingUI.setBuilding(building);
			this.addActor(buildingUI);
		}
		
	}
	
	public void clearBuildingUI() {
		if(buildingUI != null) {
			buildingUI.getParent().removeActor(buildingUI);
		}
		buildingUI = null;
	}
	
	public GameDelegate delegate() {
		return delegate;
	}
	
	public void layout() {
		helper.alignRight(playerUI, 10);
		helper.alignBottom(playerUI, 10);
	}
	
	public void showDialog(ModalDialog dialog) {
		this.addActor(dialog);
	}
	
	
}

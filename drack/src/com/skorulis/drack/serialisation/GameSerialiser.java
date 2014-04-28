package com.skorulis.drack.serialisation;

import java.io.Writer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;
import com.skorulis.drack.def.DefManager;
import com.skorulis.drack.effects.Effect2DLayer;
import com.skorulis.drack.game.GameScene;
import com.skorulis.drack.map.GameMap;
import com.skorulis.gdx.SKAssetManager;

public class GameSerialiser {

	private static final String SAVE_FILE = "data/savefile.json";
	
	private final DefManager def;
	private final SKAssetManager assets;
	private final Effect2DLayer effects2D;
	
	public GameSerialiser(DefManager def, SKAssetManager assets, Effect2DLayer effects2D) {
		this.def = def;
		this.assets = assets;
		this.effects2D = effects2D;
	}
	
	public void save(GameScene scene) {
		try {
			FileHandle handle = Gdx.files.local(SAVE_FILE);
			Writer writer =  handle.writer(false);
			
			Json json = new Json(OutputType.json);
			String s = json.toJson(scene.getSerialisation());
			writer.write(s);
			
			writer.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public GameScene read() {
		try {
			FileHandle handle = Gdx.files.local(SAVE_FILE);
			if(!handle.exists()) {
				return null;
			}
			Json json = new Json();
			GameSceneJson sceneJson = json.fromJson(GameSceneJson.class, handle);
			
			return createScene(sceneJson);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public GameScene createScene(GameSceneJson json) {
		GameMap map = createMap(json.map);
		return new GameScene(def, assets, map, effects2D);
	}
	
	public GameMap createMap(MapJson json) {
		GameMap map = new GameMap(json.width, json.depth, assets);
		
		return map;
	}
	
}

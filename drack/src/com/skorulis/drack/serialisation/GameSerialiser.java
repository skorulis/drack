package com.skorulis.drack.serialisation;

import java.io.Writer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;
import com.skorulis.drack.def.DefManager;
import com.skorulis.drack.def.unit.UnitDef;
import com.skorulis.drack.effects.Effect2DLayer;
import com.skorulis.drack.game.GameScene;
import com.skorulis.drack.map.GameMap;
import com.skorulis.drack.map.MapGenerator;
import com.skorulis.drack.player.Player;
import com.skorulis.drack.player.PlayerContainer;
import com.skorulis.drack.serialisation.unit.UnitJson;
import com.skorulis.drack.unit.Unit;
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
		PlayerContainer players = new PlayerContainer(json.players,def,effects2D);
		
		GameMap map = createMap(json.map, players);
		
		GameScene scene = new GameScene(def, assets, map, effects2D, players);
		
		LoadData ld = new LoadData();
		ld.assets = assets;
		ld.def = def;
		ld.map = map;
		
		for(UnitJson uj : json.units) {
			UnitDef ud = def.getUnit(uj.defName);
			Player p = scene.players().findPlayer(uj.playerId);
			Unit u = ud.create(assets, p);
			if(uj.controlled) {
				p.setControllingUnit(u);
			}
			u.load(uj, ld);
			scene.addUnit(u, uj.x, uj.z);
		}
		
		return scene;
	}
	
	public GameMap createMap(MapJson json, PlayerContainer players) {
		MapGenerator mapGen = new MapGenerator(json.width, json.depth, assets, def, players);
		
		mapGen.loadMap(json);
		
		return mapGen.map();
	}
	
}

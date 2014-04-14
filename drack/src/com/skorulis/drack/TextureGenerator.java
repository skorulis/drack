package com.skorulis.drack;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.skorulis.drack.building.Building;
import com.skorulis.drack.def.BuildingDef;
import com.skorulis.drack.def.DefManager;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;

public class TextureGenerator {

	private IsoPerspectiveCamera isoCam;
	private ModelBatch modelBatch;
	private static int width = 100;
	private static int height = 100;
	private ArrayList<BuildingDef> buildings;
	private SKAssetManager assets;
	
	public TextureGenerator(SKAssetManager assets,DefManager def) {
		this.assets = assets;
		isoCam = new IsoPerspectiveCamera(width,height,5);
		modelBatch = new ModelBatch();
		
		buildings = def.buildableBuildings();
		System.out.println("Rendering " + buildings.size() + " buildings");
	}
	
	public void render(Environment environment) {
		BuildingDef bd = buildings.remove(0);
		Building building = bd.create(assets);
		
		TransparentFrameBuffer frameBuffer = new TransparentFrameBuffer(Format.RGB888,width,height,true);
        frameBuffer.begin();
		
		Gdx.gl.glViewport(0, 0, width, height);
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        isoCam.cam().update();
        RenderInfo ri = new RenderInfo(modelBatch, environment, isoCam.cam());
        
        modelBatch.begin(isoCam.cam());
        building.render(ri);
        modelBatch.end();
        frameBuffer.end();
        
        Texture t = frameBuffer.getColorBufferTexture();
        assets.addAsset(bd.name() + "_icon", Texture.class, t);
	}
	
	public boolean finished() {
		return buildings.size() == 0;
	}
}

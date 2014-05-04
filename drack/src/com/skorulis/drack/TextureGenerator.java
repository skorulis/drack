package com.skorulis.drack;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.actor.attachments.HullAttachment;
import com.skorulis.drack.actor.building.Building;
import com.skorulis.drack.def.DefManager;
import com.skorulis.drack.def.attachment.HullAttachmentDef;
import com.skorulis.drack.def.attachment.HardPointDef;
import com.skorulis.drack.def.attachment.HardPointDef.HullPointType;
import com.skorulis.drack.def.building.BuildingDef;
import com.skorulis.drack.game.IsoPerspectiveCamera;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;

public class TextureGenerator {

	private IsoPerspectiveCamera isoCam;
	private ModelBatch modelBatch;
	private static int width = 96;
	private static int height = 96;
	private ArrayList<BuildingDef> buildings;
	private ArrayList<HullAttachmentDef> attachments;
	private SKAssetManager assets;
	
	public TextureGenerator(SKAssetManager assets,DefManager def) {
		this.assets = assets;
		isoCam = new IsoPerspectiveCamera(width,height,5);
		modelBatch = new ModelBatch();
		
		buildings = def.buildableBuildings();
		attachments = new ArrayList<HullAttachmentDef>(def.allAttachments());
	}
	
	public void render(Environment environment) {
		FrameBuffer frameBuffer = new FrameBuffer(Format.RGBA8888,width,height,true);
        frameBuffer.begin();
		
		Gdx.gl.glViewport(0, 0, width, height);
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        isoCam.cam().update();
        RenderInfo ri = new RenderInfo(modelBatch, environment, isoCam.cam());
        
        modelBatch.begin(isoCam.cam());
        
        String textureName = null;
        if(buildings.size() > 0) {
        	textureName = renderNextBuilding(ri);
        } else if(attachments.size() > 0) {
        	textureName = renderNextAttachment(ri); 
        }
        
        modelBatch.end();
        frameBuffer.end();
        
        Texture t = frameBuffer.getColorBufferTexture();
        assets.addAsset(textureName, Texture.class, t);
	}
	
	private String renderNextBuilding(RenderInfo ri) {
		BuildingDef bd = buildings.remove(0);
		renderBuilding(bd, ri);
		return bd.iconName();
	}
	
	private String renderNextAttachment(RenderInfo ri) {
		HullAttachmentDef def = attachments.remove(0);
		renderAttachment(def, ri);
		return def.iconName();
	}
	
	private void renderBuilding(BuildingDef def, RenderInfo ri) {
		Building building = def.create(assets);
		building.absTransform().translate(0, -1, 0);
        building.render(ri);
	}
	
	private void renderAttachment(HullAttachmentDef def, RenderInfo ri) {
		HardPointDef hpd = new HardPointDef(new Vector3(2,2,2), 0, HullPointType.SMALL,0);
		HullAttachment att = def.create(assets, hpd);
		att.absTransform().setTranslation(2.5f, 3.5f, 2.5f);
		att.render(ri);
	}
	
	public boolean finished() {
		return buildings.size() == 0 && attachments.size() == 0;
	}
}

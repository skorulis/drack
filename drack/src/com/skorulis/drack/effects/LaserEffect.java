package com.skorulis.drack.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.MeshBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;

public class LaserEffect implements Disposable {

	private Vector3 start;
	private Vector3 end;
	private Model model;
	private ModelInstance instance;
	
	public LaserEffect(SKAssetManager assets, Vector3 start, Vector3 end) {
		this.start = start;
		this.end = end;
		
		buildGeometry(assets);
	}
	
	private void buildGeometry(SKAssetManager assets) {
		
		Texture texture = assets.getTexture("laser.png");
		MeshBuilder meshBuilder = new MeshBuilder();
		meshBuilder.begin(VertexAttributes.Usage.Position, GL20.GL_TRIANGLES);
		
		Vector3 v1 = new Vector3(start.x,start.y+0.5f,start.z);
		Vector3 v2 = new Vector3(start.x,start.y-0.5f,start.z);
		Vector3 v3 = new Vector3(end.x,end.y-0.5f,end.z);
		Vector3 v4 = new Vector3(end.x,end.y+0.5f,end.z);
		
		meshBuilder.rect(v1, v2, v3, v4, new Vector3(0,0,1));
		
		BlendingAttribute ba = new BlendingAttribute();
		ba.destFunction = GL20.GL_ONE;
		
		ColorAttribute ca = ColorAttribute.createDiffuse(0.5f, 1, 0.5f, 1.0f);
		
		Material material = new Material(TextureAttribute.createDiffuse(texture), ca, ba);
		
		Mesh mesh = meshBuilder.end();
		
		
		ModelBuilder mb = new ModelBuilder();
		mb.begin();
		mb.part("mid", mesh, GL20.GL_TRIANGLES,material);
		
		
		model = mb.end();
		instance = new ModelInstance(model);
	}
	
	public void render(RenderInfo ri) {
		//ri.batch.getRenderContext().setBlending(true, GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		ri.batch.render(instance, ri.environment);
		
	}
	
	public void dispose() {
		
	}
	
}

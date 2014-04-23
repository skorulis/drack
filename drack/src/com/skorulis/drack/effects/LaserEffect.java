package com.skorulis.drack.effects;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.graphics.g3d.utils.MeshBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;

public class LaserEffect implements Disposable {

	private Vector3 start;
	private Vector3 end;
	private Model model;
	private ModelInstance instance;
	private Mesh mesh;
	
	public LaserEffect(SKAssetManager assets, Vector3 start, Vector3 end) {
		this.start = start;
		this.end = end;
		
		buildGeometry3(assets);
	}
	
	private void buildGeometry2(SKAssetManager assets) {
		Texture texture = assets.getTexture("laser.png");
		
		BlendingAttribute ba = new BlendingAttribute();
		Material material = new Material(TextureAttribute.createDiffuse(texture), ba );
		
		ModelBuilder builder = new ModelBuilder();
		model = builder.createBox(1, 1, 1, material, Usage.Position | Usage.Normal | Usage.TextureCoordinates);

		instance = new ModelInstance(model);
		instance.transform.rotate(Vector3.X, 45);
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
		
		Material material = new Material(TextureAttribute.createDiffuse(texture), ca);
		
		Mesh mesh = meshBuilder.end();
		
		
		ModelBuilder mb = new ModelBuilder();
		mb.begin();
		mb.part("mid", mesh, GL20.GL_TRIANGLES,material);
		
		
		model = mb.end();
		instance = new ModelInstance(model);
	}
	
	private void buildGeometry3(SKAssetManager assets) {
		float[] verts = new float[20];
		int i = 0;
		verts[i++] = start.x; // x1
		verts[i++] = start.y-1; // y1
		verts[i++] = start.z;
		verts[i++] = 0f; // u1
		verts[i++] = 0f; // v1

		verts[i++] = end.x; // x2
		verts[i++] = end.y-1; // y2
		verts[i++] = end.z;
		verts[i++] = 1f; // u2
		verts[i++] = 0f; // v2

		verts[i++] = end.x; // x3
		verts[i++] = end.y+1f; // y2
		verts[i++] = end.z;
		verts[i++] = 1f; // u3
		verts[i++] = 1f; // v3

		verts[i++] = start.x; // x4
		verts[i++] = start.y+1; // y4
		verts[i++] = start.z;
		verts[i++] = 0f; // u4
		verts[i++] = 1f; // v4
		
		VertexAttribute posAtt = new VertexAttribute( Usage.Position, 3, ShaderProgram.POSITION_ATTRIBUTE );
		VertexAttribute texAtt = new VertexAttribute( Usage.TextureCoordinates, 2, ShaderProgram.TEXCOORD_ATTRIBUTE+"0" ); 
		
		mesh = new Mesh( true, 4, 0, posAtt, texAtt);
		mesh.setVertices(verts);
		
		Texture texture = assets.getTexture("laser_mid.png");
		Material material = new Material(TextureAttribute.createDiffuse(texture),new BlendingAttribute() );
		
		MeshPart part = new MeshPart("middle", mesh, 0, 4, GL20.GL_TRIANGLE_FAN);
		
		ModelBuilder builder = new ModelBuilder();
		builder.begin();
		builder.part(part, material);
		model = builder.end();
		instance = new ModelInstance(model);
	}
	
	public void render(RenderInfo ri) {
		//ri.batch.getRenderContext().setBlending(true, GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		ri.batch.render(instance, ri.environment);
		//mesh.render(shader, primitiveType);
		
	}
	
	public void dispose() {
		
	}
	
	public static Set<String> textures() {
		HashSet<String> ret = new HashSet<String>();
		ret.add("laser_start.png");
		ret.add("laser_mid.png");
		ret.add("laser_end.png");
		
		return ret;
	}
	
}

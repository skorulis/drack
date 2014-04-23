package com.skorulis.drack.effects;

import java.util.HashSet;
import java.util.Set;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.UpdateInfo;

public class LaserEffect implements Disposable {

	private Vector3 startPos;
	private Vector3 endPos;
	private Vector3 dir;
	private Vector3 spriteAxis;
	private Vector3 forward;
	private Model model;
	private ModelInstance instance;
	
	public LaserEffect(SKAssetManager assets, Vector3 start, Vector3 end) {
		this.startPos = start;
		this.endPos = end;
		dir = end.cpy().sub(start).nor();
		forward = new Vector3(0,0,1);
		spriteAxis = forward.cpy().crs(dir).nor();
		
		System.out.println("AXIS " + spriteAxis);
		
		buildGeometry(assets);
	}
	
	/*private void buildGeometry2(SKAssetManager assets) {
		Texture texture = assets.getTexture("laser.png");
		
		BlendingAttribute ba = new BlendingAttribute();
		Material material = new Material(TextureAttribute.createDiffuse(texture), ba );
		
		ModelBuilder builder = new ModelBuilder();
		model = builder.createBox(1, 1, 1, material, Usage.Position | Usage.Normal | Usage.TextureCoordinates);

		instance = new ModelInstance(model);
		instance.transform.rotate(Vector3.X, 45);
	}*/
	
	private void buildGeometry(SKAssetManager assets) {
		
		Mesh midMesh = createQuad(startPos.cpy().add(dir), endPos.cpy().sub(dir));
		Mesh startMesh = createQuad(startPos, startPos.cpy().add(dir));
		Mesh endMesh = createQuad(endPos.cpy().sub(dir), endPos);
		
		Texture texture1 = assets.getTexture("laser_mid.png");
		Texture texture2 = assets.getTexture("laser_start.png");
		Texture texture3 = assets.getTexture("laser_end.png");
		Material material1 = new Material(TextureAttribute.createDiffuse(texture1),new BlendingAttribute() );
		Material material2 = new Material(TextureAttribute.createDiffuse(texture2),new BlendingAttribute() );
		Material material3 = new Material(TextureAttribute.createDiffuse(texture3),new BlendingAttribute() );
		
		MeshPart part1 = new MeshPart("middle", midMesh, 0, 4, GL20.GL_TRIANGLE_FAN);
		MeshPart part2 = new MeshPart("start", startMesh, 0, 4, GL20.GL_TRIANGLE_FAN);
		MeshPart part3 = new MeshPart("end", endMesh, 0, 4, GL20.GL_TRIANGLE_FAN);
		
		ModelBuilder builder = new ModelBuilder();
		builder.begin();
		builder.part(part1, material1);
		builder.part(part2, material2);
		builder.part(part3, material3);
		model = builder.end();
		instance = new ModelInstance(model);
	}
	
	public Mesh createQuad(Vector3 start, Vector3 end) {
		float[] verts = new float[20];

		Vector3 axis = spriteAxis.cpy().scl(0.5f);
		
		
		int i = 0;
		verts[i++] = start.x - axis.x; // x1
		verts[i++] = start.y - axis.y; // y1
		verts[i++] = start.z - axis.z;
		verts[i++] = 0f; // u1
		verts[i++] = 0f; // v1

		verts[i++] = end.x - axis.x; // x2
		verts[i++] = end.y - axis.y; // y2
		verts[i++] = end.z - axis.z;
		verts[i++] = 1f; // u2
		verts[i++] = 0f; // v2

		verts[i++] = end.x + axis.x; // x3
		verts[i++] = end.y + axis.y; // y2
		verts[i++] = end.z + axis.z;
		verts[i++] = 1f; // u3
		verts[i++] = 1f; // v3

		verts[i++] = start.x + axis.x; // x4
		verts[i++] = start.y + axis.y; // y4
		verts[i++] = start.z + axis.z;
		verts[i++] = 0f; // u4
		verts[i++] = 1f; // v4
		
		VertexAttribute posAtt = new VertexAttribute( Usage.Position, 3, ShaderProgram.POSITION_ATTRIBUTE );
		VertexAttribute texAtt = new VertexAttribute( Usage.TextureCoordinates, 2, ShaderProgram.TEXCOORD_ATTRIBUTE+"0" ); 
		
		Mesh mesh = new Mesh( true, 4, 0, posAtt, texAtt);
		mesh.setVertices(verts);
		
		return mesh;
	}
	
	public void render(RenderInfo ri) {
		//ri.batch.getRenderContext().setBlending(true, GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		ri.batch.render(instance, ri.environment);
		//mesh.render(shader, primitiveType);
		
	}
	
	public void update(UpdateInfo ui) {
		Vector3 dir = ui.cam.direction.cpy();
		
		//instance.transform.setToLookAt(dir, spriteAxis);
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

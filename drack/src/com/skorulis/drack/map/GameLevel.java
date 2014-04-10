package com.skorulis.drack.map;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.skorulis.scene.SceneNode;

public class GameLevel implements SceneNode, Disposable{

	public Matrix4 transform;
	public Model model;
	public Model wallModel;
	public ModelInstance modelInstance;
	
	public ArrayList<ModelInstance> walls;
	
	private int width,depth;
	
	
	public GameLevel(int width, int depth) {
		this.width = width;
		this.depth = depth;
		
		transform = new Matrix4();
		
		model = createFloor(width, depth);
		modelInstance = new ModelInstance(model);
		
		Material material = new Material();
		Texture texture = new Texture(Gdx.files.internal("data/square.png"));
		material.set(new TextureAttribute(TextureAttribute.Diffuse, texture));
		
		ModelBuilder builder = new ModelBuilder();
		wallModel = builder.createBox(1, 1.5f, 1, material, Usage.Position | Usage.Normal | Usage.TextureCoordinates);
		
		walls = new ArrayList<ModelInstance>();
		for(int i = -1; i < width + 1; ++i) {
			ModelInstance wallInstance = new ModelInstance(wallModel);
			wallInstance.transform.setToTranslation(new Vector3(i + 0.5f, 0.75f, -0.5f));
			walls.add(wallInstance);
			
			wallInstance = new ModelInstance(wallModel);
			wallInstance.transform.setToTranslation(new Vector3(i + 0.5f, 0.75f, depth + 0.5f));
			walls.add(wallInstance);
		}
		
		for(int i = 0; i < depth; ++i) {
			ModelInstance wallInstance = new ModelInstance(wallModel);
			wallInstance.transform.setToTranslation(new Vector3(-0.5f, 0.75f, i + 0.5f));
			walls.add(wallInstance);
			
			wallInstance = new ModelInstance(wallModel);
			wallInstance.transform.setToTranslation(new Vector3(width + 0.5f, 0.75f, i + 0.5f));
			walls.add(wallInstance);
		}
		
	}
	
	public Model createFloor(int w, int d) {
		final Mesh mesh = new Mesh(true, 4, 6, new VertexAttribute(
                Usage.Position, 3, "a_position"), new VertexAttribute(
                Usage.TextureCoordinates, 2, "a_texCoords")); 
        mesh.setVertices(new float[]
                { 0, 0f, 0, 0, 0,
                0, 0f, d, 0, d,
                w, 0f, d , w,d,
                w, 0f, 0, w, 0
                });
        mesh.setIndices(new short[] { 0, 1, 2, 2, 3, 0 });
        
        Material material = new Material();
		Texture texture = new Texture(Gdx.files.internal("data/square.png"));
		texture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		material.set(new TextureAttribute(TextureAttribute.Diffuse, texture));
        
        Model model = ModelBuilder.createFromMesh(mesh, GL20.GL_TRIANGLES , material);
        model.manageDisposable(texture);
        
        return model;
	}
	
	@Override
	public Matrix4 absTransform() {
		return transform;
	}

	@Override
	public Matrix4 relTransform() {
		return transform;
	}

	@Override
	public void render(ModelBatch batch, Environment environment) {
		batch.render(modelInstance,environment);
		batch.render(walls,environment);
	}

	@Override
	public void dispose() {
		model.dispose();
		wallModel.dispose();
	}

	public Vector3 center() {
		return new Vector3(this.width/2.0f, 0, this.depth/2.0f);
	}
	
}

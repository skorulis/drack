package com.skorulis.drack.building;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class Tree extends Building {
	
	public Tree(AssetManager assets) {
		super(assets);
		buildingInstance = new ModelInstance(assets.get("data/tree1.g3db",Model.class));
	}
	
}

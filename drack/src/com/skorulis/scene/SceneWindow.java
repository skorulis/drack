package com.skorulis.scene;

import com.badlogic.gdx.input.GestureDetector.GestureListener;

public interface SceneWindow {

	public void draw();
	public void update(float delta);
	public void resized(int width, int height);
	public GestureListener gestureListener();
	
}

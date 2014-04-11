package com.skorulis.drack;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "drack";
		cfg.width = 640;
		cfg.height = 480;
		
		new LwjglApplication(new DrackGame(), cfg);
	}
}

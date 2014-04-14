package com.skorulis.drack;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Blending;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;

public class TransparentFrameBuffer extends FrameBuffer{

	public TransparentFrameBuffer(Format format, int width, int height,
			boolean hasDepth) {
		super(format, width, height, hasDepth);
	}
	
	protected void setupTexture () {
		Pixmap.setBlending(Blending.None);
		Pixmap pixmap = new Pixmap(width, height, format);
		
		
		//pixmap.setColor(0, 1, 0, 0.2f);
		//pixmap.fillRectangle(0, 0, 30, 30);
		colorTexture = new Texture(pixmap);
		colorTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		colorTexture.setWrap(TextureWrap.ClampToEdge, TextureWrap.ClampToEdge);
	}

}

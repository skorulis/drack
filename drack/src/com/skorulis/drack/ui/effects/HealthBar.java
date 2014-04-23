package com.skorulis.drack.ui.effects;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

public class HealthBar extends WidgetGroup {

	private Image innerImage;
	private Image bgImage;
	private float pct;
	
	public HealthBar(Skin skin) {
		innerImage = new Image(skin.getDrawable("green_sq"));
		bgImage = new Image(skin.getDrawable("health_outer"));
		
		addActor(innerImage);
		addActor(bgImage);
		
		this.bgImage.setFillParent(true);
		
		this.setWidth(64);
		this.setHeight(12);		
		
		pct = 1;
	}
	
	public void layout() {
		this.bgImage.setX(-this.getWidth()/2);
		this.innerImage.setX(-this.getWidth()/2);
		this.bgImage.setY(24);
		this.innerImage.setY(24);
		this.innerImage.setHeight(this.getHeight());
		this.innerImage.setWidth(this.getWidth() * pct);
	}
	
	public void setPct(float pct) {
		this.pct = pct;
		this.innerImage.setWidth(this.getWidth() * pct);
	}
	
	
}

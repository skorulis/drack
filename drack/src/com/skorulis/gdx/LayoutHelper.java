package com.skorulis.gdx;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;

public class LayoutHelper {

	private WidgetGroup widget;
	
	public LayoutHelper(WidgetGroup widget) {
		this.widget = widget;
	}
	
	public void centreChild(Actor actor) {
		Layout layout = (Layout) actor;
		float w = layout.getPrefWidth();
		float h = layout.getPrefHeight();
		actor.setBounds( (widget.getWidth() - w)/2 , (widget.getHeight() - h)/2, w, h);
	}
	
}

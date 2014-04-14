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
		centreChild(actor, 0);
	}
	
	public void centreChild(Actor actor, float padding) {
		Layout layout = (Layout) actor;
		float w = layout.getPrefWidth();
		float h = layout.getPrefHeight();
		
		w = Math.min(w, widget.getWidth() - padding * 2);
		h = Math.min(h, widget.getHeight() - padding * 2);
		
		actor.setBounds( (widget.getWidth() - w)/2 , (widget.getHeight() - h)/2, w, h);
	}
	
}

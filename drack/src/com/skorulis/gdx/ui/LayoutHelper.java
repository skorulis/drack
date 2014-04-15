package com.skorulis.gdx.ui;

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
	
	public void centreChildX(Actor actor) {
		Layout layout = (Layout) actor;
		float w = layout.getPrefWidth();
		float h = layout.getPrefHeight();
		
		actor.setBounds( (widget.getWidth() - w)/2 , actor.getY(), w, h);
	}
	
	public void alignRight(Actor actor, float padding) {
		float w = actor.getWidth();
		actor.setX(widget.getWidth() - w - padding);
	}
	
	public void alignBottom(Actor actor, float padding) {
		actor.setY(padding);
	}
	
	public void alignTopOf(Actor actor, Actor to, float padding) {
		float y = to.getTop();
		actor.setY(y + padding);
	}
	
	
}

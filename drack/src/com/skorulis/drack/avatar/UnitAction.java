package com.skorulis.drack.avatar;

public abstract class UnitAction {

	protected Avatar avatar;
	
	public UnitAction(Avatar avatar) {
		this.avatar = avatar;
	}
	
	public abstract void update(float delta);
	
}

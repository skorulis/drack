package com.skorulis.drack.unit;

public abstract class UnitAction {

	protected Unit avatar;
	
	public UnitAction(Unit avatar) {
		this.avatar = avatar;
	}
	
	public abstract void update(float delta);
	public abstract boolean finished();
	
}

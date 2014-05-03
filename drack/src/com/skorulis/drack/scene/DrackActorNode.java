package com.skorulis.drack.scene;

import java.util.Set;

import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.player.Player;
import com.skorulis.drack.resource.ResourceBatch;
import com.skorulis.drack.unit.composite.Weapon;
import com.skorulis.scene.SceneNode;

public interface DrackActorNode extends SceneNode {

	public Set<Weapon> allWeapons();
	public Player owner();
	public ResourceBatch resources();
	public Vector3 currentPosition();
	public void addResources(ResourceBatch batch);
	
}

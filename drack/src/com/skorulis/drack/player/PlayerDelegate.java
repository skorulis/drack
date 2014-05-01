package com.skorulis.drack.player;

import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.resource.ResourceBatch;

public interface PlayerDelegate {

	public void resourcesAdded(Player player, Vector3 loc, ResourceBatch resources);
	
}

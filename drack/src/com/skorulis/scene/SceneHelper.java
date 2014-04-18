package com.skorulis.scene;

import com.badlogic.gdx.math.Vector3;

public class SceneHelper {

	public static float dist(SceneNode node, Vector3 loc) {
		Vector3 nodeLoc = node.absTransform().getTranslation(new Vector3());
		return nodeLoc.sub(loc).len();
	}
	
}

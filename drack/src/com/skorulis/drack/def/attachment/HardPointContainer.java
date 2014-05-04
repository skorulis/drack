package com.skorulis.drack.def.attachment;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.def.attachment.HardPointDef.HullPointType;

public class HardPointContainer {

	public List<HardPointDef> points;
	
	public HardPointContainer() {
		this.points = new ArrayList<HardPointDef>();
	}
	
	public void addHardPoint(HardPointDef point) {
		points.add(point);
	}
	
	public void addHardPoint(Vector3 loc, float rotation, HullPointType type) {
		HardPointDef hardPoint = new HardPointDef(loc, rotation, type, this.points.size());
		this.points.add(hardPoint);
	}
	
	public int size() {
		return points.size();
	}
	
	public HardPointDef getHardPoint(int index) {
		return points.get(index);
	}
	
}

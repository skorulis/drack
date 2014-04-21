package com.skorulis.drack.unit.composite;

import java.util.HashSet;
import java.util.Set;

import com.skorulis.drack.def.unit.CompositeUnitDef;
import com.skorulis.drack.player.Player;
import com.skorulis.drack.unit.Unit;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;

public class CompositeUnit extends Unit {

	public Set<HullAttachment> attachments;
	
	public CompositeUnit(SKAssetManager assets, Player owner, CompositeUnitDef def) {
		super(assets, owner, def);
		this.attachments = new HashSet<HullAttachment>();
	}
	
	public CompositeUnitDef compDef() {
		return (CompositeUnitDef) this.def;
	}
	
	public void render(RenderInfo ri) {
		super.render(ri);
		for(HullAttachment att: attachments) {
			att.render(ri);
		}
	}
	
	public void addAttachment(HullAttachment att) {
		attachments.add(att);
	}
	


}
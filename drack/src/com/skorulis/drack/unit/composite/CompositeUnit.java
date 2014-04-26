package com.skorulis.drack.unit.composite;

import java.util.HashSet;
import java.util.Set;

import com.skorulis.drack.def.attachment.HullPointDef;
import com.skorulis.drack.def.unit.CompositeUnitDef;
import com.skorulis.drack.player.Player;
import com.skorulis.drack.unit.Unit;
import com.skorulis.gdx.SKAssetManager;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.UpdateInfo;

public class CompositeUnit extends Unit {

	public Set<HullAttachment> attachments;
	
	public CompositeUnit() {
		this.attachments = new HashSet<HullAttachment>();
	}
	
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
		HullAttachment old = attachmentAt(att.hardPoint());
		if(old != null) {
			attachments.remove(old);
		}
		attachments.add(att);
	}
	
	public HullAttachment attachmentAt(HullPointDef hpd) {
		for(HullAttachment att : attachments) {
			if(att.hardPoint() == hpd) {
				return att;
			}
		}
		return null;
	}
	
	public void update(UpdateInfo ui) {
		super.update(ui);
		for(HullAttachment att: attachments) {
			att.updatePosition(this);
		}
	}
	
	public HullPointDef emptyPoint() {
		for(HullPointDef hpd: compDef().hull.points) {
			if(attachmentAt(hpd) == null) {
				return hpd;
			}
		}
		return null;
	}
	
	public Set<Weapon> allWeapons() {
		HashSet<Weapon> all = new HashSet<Weapon>();
		for(HullAttachment att : attachments) {
			if(att instanceof Weapon) {
				all.add((Weapon)att);
			}
		}
		return all;
	}


}

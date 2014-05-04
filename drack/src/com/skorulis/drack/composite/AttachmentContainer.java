package com.skorulis.drack.composite;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.skorulis.drack.actor.attachments.HullAttachment;
import com.skorulis.drack.actor.attachments.Weapon;
import com.skorulis.drack.def.attachment.HardPointDef;
import com.skorulis.drack.def.attachment.HullAttachmentDef;
import com.skorulis.drack.serialisation.AttachmentJson;
import com.skorulis.drack.serialisation.LoadData;
import com.skorulis.scene.RenderInfo;
import com.skorulis.scene.SceneNode;
import com.skorulis.scene.UpdateInfo;

public class AttachmentContainer {

	private Set<HardPointDef> hardPoints;
	private final Set<HullAttachment> attachments;
	private final SceneNode node;
	
	public AttachmentContainer(SceneNode node) {
		this.node = node;
		this.attachments = new HashSet<HullAttachment>();
	}
	
	public void load(ArrayList<AttachmentJson> attachments, LoadData ld) {
		for(AttachmentJson aj : attachments) {
			HullAttachmentDef attDef = ld.def.getAttachment(aj.defName);
			HardPointDef hardPoint = getHardPoint(aj.hardPoint);
			HullAttachment att = attDef.create(ld.assets, hardPoint);
			addAttachment(att);
		}
	}
	
	
	public void setHardPoints(Set<HardPointDef> hardPoints) {
		this.hardPoints = hardPoints;
	}
	
	public HardPointDef getHardPoint(int number) {
		for(HardPointDef hpd : this.hardPoints) {
			if(hpd.number == number) {
				return hpd;
			}
		}
		return null;
	}
	
	public void render(RenderInfo ri) {
		for(HullAttachment att: attachments) {
			att.render(ri);
		}
	}
	
	public void update(UpdateInfo ui) {
		for(HullAttachment att: attachments) {
			att.updatePosition(node);
			att.update(ui);
		}
	}
	
	public void addAttachment(HullAttachment att) {
		HullAttachment old = attachmentAt(att.hardPoint());
		if(old != null) {
			attachments.remove(old);
		}
		attachments.add(att);
	}
	
	public HullAttachment attachmentAt(HardPointDef hpd) {
		for(HullAttachment att : attachments) {
			if(att.hardPoint() == hpd) {
				return att;
			}
		}
		return null;
	}
	
	public HardPointDef emptyPoint() {
		for(HardPointDef hpd: hardPoints) {
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
	
	public Set<HardPointDef> hardPoints() {
		return hardPoints;
	}
	
	public ArrayList<AttachmentJson> getSerialisation() {
		ArrayList<AttachmentJson> ret = new ArrayList<AttachmentJson>();
		for(HullAttachment att : attachments) {
			ret.add(att.getSerialisation());
		}
		return ret;
	}
	
}

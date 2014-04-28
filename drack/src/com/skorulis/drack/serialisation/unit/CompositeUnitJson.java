package com.skorulis.drack.serialisation.unit;

import java.util.ArrayList;

import com.skorulis.drack.serialisation.AttachmentJson;

public class CompositeUnitJson extends UnitJson {

	public ArrayList<AttachmentJson> attachments;
	
	public CompositeUnitJson() {
		attachments = new ArrayList<AttachmentJson>();
	}
	
}

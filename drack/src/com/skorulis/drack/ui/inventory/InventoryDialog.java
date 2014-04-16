package com.skorulis.drack.ui.inventory;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.skorulis.drack.player.Player;
import com.skorulis.drack.resource.ResourceQuantity;
import com.skorulis.drack.ui.ModalDialog;

public class InventoryDialog extends ModalDialog {

	private ScrollPane resourceScrollPane;
	private VerticalGroup resourceList;
	private ArrayList<ResourceQuantity> resources;
	
	public InventoryDialog(Player player, Skin skin) {
		super(skin);
		resourceList = new VerticalGroup();
		resourceScrollPane = new ScrollPane(resourceList);
		this.setContent(resourceScrollPane);
		
		resources = player.resources().allResources();
		System.out.println(" CREATING");
		for(ResourceQuantity rq : resources) {
			System.out.println(" CREATED " + rq);
			ResourceCell cell = new ResourceCell(rq, skin);
			this.resourceList.addActor(cell);
		}
	}

}

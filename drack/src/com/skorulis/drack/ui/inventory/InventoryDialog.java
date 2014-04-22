package com.skorulis.drack.ui.inventory;

import java.util.ArrayList;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.skorulis.drack.player.Player;
import com.skorulis.drack.resource.ResourceQuantity;
import com.skorulis.drack.ui.ModalDialog;
import com.skorulis.drack.ui.UIManager;

public class InventoryDialog extends ModalDialog {

	private ScrollPane resourceScrollPane;
	private VerticalGroup resourceList;
	private ArrayList<ResourceQuantity> resources;
	
	public InventoryDialog(Player player, UIManager ui) {
		super(ui);
		resourceList = new VerticalGroup();
		resourceScrollPane = new ScrollPane(resourceList);
		this.setContent(resourceScrollPane);
		
		resources = player.resources().allResources();
		for(ResourceQuantity rq : resources) {
			ResourceCell cell = new ResourceCell(rq, ui.style().gameSkin());
			this.resourceList.addActor(cell);
		}
	}

}

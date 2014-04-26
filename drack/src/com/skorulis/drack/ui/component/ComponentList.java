package com.skorulis.drack.ui.component;

import java.util.Collection;
import java.util.Iterator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.skorulis.drack.def.attachment.HullAttachmentDef;
import com.skorulis.drack.ui.UIManager;
import com.skorulis.gdx.ui.LayoutHelper;

public class ComponentList extends WidgetGroup {
	
	private ScrollPane scroll;
	private HorizontalGroup group;
	private Image image;
	private LayoutHelper helper;
	private ComponentListDelegate delegate;
	
	public ComponentList(UIManager ui,ComponentListDelegate delegate) {
		this.delegate = delegate;
		image = new Image(ui.style().gameSkin().getDrawable("off_white_bg"));
		
		this.addActor(image);
		image.setFillParent(true);
		
		helper = new LayoutHelper(this);
		
		group = new HorizontalGroup();
		group.space(10);
		scroll = new ScrollPane(group);
		scroll.setScrollingDisabled(false, true);
		addActor(scroll);
		
		Collection<HullAttachmentDef> attachments = ui.def().allAttachments();
		Iterator<HullAttachmentDef> it = attachments.iterator();
		while(it.hasNext()) {
			final HullAttachmentDef had = it.next();
			Texture texture = ui.assets().get(had.iconName(), Texture.class);
			
			ImageButton button = new ImageButton(ui.style().createImageStyle(texture,true));
			group.addActor(button);
			button.addListener(new ClickListener() {
				public void clicked(InputEvent event, float x, float y) {
					ComponentList.this.delegate.componentSelected(had);
				}
			});
			
		}
	}
	
	public void layout() {
		helper.centreFill(scroll, 4);
	}

}

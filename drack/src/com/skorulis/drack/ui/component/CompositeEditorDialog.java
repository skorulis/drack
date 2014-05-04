package com.skorulis.drack.ui.component;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.skorulis.drack.attachments.HullAttachment;
import com.skorulis.drack.composite.CompositeObject;
import com.skorulis.drack.composite.editor.HardPointNode;
import com.skorulis.drack.composite.editor.CompositeEditor;
import com.skorulis.drack.def.attachment.HullAttachmentDef;
import com.skorulis.drack.ui.ModalDialog;
import com.skorulis.drack.ui.UIManager;
import com.skorulis.gdx.ui.LayoutHelper;

public class CompositeEditorDialog extends ModalDialog implements ComponentListDelegate {

	private WidgetGroup mainContent;
	private CompositeEditor editor;
	private ComponentList list;
	private LayoutHelper contentHelper;
	
	public CompositeEditorDialog(UIManager ui, CompositeObject unit) {
		super(ui);
		
		mainContent = new WidgetGroup();
		this.setContent(mainContent);
		
		contentHelper = new LayoutHelper(mainContent);
		
		editor = new CompositeEditor(ui.assets(), ui.def(),unit);
		ui.delegate().setSubScene(editor);
		
		list = new ComponentList(ui, this);
		list.setHeight(116);
		this.mainContent.addActor(list);
	}
	
	public void act(float delta) {
		super.act(delta);
	}
	
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
	}
	
	public void layout() {
		this.layoutHelper.centreFill(mainContent, 40);
		contentHelper.centreFillX(list, 8);
		
		super.layout();
	}
	
	public CompositeEditor editor() {
		return editor;
	}
	
	public void willClose() {
		editor.returnUnitToMap();
		ui.delegate().setSubScene(null);
	}

	@Override
	public void componentSelected(HullAttachmentDef def) {
		HardPointNode selected = editor.selectedNode();
		if(selected != null) {
			HullAttachment att = def.create(editor.assets(), selected.def());
			editor.object().attachmentContainer().addAttachment(att);
		}
	}

}

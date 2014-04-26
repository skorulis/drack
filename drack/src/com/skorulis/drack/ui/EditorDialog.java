package com.skorulis.drack.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.skorulis.drack.ui.component.ComponentList;
import com.skorulis.drack.unit.composite.CompositeUnit;
import com.skorulis.drack.unit.editor.UnitEditor;
import com.skorulis.gdx.ui.LayoutHelper;

public class EditorDialog extends ModalDialog {

	private WidgetGroup mainContent;
	private UnitEditor editor;
	private ComponentList list;
	private LayoutHelper contentHelper;
	
	public EditorDialog(UIManager ui, CompositeUnit unit) {
		super(ui);
		
		mainContent = new WidgetGroup();
		this.setContent(mainContent);
		
		contentHelper = new LayoutHelper(mainContent);
		
		editor = new UnitEditor(ui.assets(), ui.def(),unit);
		ui.delegate().setSubScene(editor);
		
		list = new ComponentList(ui);
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
	
	public UnitEditor editor() {
		return editor;
	}
	
	public void willClose() {
		editor.returnUnitToMap();
		ui.delegate().setSubScene(null);
	}

}

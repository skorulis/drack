package com.skorulis.drack.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.skorulis.drack.unit.editor.UnitEditor;

public class EditorDialog extends ModalDialog {

	private WidgetGroup mainContent;
	private UnitEditor editor;
	
	
	public EditorDialog(UIManager ui) {
		super(ui);
		
		mainContent = new WidgetGroup();
		this.setContent(mainContent);
		
		editor = new UnitEditor(ui.assets(), ui.def());
		ui.delegate().setSubScene(editor);
	}
	
	public void act(float delta) {
		super.act(delta);
	}
	
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
	}
	
	public void layout() {
		this.layoutHelper.centreFill(mainContent, 40);
		super.layout();
	}
	
	public UnitEditor editor() {
		return editor;
	}
	
	public void willClose() {
		ui.delegate().setSubScene(null);
	}

}

package com.skorulis.drack.ui.building;

import java.util.Set;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.skorulis.drack.def.unit.UnitDef;
import com.skorulis.drack.map.MapSquare;
import com.skorulis.drack.ui.UIManager;
import com.skorulis.drack.unit.Unit;

public class BarracksUI extends BuildingUI {

	private TextButton hireButton;
	
	public void init(Skin skin, UIManager ui) {
		super.init(skin,ui);
		hireButton = new TextButton("hire", skin);
		
		hireButton.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				hirePressed();
			}
		});
		
		buttonGroup.addActor(hireButton);
		layout();
	}
	
	private void hirePressed() {
		UnitDef def = this.ui.def().getUnit("truck");
		Unit u = def.create(ui.assets(), ui.logic().player());

		Set<MapSquare> squares = this.ui.game().map().squaresAround(this.building);
		
		this.ui.game().addUnit(u, squares.iterator().next());
	}
	
	public void layout() {
		super.layout();
	}
	
}

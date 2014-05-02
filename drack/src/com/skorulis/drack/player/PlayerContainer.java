package com.skorulis.drack.player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.badlogic.gdx.math.Vector3;
import com.skorulis.drack.def.DefManager;
import com.skorulis.drack.effects.Effect2DLayer;
import com.skorulis.drack.resource.ResourceBatch;
import com.skorulis.drack.resource.ResourceQuantity;
import com.skorulis.drack.serialisation.PlayerJson;
import com.skorulis.scene.UpdateInfo;

public class PlayerContainer implements PlayerDelegate {

	private Player humanPlayer;
	private Set<Player> players;
	private Effect2DLayer effects;
	
	public PlayerContainer(Effect2DLayer effects) {
		players = new HashSet<Player>();
		humanPlayer = new Player("human");
		players.add(humanPlayer);
		this.effects = effects;
	}
	
	public PlayerContainer(ArrayList<PlayerJson> json, DefManager def, Effect2DLayer effects) {
		players = new HashSet<Player>();
		this.effects = effects;
		for(PlayerJson pj : json) {
			Player p = new Player(pj,def);
			addPlayer(p);
			if(p.playerId().equals("human")) {
				humanPlayer = p;
			}
		}
	}
	
	public void addPlayer(Player p) {
		players.add(p);
		p.setDelegate(this);
	}
	
	public Player findPlayer(String id) {
		for(Player p : players) {
			if(p.playerId().equals(id)) {
				return p;
			}
		}
		return null;
	}
	
	public void update(UpdateInfo info) {
		Iterator<Player> playerIt = players.iterator();
		while(playerIt.hasNext()) {
			playerIt.next().update(info);
		}
	}
	
	public ArrayList<PlayerJson> getSerialisation() {
		ArrayList<PlayerJson> ret = new ArrayList<PlayerJson>();
		
		for(Player p : players) {
			ret.add(p.getSerialisation());
		}
		
		return ret;
	}
	
	public Player humanPlayer() {
		return humanPlayer;
	}

	@Override
	public void resourcesAdded(Player player, Vector3 loc,
			ResourceBatch resources) {
		
		ArrayList<ResourceQuantity> all = resources.allResources();
		for(int i = 0; i < all.size(); ++i) {
			effects.addTextEffect(loc, all.get(i).displayText(),i);
		}
		
		System.out.println("taking " + resources.count());
	}
	
}

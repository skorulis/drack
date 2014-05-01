package com.skorulis.drack.player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.skorulis.drack.def.DefManager;
import com.skorulis.drack.serialisation.PlayerJson;
import com.skorulis.scene.UpdateInfo;

public class PlayerContainer {

	private Player humanPlayer;
	private Set<Player> players;
	
	public PlayerContainer() {
		players = new HashSet<Player>();
		humanPlayer = new Player("human");
		players.add(humanPlayer);
	}
	
	public PlayerContainer(ArrayList<PlayerJson> json, DefManager def) {
		players = new HashSet<Player>();
		for(PlayerJson pj : json) {
			Player p = new Player(pj,def);
			players.add(p);
			if(p.playerId().equals("human")) {
				humanPlayer = p;
			}
		}
	}
	
	public void addPlayer(Player p) {
		players.add(p);
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
	
}

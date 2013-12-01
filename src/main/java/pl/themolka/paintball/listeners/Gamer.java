package pl.themolka.paintball.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import pl.themolka.paintball.Paintball;
import pl.themolka.paintball.api.TeamType;
import pl.themolka.paintball.game.Teams;

public class Gamer implements Listener {
	
	Paintball plugin;
	Teams teams;
	
	public Gamer(Paintball plugin) {
		this.plugin = plugin;
		this.teams = new Teams(plugin);
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if(!Teams.getBluePlayers.contains(e.getPlayer().getName()) || !Teams.getRedPlayers.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if(!Teams.getBluePlayers.contains(e.getPlayer().getName()) || !Teams.getRedPlayers.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		if(teams.isTeam(e.getPlayer(), TeamType.BLUE)) {
			//e.setRespawnLocation(MapYAML.getBlueSpawnLocation());
			Teams.join(e.getPlayer(), TeamType.BLUE);
		}
		if(teams.isTeam(e.getPlayer(), TeamType.RED)) {
			//e.setRespawnLocation(MapYAML.getRedSpawnLocation());
			Teams.join(e.getPlayer(), TeamType.RED);
		}
	}
	
}

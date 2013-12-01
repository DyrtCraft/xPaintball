package pl.themolka.paintball.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.kitteh.tag.PlayerReceiveNameTagEvent;

import pl.themolka.paintball.Paintball;
import pl.themolka.paintball.game.Teams;

public class Tag implements Listener {
	
	Paintball plugin;
	
	public Tag(Paintball plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerReceiveNameTag(PlayerReceiveNameTagEvent e) {
		if(Teams.getBluePlayers.contains(e.getPlayer().getName())) { // Blue team
			e.setTag(ChatColor.BLUE + e.getPlayer().getName() + ChatColor.RESET);
		}
		if(Teams.getRedPlayers.contains(e.getPlayer().getName())) { // Red team
			e.setTag(ChatColor.RED + e.getPlayer().getName() + ChatColor.RESET);
		} else { // Observators team
			e.setTag(ChatColor.AQUA + e.getPlayer().getName() + ChatColor.RESET);
		}
	}
	
}

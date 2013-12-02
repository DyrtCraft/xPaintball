package pl.themolka.paintball.tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import pl.themolka.paintball.Paintball;
import pl.themolka.paintball.api.PbPlugin;
import pl.themolka.paintball.game.Teams;

public class StartTask implements Runnable {
	
	Paintball plugin;
	Teams teams;
	int timer = 31;
	
	public StartTask(Paintball plugin) {
		this.plugin = plugin;
		Bukkit.getScheduler().runTaskTimer(plugin, this, 1200L, 100L);
	}

	@Override
	public void run() {
		if(!(timer >= 30)) {
			timer = timer - 10;
		}
		
		if(!(Teams.getBluePlayers.size() > 0) && !(Teams.getRedPlayers.size() > 0)) {
			Bukkit.broadcastMessage(ChatColor.RED + "There are no players in another team. Waiting for more players...");
			return;
		}
		
		Bukkit.broadcastMessage(ChatColor.GREEN + "Match starting in " + ChatColor.DARK_RED + timer + ChatColor.GREEN + " seconds...");
		if(isMinus()) {
			Bukkit.getScheduler().cancelAllTasks();
			PbPlugin.getMatch().start();
		}
	}
	
	public boolean isMinus() {
		if(timer < 0) {
			return true;
		} else {
			return false;
		}
	}
	
}

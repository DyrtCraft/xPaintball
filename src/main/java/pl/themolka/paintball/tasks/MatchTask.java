package pl.themolka.paintball.tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import pl.themolka.paintball.Paintball;
import pl.themolka.paintball.PbPlugin;

public class MatchTask implements Runnable {
	
	Paintball plugin;
	int timer = 5;
	
	public MatchTask(Paintball plugin) {
		this.plugin = plugin;
		Bukkit.getScheduler().runTaskTimer(plugin, this, 6000L, 1200L);
	}
	
	@Override
	public void run() {
		timer --;
		
		Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "Time remaining " + ChatColor.GOLD + timer + ChatColor.DARK_PURPLE + ".");
		
		if(isMinus()) {
			Bukkit.getScheduler().cancelAllTasks();
			PbPlugin.getMatch().end();
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

package pl.themolka.paintball.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import pl.themolka.paintball.Paintball;
import pl.themolka.paintball.PbPlugin;
import pl.themolka.paintball.api.Map;
import pl.themolka.paintball.api.TeamType;
import pl.themolka.paintball.game.Teams;
import pl.themolka.paintball.inventories.TeamChooserInventory;

public class Player implements Listener {
	
	Paintball plugin;
	Teams teams;
	
	public Player(Paintball plugin) {
		this.plugin = plugin;
		this.teams = new Teams(plugin);
	}
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent e) {
		e.setLeaveMessage(null);
		
		Teams.getBluePlayers.remove(e.getPlayer().getName());
		Teams.getRedPlayers.remove(e.getPlayer().getName());
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Map map = PbPlugin.getMap(e.getPlayer().getLocation().getWorld().getName());
		
		e.setJoinMessage(null);
		
		teams.setTeam(e.getPlayer(), TeamType.OBSERVATOR);
		e.getPlayer().setPlayerListName(ChatColor.AQUA + e.getPlayer().getName() + ChatColor.RESET);
		
		/*for(org.bukkit.entity.Player player : Bukkit.getOnlinePlayers()) {
			if(!Teams.getBluePlayers.contains(e.getPlayer().getName()) && !Teams.getRedPlayers.contains(e.getPlayer().getName())) {
				player.hidePlayer(e.getPlayer());
			}
		}*/
		
		try {
			e.getPlayer().teleport(map.getSpawn(TeamType.OBSERVATOR));
		} catch(Exception ex) {
			e.getPlayer().sendMessage(ChatColor.RED + "This spawn point (OBSERVATOR) is not set yet!");
		}
		
		if(PbPlugin.getTeams().isJoinable()) {
			e.getPlayer().sendMessage(PbPlugin.getMatch().getCurrentMapInfo());
			e.getPlayer().openInventory(TeamChooserInventory.inv);
		}
		if(!PbPlugin.getTeams().isJoinable()) {
			e.getPlayer().sendMessage(ChatColor.DARK_PURPLE + "The match has ended, but DON'T QUIT! " + ChatColor.DARK_GREEN + "Next map will be started in one moment!");
		}
		
		e.getPlayer().getInventory().setItem(0, PbPlugin.getNavigateItems().compass());
		e.getPlayer().getInventory().setItem(1, PbPlugin.getNavigateItems().teamChooser());
		
		if(PbPlugin.getMatch().isVote()) {
			e.getPlayer().getInventory().setItem(2, PbPlugin.getNavigateItems().mapVoter());
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		
		Teams.getBluePlayers.remove(e.getPlayer().getName());
		Teams.getRedPlayers.remove(e.getPlayer().getName());
	}
	
}

package pl.themolka.paintball.game;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import pl.themolka.paintball.Paintball;
import pl.themolka.paintball.api.PbPlugin;
import pl.themolka.paintball.api.game.Map;
import pl.themolka.paintball.api.game.TeamType;

public class MapManager implements pl.themolka.paintball.api.game.MapManager {
	
	private Paintball plugin;
	
	public MapManager(Paintball plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public ArrayList<String> getMaps() {
		ArrayList<String> allMaps = new ArrayList<String>();
		List<String> maps =  plugin.getConfig().getStringList("map-list");
		
		for(String map : maps) {
			allMaps.add(map);
		}
		return allMaps;
	}
	
	@Deprecated
	@Override
	public void load(Map map) {
		if(!Bukkit.getWorlds().contains(map.getName())) {
			Bukkit.getLogger().log(Level.WARNING, ChatColor.RED + "World with name \"" + map.getName() + "\" was not found!");
			return;
		}
		/*
		 * TODO Load
		 */
	}
	
	@Override
	public void teleport(Player player, Map map) {
		if(!Bukkit.getWorlds().contains(map.getName())) {
			Bukkit.getLogger().log(Level.WARNING, ChatColor.RED + "World with name \"" + map.getName() + "\" was not found!");
			player.sendMessage(ChatColor.RED + "World with name \"" + map.getName() + "\" was not found! You're teleportation has been cancelled!");
			return;
		}
		player.teleport(PbPlugin.getMap(map.getName()).getSpawn(TeamType.OBSERVER));
	}
	
	@Deprecated
	@Override
	public void unLoad(Map map) {
		if(!Bukkit.getWorlds().contains(map.getName())) {
			Bukkit.getLogger().log(Level.WARNING, ChatColor.RED + "World with name \"" + map.getName() + "\" was not found!");
			return;
		}
		/*
		 * TODO Unload
		 */
	}
	
}

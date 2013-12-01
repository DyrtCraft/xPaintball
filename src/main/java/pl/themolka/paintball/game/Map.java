package pl.themolka.paintball.game;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import pl.themolka.paintball.Paintball;
import pl.themolka.paintball.api.TeamType;

public class Map implements pl.themolka.paintball.api.Map {
	
	Paintball plugin;
	private String authors;
	private String description;
	private int maxPlayers;
	private String name;
	private String version;
	
	private Location spawnBlue;
	private Location spawnObs;
	private Location spawnRed;
	
	public Map(Paintball plugin, String mapName) {
		this.plugin = plugin;
		//authors = plugin.getConfig().getString("maps." + mapName + ".authors");
		//description = plugin.getConfig().getString("maps." + mapName + ".description");
		//maxPlayers = plugin.getConfig().getInt("maps." + mapName + ".max-players");
		//name = mapName;
		//version = plugin.getConfig().getString("maps." + mapName + ".version");
		
		authors = plugin.getConfig().getString("maps.Default_Map.authors");
		description = plugin.getConfig().getString("maps.Default_Map.description");
		maxPlayers = plugin.getConfig().getInt("maps.Default_Map.max-players");
		name = mapName;
		version = plugin.getConfig().getString("maps.Default_Map.version");
		
		// Locations
		spawnBlue = getSpawn(mapName, "blue");
		spawnObs = getSpawn(mapName, "observator");
		spawnRed = getSpawn(mapName, "observator");
	}
	
	private Location getSpawn(String mapName, String team) {
		//int x = plugin.getConfig().getInt("maps." + mapName + "." + team + ".spawn-point.x");
		//int y = plugin.getConfig().getInt("maps." + mapName + "." + team + ".spawn-point.y");
		//int z = plugin.getConfig().getInt("maps." + mapName + "." + team + ".spawn-point.z");
		//return new Location(Bukkit.getWorld(mapName), x, y, z);
		
		int x = plugin.getConfig().getInt("maps.Default_Map." + team + ".spawn-point.x");
		int y = plugin.getConfig().getInt("maps.Default_Map." + team + ".spawn-point.y");
		int z = plugin.getConfig().getInt("maps.Default_Map." + team + ".spawn-point.z");
		return new Location(Bukkit.getWorld(mapName), x, y, z);
	}
	
	@Override
	public String getAuthors() {
		return authors;
	}
	
	@Override
	public String getDescription() {
		return description;
	}
	
	@Override
	public int getMaxPlayers() {
		return maxPlayers;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public Location getSpawn(TeamType teamType) {
		if(teamType == TeamType.BLUE) {
			return spawnBlue;
		}
		if(teamType == TeamType.OBSERVATOR) {
			return spawnObs;
		}
		if(teamType == TeamType.RED) {
			return spawnRed;
		} else {
			return null;
		}
	}
	
	@Override
	public String getVersion() {
		return version;
	}
	
	@Override
	public String toString() {
		return "Map{mapName=" + name + ",mapAuthors=" + authors + ",mapDescription=" + description + ",mapMaxPlayers=" + maxPlayers + ",mapVersion=" + version + "}";
	}
	
}

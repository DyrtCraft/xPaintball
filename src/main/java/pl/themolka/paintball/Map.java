package pl.themolka.paintball;

import org.bukkit.Location;

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
		authors = "TheMolkaPL"; // TODO From YAML file
		description = "Testing your description!"; // TODO From YAML file
		maxPlayers = 25; // TODO From YAML file
		name = mapName;
		version = "1.0"; // TODO From YAML file
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

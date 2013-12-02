package pl.themolka.paintball.api.game;

import org.bukkit.Location;

public interface Map {
	
	public String getAuthors();
	
	public String getDescription();
	
	public int getMaxPlayers();
	
	public String getName();
	
	public Location getSpawn(TeamType teamType);
	
	public String getVersion();
	
}

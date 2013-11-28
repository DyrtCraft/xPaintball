package pl.themolka.paintball.api;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public interface MapManager {
	
	public Map getCurrentMap();
	
	public ArrayList<String> getMaps();
	
	public void load(Map map);
	
	public void teleport(Player player, Map map);
	
	public void setCurrentMap(Map currentMap);
	
	public void unLoad(Map map);
	
}

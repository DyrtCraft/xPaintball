package pl.themolka.paintball.api;

import org.bukkit.entity.Player;

public interface BungeeConnector {
	
	/**
	 * Connect a player to another server on BungeeCord
	 * @param player Player was moved
	 * @param server Name of server to move
	 */
	public void connect(Player player, String server);
	
	/**
	 * Get name of lobby server on BungeeCord
	 * @return server name
	 */
	public String getHubServerName();
	
	/**
	 * Check if BungeeCord is set to true in config.yml file
	 * @return true if is enabled
	 */
	public boolean isBungeeCordEnabled();
	
	/**
	 * Kick all players on server to lobby
	 */
	public void kickAllToHub();
	
}

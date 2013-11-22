package pl.themolka.paintball;

import pl.themolka.paintball.api.Help;
import pl.themolka.paintball.api.Teams;

public final class PbPlugin {
	
	private static String mapName = null;
	
	private static Paintball plugin = new Paintball();
	private static BungeeConnector bungeeConnector = new BungeeConnector(plugin);
	private static Help help = new pl.themolka.paintball.Help();
	private static Items items = new pl.themolka.paintball.Items();
	private static Map map = new pl.themolka.paintball.Map(plugin, mapName);
	private static Match match = new pl.themolka.paintball.Match(plugin);
	private static Teams teams = new pl.themolka.paintball.Teams(plugin);
	
	/**
	 * BungeeCord
	 * @return BungeeCord
	 */
	public static BungeeConnector getBungeeConnector() {
		return bungeeConnector;
	}
	
	/**
	 * Get currently playing map
	 * @return Map
	 */
	public static Map getCurrentMap() {
		mapName = ""; // TODO
		return map;
	}
	
	/**
	 * Paintball's Help Center
	 * @return Help Center
	 */
	public static Help getHelp() {
		return help;
	}
	
	public static Items getItems() {
		return items;
	}
	
	public static Map getMap(String mapName) {
		PbPlugin.mapName = mapName;
		return map;
	}
	
	public static Match getMatch() {
		return match;
	}
	
	public static Teams getTeams() {
		return teams;
	}
	
}

package pl.themolka.paintball;

import pl.themolka.paintball.api.Help;
import pl.themolka.paintball.api.NavigateItems;
import pl.themolka.paintball.api.Teams;

public final class PbPlugin {
	
	private static String mapName = null;
	
	private static Paintball plugin = new Paintball();
	private static BungeeConnector bungeeConnector = new BungeeConnector(plugin);
	private static String currnetMap;
	private static Help help = new pl.themolka.paintball.Help();
	private static Items items = new pl.themolka.paintball.Items();
	private static Map map = new pl.themolka.paintball.Map(plugin, mapName);
	private static Match match = new pl.themolka.paintball.Match(plugin);
	private static NavigateItems navigateItems = new pl.themolka.paintball.NavigateItems();
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
		return PbPlugin.map = PbPlugin.getMap(PbPlugin.currnetMap);
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
	
	public static NavigateItems getNavigateItems() {
		return navigateItems;
	}
	
	public static Teams getTeams() {
		return teams;
	}
	
	public static void setCurrentMap(String currentMap) {
		PbPlugin.currnetMap = currentMap;
	}
	
}

package pl.themolka.paintball.api;

import pl.themolka.paintball.Paintball;
import pl.themolka.paintball.api.game.Teams;
import pl.themolka.paintball.game.Items;
import pl.themolka.paintball.game.Map;
import pl.themolka.paintball.game.MapManager;
import pl.themolka.paintball.game.Match;
import pl.themolka.paintball.utils.BungeeConnector;
import pl.themolka.paintball.utils.PaintballPluginsManager;

public final class PbPlugin {
	
	private static String mapName = null;
	
	private static Paintball plugin = new Paintball();
	private static BungeeConnector bungeeConnector = new BungeeConnector(plugin);
	private static Map currnetMap = null;
	private static Help help = new pl.themolka.paintball.help.HelpCenter();
	private static Items items = new pl.themolka.paintball.game.Items();
	private static Map map = new pl.themolka.paintball.game.Map(plugin, mapName);
	private static MapManager mapManager = new pl.themolka.paintball.game.MapManager(plugin);
	private static Match match = new pl.themolka.paintball.game.Match(plugin);
	private static NavigateItems navigateItems = new pl.themolka.paintball.NavigateItems();
	private static PaintballPluginsManager pluginManager = new pl.themolka.paintball.utils.PaintballPluginsManager();
	private static Teams teams = new pl.themolka.paintball.game.Teams(plugin);
	
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
		return currnetMap;
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
	
	public static MapManager getMapManager() {
		return mapManager;
	}
	
	public static Match getMatch() {
		return match;
	}
	
	public static NavigateItems getNavigateItems() {
		return navigateItems;
	}
	
	public static PaintballPluginsManager getPluginsManager() {
		return pluginManager;
	}
	
	public static Teams getTeams() {
		return teams;
	}
	
	public static void setCurrentMap(Map currentMap) {
		PbPlugin.currnetMap = currentMap;
	}
	
}

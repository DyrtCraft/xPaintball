package pl.themolka.paintball;

import org.bukkit.plugin.java.JavaPlugin;

import pl.themolka.paintball.commands.JoinCommand;
import pl.themolka.paintball.commands.MaplistCommand;
import pl.themolka.paintball.commands.MyteamCommand;
import pl.themolka.paintball.commands.PaintballCommand;
import pl.themolka.paintball.commands.VoteCommand;
import pl.themolka.paintball.inventories.TeamChooserInventory;
import pl.themolka.paintball.inventories.VoteInventory;
import pl.themolka.paintball.listeners.Gamer;
import pl.themolka.paintball.listeners.Observator;
import pl.themolka.paintball.listeners.Player;
import pl.themolka.paintball.listeners.PlayerMove;
import pl.themolka.paintball.listeners.Tag;
import pl.themolka.paintball.listeners.World;

public class Paintball extends JavaPlugin {

	private static Paintball paintball = null;
	
	@Override
	public void onEnable() {
		Long loadTime = System.currentTimeMillis();
		getLogger().info("Loading Paintball plugin v" + getDescription().getVersion() + " by " + getDescription().getAuthors() + "...");
		
		paintball = this;
		saveDefaultConfig();
		
		registerCommands();
		registerListeners();
		
		if(!(getServer().getPluginManager().getPlugin("PaintballChat") == null)) {
			getLogger().info("PaintballChat plugin was found!");
			PbPlugin.getPluginsManager().setPaintballChat(true);
		}
		
		Long finLoadTime = System.currentTimeMillis() - loadTime;
		getLogger().info("Paintball v" + getDescription().getVersion() + " by " + getDescription().getAuthors() + " has been loaded! (" + finLoadTime + " ms)");
		
		PbPlugin.getTeams().setJoinable(true);
		PbPlugin.getTeams().setRunning(false);
	}
	
	@Override
	public void onDisable() {
		PbPlugin.getMatch().end(false);
		PbPlugin.getBungeeConnector().kickAllToHub();
		saveConfig();
	}
	
	public void registerCommands() {
		getCommand("join").setExecutor(new JoinCommand(this));
		getCommand("maplist").setExecutor(new MaplistCommand(this));
		getCommand("myteam").setExecutor(new MyteamCommand(this));
		getCommand("paintball").setExecutor(new PaintballCommand(this));
		getCommand("vote").setExecutor(new VoteCommand(this));
	}
	
	public void registerListeners() {
		getLogger().info("Loading listners...");
		getServer().getPluginManager().registerEvents(new Gamer(this), this);
		getServer().getPluginManager().registerEvents(new Observator(this), this);
		getServer().getPluginManager().registerEvents(new Player(this), this);
		getServer().getPluginManager().registerEvents(new PlayerMove(this), this);
		getServer().getPluginManager().registerEvents(new Tag(this), this);
		getServer().getPluginManager().registerEvents(new World(), this);
		
		getServer().getPluginManager().registerEvents(new TeamChooserInventory(this), this);
		getServer().getPluginManager().registerEvents(new VoteInventory(this), this);
		getLogger().info("All listeners has been loaded!");
	}
	
	public static Paintball getInstance() {
		return paintball;
	}
	
	public static void setInstance(Paintball paintball) {
		Paintball.paintball = paintball;
	}
	
}

package pl.themolka.paintball;

import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.kitteh.tag.TagAPI;
import org.mcstats.Metrics;

import pl.themolka.paintball.api.PbPlugin;
import pl.themolka.paintball.commands.JoinCommand;
import pl.themolka.paintball.commands.MaplistCommand;
import pl.themolka.paintball.commands.MyteamCommand;
import pl.themolka.paintball.commands.PaintballCommand;
import pl.themolka.paintball.commands.VoteCommand;
import pl.themolka.paintball.game.Map;
import pl.themolka.paintball.inventories.TeamChooserInventory;
import pl.themolka.paintball.inventories.VoteInventory;
import pl.themolka.paintball.listeners.Damage;
import pl.themolka.paintball.listeners.Gamer;
import pl.themolka.paintball.listeners.ItemUsage;
import pl.themolka.paintball.listeners.Observer;
import pl.themolka.paintball.listeners.Player;
import pl.themolka.paintball.listeners.PlayerMove;
import pl.themolka.paintball.listeners.Tag;
import pl.themolka.paintball.listeners.World;

public class Paintball extends JavaPlugin {

	private static Paintball paintball = null;
	
	public void onLoad() {
		if(!(getServer().getPluginManager().getPlugin("xPaintballChat") == null)) {
			getLogger().info("Chat plugin (xPaintballChat) was found!");
			PbPlugin.getPluginsManager().setPaintballChat(true);
		}
	}
	
	@Override
	public void onEnable() {
		Long loadTime = System.currentTimeMillis();
		getLogger().info("Loading xPaintball plugin v" + getDescription().getVersion() + " by " + getDescription().getAuthors() + "...");
		
		paintball = this;
		
		saveDefaultConfig();
		
		new Map(this, "Hardcore");
		PbPlugin.setCurrentMap(PbPlugin.getMap("Hardcore"));
		
		registerCommands();
		registerListeners();
		
		Long finLoadTime = System.currentTimeMillis() - loadTime;
		getLogger().info("xPaintball v" + getDescription().getVersion() + " by " + getDescription().getAuthors() + " has been loaded! (" + finLoadTime + " ms)");
		
		PbPlugin.getTeams().setJoinable(true);
		PbPlugin.getTeams().setRunning(false);
		
		for(org.bukkit.entity.Player player : Bukkit.getOnlinePlayers()) {
			TagAPI.refreshPlayer(player);
		}
		
		// Metrics start
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch(IOException ex) {
			getLogger().log(Level.WARNING, "[MCStats] Failed to submit the stats :-(");
		}
		// Metrics end
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
		getServer().getPluginManager().registerEvents(new Damage(this), this);
		getServer().getPluginManager().registerEvents(new Gamer(this), this);
		getServer().getPluginManager().registerEvents(new ItemUsage(this), this);
		getServer().getPluginManager().registerEvents(new Observer(this), this);
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

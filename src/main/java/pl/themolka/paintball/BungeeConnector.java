package pl.themolka.paintball;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class BungeeConnector implements pl.themolka.paintball.api.BungeeConnector {
	
	Paintball plugin;
	pl.themolka.paintball.api.BungeeConnector bungee;
	
	public BungeeConnector(Paintball plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public void connect(Player player, String server) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("Connect");
			out.writeUTF(server);
		} catch(IOException ex) {}
		player.sendMessage(ChatColor.GOLD + "You have been moved to Lobby server by reload or restart.");
		player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
	}
	
	@Override
	public String getHubServerName() {
		try {
			return plugin.getConfig().getString("bungee-cord.hub-server");
		} catch(Exception ex) {
			return null;
		}
	}
	
	@Override
	public boolean isBungeeCordEnabled() {
		if(plugin.getConfig().getBoolean("bungee-cord.enable", true)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void kickAllToHub() {
		for(Player player : Bukkit.getOnlinePlayers()) {
			if(player.isOnline()) {
				connect(player, getHubServerName());
			}
		}
	}
	
}

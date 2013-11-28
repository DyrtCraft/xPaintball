package pl.themolka.paintball.commands.pbcmd;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import pl.themolka.paintball.PbPlugin;

public class Maplist {
	
	public static void execute(CommandSender sender, Command command, String label, String[] args) {
		ArrayList<String> map = PbPlugin.getMapManager().getMaps();
		
		StringBuilder stringBuilder = new StringBuilder();
		for(String maps : map) {
			maps = maps.replace("_", " ");
			stringBuilder.append(ChatColor.DARK_PURPLE + "- " + ChatColor.GOLD + maps + ChatColor.DARK_PURPLE + " version " + ChatColor.GOLD + PbPlugin.getMap(maps).getVersion() + ChatColor.DARK_PURPLE + " by " + ChatColor.GOLD + PbPlugin.getMap(maps).getAuthors() + ChatColor.DARK_PURPLE + ", " + ChatColor.GOLD + PbPlugin.getMap(maps).getMaxPlayers() + ChatColor.DARK_PURPLE + " players;\n");
		}
		
		sender.sendMessage(ChatColor.GOLD + " ========== Map rotate ========== ");
		sender.sendMessage(stringBuilder.toString());
	}
	
}

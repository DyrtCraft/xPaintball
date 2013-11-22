package pl.themolka.paintball.commands.pbcmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import pl.themolka.paintball.Paintball;

public class About {
	
	public static boolean about(CommandSender sender) {
		sender.sendMessage(ChatColor.GOLD + " ===== Paintball by TheMolkaPL ===== ");
		sender.sendMessage(ChatColor.GOLD + "Version: " + ChatColor.GRAY + Paintball.getInstance().getDescription().getVersion());
		sender.sendMessage(ChatColor.GOLD + "Author: " + ChatColor.GRAY + Paintball.getInstance().getDescription().getAuthors().toString());
		sender.sendMessage(ChatColor.GOLD + "Source code: " + ChatColor.GRAY + "https://github.com/TheMolkaPL/Paintball");
		return true;
	}
	
}

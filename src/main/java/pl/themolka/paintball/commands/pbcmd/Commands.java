package pl.themolka.paintball.commands.pbcmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Commands {
	
	public static boolean list(CommandSender sender, int page) {
		int nextPage = page + 1;
		if(page == 1) {
			sender.sendMessage(ChatColor.GOLD + " ===== Paintball's commands <Page " + page + "/2> ===== ");
			sender.sendMessage(ChatColor.GOLD + "/join " + ChatColor.GRAY + "- Join to the team and start the game");
			sender.sendMessage(ChatColor.GOLD + "/maplist " + ChatColor.GRAY + "- Show all loades maps on this server");
			sender.sendMessage(ChatColor.GOLD + "/myteam " + ChatColor.GRAY + "- Get information about team you are on");
			sender.sendMessage(ChatColor.GOLD + "/pb about " + ChatColor.GRAY + "- Show all informations about this plugin");
			sender.sendMessage(ChatColor.GOLD + "/pb help " + ChatColor.GRAY + "- Paintball's Help Center");
			sender.sendMessage(ChatColor.GOLD + "/pb info " + ChatColor.GRAY + "- Show Paintball's Help Center");
			sender.sendMessage(ChatColor.GRAY + "Next page by typing /pb commands " + nextPage);
			return true;
		}
		if(page == 2) {
			sender.sendMessage(ChatColor.GOLD + " ===== Paintball's commands <Page " + page + "/2> ===== ");
			sender.sendMessage(ChatColor.GOLD + "/pb map " + ChatColor.GRAY + "- Show all informations about maps");
			sender.sendMessage(ChatColor.GOLD + "/pb maplist " + ChatColor.GRAY + "- Show all loades maps on this server");
			sender.sendMessage(ChatColor.GOLD + "/pb match " + ChatColor.GRAY + "- Show all informations about current match");
			sender.sendMessage(ChatColor.GOLD + "/pb reload " + ChatColor.GRAY + "- Reload all plugin settings and maps files");
			sender.sendMessage(ChatColor.GOLD + "/pb set " + ChatColor.GRAY + "- Set a setting");
			sender.sendMessage(ChatColor.GOLD + "/vote " + ChatColor.GRAY + "- Vote new map");
			sender.sendMessage(ChatColor.GRAY + "Next page by typing /pb commands " + nextPage);
			return true;
		} else {
			sender.sendMessage(ChatColor.GOLD + " ===== Paintball's commands <Page " + page + "/2> ===== ");
			sender.sendMessage(ChatColor.GRAY + "Next page by typing /pb commands " + nextPage);
			return true;
		}
	}
	
}

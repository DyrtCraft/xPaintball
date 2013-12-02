package pl.themolka.paintball.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.themolka.paintball.Paintball;
import pl.themolka.paintball.api.PbPlugin;
import pl.themolka.paintball.inventories.VoteInventory;

public class VoteCommand implements CommandExecutor {
	
	Paintball plugin;
	public static ArrayList<String> votedPlayers = new ArrayList<String>();
	
	public VoteCommand(Paintball plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String args[]) {
		if(command.getName().equalsIgnoreCase("vote") || command.getName().equalsIgnoreCase("v")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Bad sender!");
				return true;
			}
			
			if(!PbPlugin.getTeams().isJoinable() && !PbPlugin.getTeams().isRunning()) {
				if(votedPlayers.contains(sender.getName())) {
					sender.sendMessage(ChatColor.RED + "You have already voted new map! Your vote can not be change.");
					return true;
				} else {
					((Player) sender).openInventory(VoteInventory.inv);
					return true;
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You can not vote at this time!" + ChatColor.GOLD + " Voting is only enabled on end-game.");
				return true;
			}
		}
		return false;
	}
	
}

package pl.themolka.paintball.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.themolka.paintball.Paintball;
import pl.themolka.paintball.PbPlugin;
import pl.themolka.paintball.api.TeamType;
import pl.themolka.paintball.game.Teams;

public class MyteamCommand implements CommandExecutor {
	
	Paintball plugin;
	
	public MyteamCommand(Paintball plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("myteam") || command.getName().equalsIgnoreCase("mt")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Bad sender!");
				return true;
			}
			if(Teams.getInstance().isTeam((Player) sender, TeamType.BLUE)) {
				sender.sendMessage(ChatColor.DARK_PURPLE + "You are currently in the " + ChatColor.DARK_BLUE + PbPlugin.getTeams().getTeamName(TeamType.BLUE) + ChatColor.DARK_PURPLE + ".");
				return true;
			}
			if(Teams.getInstance().isTeam((Player) sender, TeamType.RED)) {
				sender.sendMessage(ChatColor.DARK_PURPLE + "You are currently in the " + ChatColor.RED + PbPlugin.getTeams().getTeamName(TeamType.RED) + ChatColor.DARK_PURPLE + ".");
				return true;
			}
			if(Teams.getInstance().isTeam((Player) sender, TeamType.OBSERVATOR)) {
				sender.sendMessage(ChatColor.DARK_PURPLE + "You are currently in the " + ChatColor.AQUA + PbPlugin.getTeams().getTeamName(TeamType.OBSERVATOR) + ChatColor.DARK_PURPLE + ".");
				return true;
			}
		}
		return false;
	}
	
}

package pl.themolka.paintball.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.themolka.paintball.Paintball;
import pl.themolka.paintball.api.TeamType;
import pl.themolka.paintball.game.Teams;

public class JoinCommand implements CommandExecutor {

	Paintball plugin;
	
	public JoinCommand(Paintball plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("join") || command.getName().equalsIgnoreCase("play")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Bad sender!");
				return true;
			}
			if(args.length == 0) {
				Teams.getInstance().setTeam((Player) sender, TeamType.RANDOM);
				return true;
			}
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("blue")) {
					Teams.getInstance().setTeam((Player) sender, TeamType.BLUE);
					return true;
				}
				if(args[0].equalsIgnoreCase("obs")) {
					Teams.getInstance().setTeam((Player) sender, TeamType.OBSERVER);
					return true;
				}
				if(args[0].equalsIgnoreCase("red")) {
					Teams.getInstance().setTeam((Player) sender, TeamType.RED);
					return true;
				} else {
					sender.sendMessage(ChatColor.RED + "Team named \"" + args[1] + "\" not found!");
					return true;
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Too many arguments!");
				return true;
			}
		}
		return false;
	}
	
}

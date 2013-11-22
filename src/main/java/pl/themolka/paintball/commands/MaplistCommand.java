package pl.themolka.paintball.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import pl.themolka.paintball.Paintball;

public class MaplistCommand implements CommandExecutor {
	
	Paintball plugin;
	
	public MaplistCommand(Paintball plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return false;
	}
	
}

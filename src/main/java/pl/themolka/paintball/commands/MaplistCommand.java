package pl.themolka.paintball.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import pl.themolka.paintball.Paintball;
import pl.themolka.paintball.commands.pbcmd.Maplist;

public class MaplistCommand implements CommandExecutor {
	
	Paintball plugin;
	
	public MaplistCommand(Paintball plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Maplist.execute(sender, command, label, args);
		return true;
	}
	
}

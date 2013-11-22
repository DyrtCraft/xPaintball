package pl.themolka.paintball.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import pl.themolka.paintball.Paintball;
import pl.themolka.paintball.PbPlugin;
import pl.themolka.paintball.commands.pbcmd.About;
import pl.themolka.paintball.commands.pbcmd.Commands;

public class PaintballCommand implements CommandExecutor {
	
	Paintball plugin;
	
	public PaintballCommand(Paintball plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("paintball") || command.getName().equalsIgnoreCase("pb")) {
			if(args.length == 0) {
				return true;
			}
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("about") || args[0].equalsIgnoreCase("version")) {
					return About.about(sender);
				}
				if(args[0].equalsIgnoreCase("commands") || args[0].equalsIgnoreCase("cmds")) {
					return Commands.list(sender, 1);
				}
				if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("?")) {
					return PbPlugin.getHelp().getHelp(sender, null);
				}
				if(args[0].equalsIgnoreCase("info")) {
					
				}
				if(args[0].equalsIgnoreCase("map")) {
					
				}
				if(args[0].equalsIgnoreCase("maplist")) {
					
				}
				if(args[0].equalsIgnoreCase("match")) {
					
				}
				if(args[0].equalsIgnoreCase("reload")) {
					
				}
				if(args[0].equalsIgnoreCase("set")) {
					
				} else {
					return true;
				}
			}
			if(args.length == 2) {
				if(args[0].equalsIgnoreCase("commands") || args[0].equalsIgnoreCase("cmds")) {
					try {
						int page = Integer.parseInt(args[1]);
						return Commands.list(sender, page);
					} catch(NumberFormatException ex) {
						sender.sendMessage(ChatColor.RED + "Argument " + args[1] + " is not a valid number!");
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("?")) {
					return PbPlugin.getHelp().getHelp(sender, args[1]);
				}
				if(args[0].equalsIgnoreCase("match")) {
					if(args[1].equalsIgnoreCase("start")) {
						sender.sendMessage(ChatColor.DARK_GREEN + "Starting the match...");
						PbPlugin.getMatch().start();
						return true;
					}
					if(args[1].equalsIgnoreCase("end")) {
						sender.sendMessage(ChatColor.DARK_GREEN + "Stopping the match...");
						PbPlugin.getMatch().end();
						return true;
					}
				} else {
					return true;
				}
			} else {
				return true;
			}
		}
		return false;
	}
	
}

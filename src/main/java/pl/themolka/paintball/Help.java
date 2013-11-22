package pl.themolka.paintball;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Help implements pl.themolka.paintball.api.Help {
	
	@Override
	public boolean getHelp(CommandSender sender, String command) {
		if(command == null) {
			sender.sendMessage(ChatColor.GOLD + " ===== Paintballs's Help Center ===== ");
			sender.sendMessage(ChatColor.GRAY + "Welcome to Paintballs's help center. Here you can get a help for specifited command!");
			sender.sendMessage(ChatColor.GOLD + "Let's started by using /pb help <command>!");
			sender.sendMessage(ChatColor.GOLD + "Get all Paintball's commands by typing /pb commands");
			return true;
		}
		
		if(command.equalsIgnoreCase("about") || command.equalsIgnoreCase("version")) {
			sendHelp(sender, "/pb about", "version", "Show all informations about this plugin", "/pb about", null, true);
			return true;
		}
		if(command.equalsIgnoreCase("commands") || command.equalsIgnoreCase("cmds")) {
			sendHelp(sender, "/pb commands", "cmds", "Get all Paintball's commands", "/pb commands", null, true);
			return true;
		}
		if(command.equalsIgnoreCase("help") || command.equalsIgnoreCase("?")) {
			sendHelp(sender, "/pb help", "?", "Show Paintball's Help Center", "/pb help\n/pgm help <command>", null, true);
			return true;
		}
		if(command.equalsIgnoreCase("info")) {
			sendHelp(sender, "/pb info", null, "Show all information about currently settings", "/pb info map <author>\n/pb info map <version>\n/pb info map <desc>", "pb.op.info", true);
			return true;
		}
		if(command.equalsIgnoreCase("join")) {
			sendHelp(sender, "/join", "play", "Join to the team and start the game", "/join\n/join blue\n/join obs\n/join red", null, false);
			return true;
		}
		if(command.equalsIgnoreCase("map")) {
			sendHelp(sender, "/pb map", null, "Show all informations about maps", "/pb map new <name>\n/pb map del <name>", "pb.op.map", true);
			return true;
		}
		if(command.equalsIgnoreCase("maplist")) {
			sendHelp(sender, "/pb maplist", null, "Show all loades maps on this server", "/maplist", null, true);
			return true;
		}
		if(command.equalsIgnoreCase("match")) {
			sendHelp(sender, "/pb match", null, "Show all informations about current match", "/pb match\n/pb match start\n/pb match end", "pb.op.match", false);
			return true;
		}
		if(command.equalsIgnoreCase("myteam")) {
			sendHelp(sender, "/myteam", "mt", "Get information about team you are on", "/myteam", null, false);
			return true;
		}
		if(command.equalsIgnoreCase("pb")) {
			sendHelp(sender, "/pb", null, "Set/get informations about this plugin and more", "/pb commands", null, true);
			return true;
		}
		if(command.equalsIgnoreCase("reload")) {
			sendHelp(sender, "/pb reload", null, "Reload all plugin settings and maps files", "/pb reload", "pb.op.reload", true);
			return true;
		}
		if(command.equalsIgnoreCase("set")) {
			sendHelp(sender, "/pb set", null, "Set a setting", "/pb set <setting> <value>", "pb.op.set", true);
			return true;
		}
		if(command.equalsIgnoreCase("vote")) {
			sendHelp(sender, "/vote", null, "Vote new map", "/pb vote", null, false);
			return true;
		} else {
			sender.sendMessage(ChatColor.RED + "Help for \"" + command + "\" was not found! Please try again and typing other command");
			return true;
		}
	}
	
	@Override
	public void sendHelp(CommandSender sender, String command, String alias, String description, String usage, String permission, boolean canConsole) {
		sender.sendMessage(ChatColor.GOLD + " ===== Paintball's help center ===== ");
		sender.sendMessage(ChatColor.GOLD + "Help for: " + ChatColor.GRAY + command);
		sender.sendMessage(ChatColor.GOLD + "Description: " + ChatColor.GRAY + description);
		if(!(alias == null)) {
			sender.sendMessage(ChatColor.GOLD + "Aliases: " + ChatColor.GRAY + alias);
		}
		if(!(permission == null)) {
			sender.sendMessage(ChatColor.GOLD + "Permission: " + ChatColor.GRAY + permission);
		}
		if(canConsole == true) {
			sender.sendMessage(ChatColor.DARK_GREEN + "This command can be use by console!");
		}
		if(canConsole == false) {
			sender.sendMessage(ChatColor.RED + "This command can not be use by console!");
		}
		sender.sendMessage(ChatColor.GOLD + "Example Usages: " + ChatColor.GRAY + usage);
	}
	
}

package pl.themolka.paintball.api;

import org.bukkit.command.CommandSender;

public interface Help {
	
	/**
	 * Get help about specifited command
	 * @param sender Sender to send help
	 * @param command Command to get a help
	 * @return true if command is supported
	 */
	public boolean getHelp(CommandSender sender, String command);
	
	/**
	 * Send a help message to player
	 * @param sender Sender to send help
	 * @param command Command (/example)
	 * @param alias Alias (/ex)
	 * @param description Description (This is example command to get help)
	 * @param usage Usage (/example <example command>)
	 * @param permission Permission (exampleplugin.command.example)
	 * @param canConsole If console can execute that
	 */
	public void sendHelp(CommandSender sender, String command, String alias, String description, String usage, String permission, boolean canConsole);
	
}

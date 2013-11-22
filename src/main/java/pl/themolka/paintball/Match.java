package pl.themolka.paintball;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import pl.themolka.paintball.api.TeamType;
import pl.themolka.paintball.api.events.TeamWonEvent;
import pl.themolka.paintball.tasks.MatchTask;

public class Match implements pl.themolka.paintball.api.Match{
	
	Paintball plugin;
	Teams teams;
	
	private int blueScore;
	private int redScore;
	
	public Match(Paintball plugin) {
		this.plugin = plugin;
		this.teams = new Teams(plugin);
	}
	
	@Override
	public void addScore(TeamType teamType, int amount) {
		int newAmount = getScore(teamType) + amount;
		setScore(teamType, newAmount);
	}
	
	@Override
	public void end() {
		TeamWonEvent event = new TeamWonEvent(win());
		Bukkit.getPluginManager().callEvent(event);
		
		PbPlugin.getTeams().setJoinable(false);
		PbPlugin.getTeams().setRunning(false);
		
		Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "########################");
		Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "#   " + PbPlugin.getTeams().getTeamName(win()) + ChatColor.DARK_PURPLE + " won!" + "   #");
		Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "########################");
		Bukkit.broadcastMessage(ChatColor.GOLD + "Thanks for playing! Say 'Good Game' to all!");
		Bukkit.broadcastMessage(ChatColor.GOLD + "Vote new map by using /vote, you have 20 second for that!");
	}
	
	@Override
	public int getScore(TeamType teamType) {
		if(teamType == TeamType.BLUE) {
			return blueScore;
		}
		if(teamType == TeamType.RED) {
			return redScore;
		} else {
			return -1;
		}
	}
	
	@Override
	public void setScore(TeamType teamType, int amount) {
		if(teamType == TeamType.BLUE) {
			blueScore = amount;
		}
		if(teamType == TeamType.RED) {
			redScore = amount;
		}
	}
	
	@Override
	public void start() {
		for(String blueStr : Teams.getBluePlayers) {
			Player blue = Bukkit.getPlayer(blueStr);
			if(blue.isOnline()) {
				Teams.join(blue, TeamType.BLUE);
			}
		}
		for(String redStr : Teams.getRedPlayers) {
			Player red = Bukkit.getPlayer(redStr);
			if(red.isOnline()) {
				Teams.join(red, TeamType.RED);
			}
		}
		
		teams.setRunning(true);
		
		Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "########################");
		Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "# " + ChatColor.DARK_GREEN + "The match has started!" + ChatColor.DARK_PURPLE + " #");
		Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "########################");
		Bukkit.broadcastMessage(ChatColor.GOLD + " >=====> Your team has 5 minutes for win! <=====< ");
		
		new MatchTask(plugin);
	}
	
	private TeamType win() {
		if(PbPlugin.getMatch().getScore(TeamType.BLUE) > PbPlugin.getMatch().getScore(TeamType.RED)) {
			return TeamType.BLUE;
		}
		if(PbPlugin.getMatch().getScore(TeamType.RED) > PbPlugin.getMatch().getScore(TeamType.BLUE)) {
			return TeamType.RED;
		} else {
			return null;
		}
	}
	
}

package pl.themolka.paintball.api.game;

import java.util.List;

import org.bukkit.entity.Player;

public interface Teams {
	
	public List<String> getPlayers(TeamType teamType);
	
	public TeamType getTeamType(Player player);
	
	public String getTeamName(TeamType teamType);
	
	public boolean isJoinable();
	
	public boolean isRunning();
	
	public boolean isTeam(Player player, TeamType teamType);
	
	public void setJoinable(boolean joinable);
	
	public void setRunning(boolean running);
	
	public void setTeam(Player player, TeamType teamType);
	
}

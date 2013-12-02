package pl.themolka.paintball.api.events;

import java.util.List;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import pl.themolka.paintball.api.PbPlugin;
import pl.themolka.paintball.api.game.TeamType;

public class TeamWonEvent extends Event {
	
	private static final HandlerList handlerList = new HandlerList();
	private List<String> players;
	private TeamType teamType;
	
	public TeamWonEvent(TeamType teamType) {
		this.players = PbPlugin.getTeams().getPlayers(teamType);
		this.teamType = teamType;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlerList;
	}
	
	public static HandlerList getHandlerList() {
		return handlerList;
	}
	
	public List<String> getPlayers() {
		return players;
	}
	
	public TeamType getTeamType() {
		return teamType;
	}
	
}

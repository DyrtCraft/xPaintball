package pl.themolka.paintball.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import pl.themolka.paintball.api.TeamType;

public class PlayerJoinTeamEvent extends Event {
	
	private static final HandlerList handlerList = new HandlerList();
	private Player player;
	private TeamType teamType;
	
	public PlayerJoinTeamEvent(Player player, TeamType teamType) {
		this.player = player;
		this.teamType = teamType;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlerList;
	}
	
	public static HandlerList getHandlerList() {
		return handlerList;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public TeamType getTeamType() {
		return teamType;
	}
	
}

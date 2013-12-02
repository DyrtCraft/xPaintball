package pl.themolka.paintball.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import pl.themolka.paintball.Paintball;
import pl.themolka.paintball.PbPlugin;
import pl.themolka.paintball.api.TeamType;
import pl.themolka.paintball.game.Teams;

public class PlayerMove implements Listener {
	
	Paintball painball;
	
	public PlayerMove(Paintball paintball) {
		this.painball = paintball;
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		if(Teams.getInstance().isTeam(e.getPlayer(), TeamType.OBSERVER)) {
			PbPlugin.getCurrentMap().getSpawn(TeamType.OBSERVER);
			return;
		} else {
			if(PbPlugin.getTeams().isRunning()) {
				if(e.getTo().getBlockY() == -10) {
					e.getPlayer().damage(20.0);
					return;
				}
			} else {
				if(e.getTo().getBlockY() == -10) {
					PbPlugin.getCurrentMap().getSpawn(TeamType.OBSERVER);
					return;
				}
			}
		}
	}
	
}

package pl.themolka.paintball.listeners;

import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import pl.themolka.paintball.Paintball;
import pl.themolka.paintball.PbPlugin;
import pl.themolka.paintball.api.TeamType;

public class ItemUsage implements Listener {
	
	Paintball plugin;
	
	public ItemUsage(Paintball plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if(PbPlugin.getTeams().isTeam(e.getPlayer(), TeamType.OBSERVER) || !PbPlugin.getTeams().isRunning()) {
			e.setCancelled(true);
			return;
		}
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			// Default pistol
			if(e.getItem() == PbPlugin.getItems().defaultPistol()) {
				short durability = (short) (e.getPlayer().getInventory().getItem(0).getDurability() - 10);
				
				e.getPlayer().getWorld().spawn(e.getPlayer().getLocation(), Snowball.class);
				e.getPlayer().getInventory().getItem(0).setDurability(durability);
			}
		}
	}
	
}

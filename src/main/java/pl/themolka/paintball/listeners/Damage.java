package pl.themolka.paintball.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import pl.themolka.paintball.Paintball;

public class Damage implements Listener {
	
	Paintball plugin;
	
	public Damage(Paintball plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		if(!(e.getCause() == DamageCause.PROJECTILE)) {
			e.setCancelled(true);
		} else {
			/*
			 * TODO
			 */
		}
	}
	
	@EventHandler
	public void onProjectileLaunch(ProjectileLaunchEvent e) {
		if(e.getEntity().getShooter().getType() == EntityType.PLAYER) {
			if(e.getEntityType() == EntityType.PLAYER) {
				
			}
		}
	}
	
}

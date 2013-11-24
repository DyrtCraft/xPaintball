package pl.themolka.paintball.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import pl.themolka.paintball.Paintball;
//import pl.themolka.paintball.PbPlugin;
import pl.themolka.paintball.Teams;
import pl.themolka.paintball.inventories.TeamChooserInventory;
import pl.themolka.paintball.inventories.VoteInventory;

public class Observator implements Listener {
	
	Paintball plugin;
	Teams teams;
	
	public Observator(Paintball plugin) {
		this.plugin = plugin;
		this.teams = new Teams(plugin);
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerBucketEmptyEvent(PlayerBucketEmptyEvent e) {
		if(!Teams.getBluePlayers.contains(e.getPlayer().getName()) || !Teams.getRedPlayers.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerBucketFill(PlayerBucketFillEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerDamage(PlayerDropItemEvent e) {
		if(!Teams.getBluePlayers.contains(e.getPlayer().getName()) || !Teams.getRedPlayers.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerFish(PlayerFishEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if(!Teams.getBluePlayers.contains(e.getPlayer().getName()) || !Teams.getRedPlayers.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
			if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Choose your team!")) {
					//if(PbPlugin.getTeams().isJoinable()) {
						e.getPlayer().openInventory(TeamChooserInventory.inv);
					//}
				}
				if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Vote new map!")) {
					//if(PbPlugin.getMatch().isVote()) {
						e.getPlayer().openInventory(VoteInventory.inv);
					//}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent e) {
		e.setCancelled(true);
	}
	
}

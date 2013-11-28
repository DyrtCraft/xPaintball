package pl.themolka.paintball.inventories;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import pl.themolka.paintball.Paintball;
import pl.themolka.paintball.PbPlugin;
import pl.themolka.paintball.api.Map;
import pl.themolka.paintball.commands.VoteCommand;

public class VoteInventory implements Listener {
	
	Paintball plugin;
	
	public static Inventory inv;
	
	private ItemStack _0, _1, _2, _3, _4, _5, _6, _7, _8;
	
	public VoteInventory(Paintball plugin) {
		this.plugin = plugin;
		
		inv = Bukkit.createInventory(null, 9, ChatColor.GOLD + "Vote new map!");
		
		// TESTS START
		_0 = newMap(PbPlugin.getMap("Default_Map"));
		// TESTS END
		
		inv.setItem(0, _0);
		inv.setItem(1, _1);
		inv.setItem(2, _2);
		inv.setItem(3, _3);
		inv.setItem(4, _4);
		inv.setItem(5, _5);
		inv.setItem(6, _6);
		inv.setItem(7, _7);
		inv.setItem(8, _8);
	}
	
	private ItemStack newMap(Map map) {
		ItemStack i = new ItemStack(Material.EMPTY_MAP, 1);
		ItemMeta iMeta = i.getItemMeta();
		iMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + map.getName());
		iMeta.setLore(Arrays.asList("Description: " + ChatColor.GOLD + map.getDescription(), "Max players per team: " + ChatColor.GOLD + map.getMaxPlayers(), "Author(s): " + ChatColor.GOLD + map.getAuthors()));
		i.setItemMeta(iMeta);
		return i;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if(!e.getInventory().getName().equalsIgnoreCase(inv.getName())) { return; }
		
		e.setCancelled(true);
		Player player = (Player) e.getWhoClicked();
		
		try {
			if(VoteCommand.votedPlayers.contains(player.getName())) {
				player.sendMessage(ChatColor.RED + "You have already voted new map! Your vote can not be change.");
				player.closeInventory();
				return;
			} else {
				if(e.getCurrentItem().getType() == Material.EMPTY_MAP) {
					ItemMeta map = e.getCurrentItem().getItemMeta();
					
					/*
					 * TODO Voting
					 */
					
					VoteCommand.votedPlayers.add(player.getName());
					player.sendMessage(ChatColor.DARK_PURPLE + "" + "You have successfully voted " + ChatColor.RESET + map.getDisplayName() + ChatColor.DARK_PURPLE + ".");
					player.closeInventory();
					player.getInventory().removeItem(PbPlugin.getNavigateItems().mapVoter());
				}
			}
		} catch(NullPointerException ex) {}
	}
	
}

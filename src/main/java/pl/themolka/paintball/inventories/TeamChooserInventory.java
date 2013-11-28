package pl.themolka.paintball.inventories;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;

import pl.themolka.paintball.Paintball;
import pl.themolka.paintball.PbPlugin;
import pl.themolka.paintball.api.TeamType;

public class TeamChooserInventory implements Listener {
	
	Paintball plugin;
	
	public static Inventory inv;
	private static ItemStack auto, blue, red, quit;
	
	public TeamChooserInventory(Paintball plugin) {
		this.plugin = plugin;
		
		inv = Bukkit.createInventory(null, 9, ChatColor.GOLD + "Choose your team!");
		
		auto = auto();
		blue = blue();
		red = red();
		quit = quit();
		
		inv.setItem(0, auto);
		inv.setItem(1, blue);
		inv.setItem(2, red);
		inv.setItem(8, quit);
	}
	
	private ItemStack auto() {
		ItemStack i = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta iMeta = i.getItemMeta();
		iMeta.setDisplayName(ChatColor.AQUA + "Auto join");
		iMeta.setLore(Arrays.asList("Join to the random team"));
		i.setItemMeta(iMeta);
		return i;
	}
	
	private ItemStack blue() {
		int max = PbPlugin.getCurrentMap().getMaxPlayers();
		
		Wool wool = new Wool(DyeColor.BLUE);
		ItemStack i = new ItemStack(wool.toItemStack(1));
		ItemMeta iMeta = i.getItemMeta();
		iMeta.setDisplayName(PbPlugin.getTeams().getTeamName(TeamType.BLUE));
		iMeta.setLore(Arrays.asList(PbPlugin.getTeams().getPlayers(TeamType.BLUE).size() + "/" + max, "Join to the blue team", ChatColor.GOLD + "Only for VIP members!"));
		i.setItemMeta(iMeta);
		return i;
	}
	
	private ItemStack red() {
		int max = PbPlugin.getCurrentMap().getMaxPlayers();
		
		Wool wool = new Wool(DyeColor.RED);
		ItemStack i = new ItemStack(wool.toItemStack(1));
		ItemMeta iMeta = i.getItemMeta();
		iMeta.setDisplayName(PbPlugin.getTeams().getTeamName(TeamType.RED));
		iMeta.setLore(Arrays.asList(PbPlugin.getTeams().getPlayers(TeamType.RED).size() + "/" + max, "Join to the red team", ChatColor.GOLD + "Only for VIP members!"));
		i.setItemMeta(iMeta);
		return i;
	}
	
	private ItemStack quit() {
		ItemStack i = new ItemStack(Material.EYE_OF_ENDER, 1);
		ItemMeta iMeta = i.getItemMeta();
		iMeta.setDisplayName("Close window");
		i.setItemMeta(iMeta);
		return i;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if(!e.getInventory().getName().equalsIgnoreCase(inv.getName())) { return; }
		
		e.setCancelled(true);
		Player player = (Player) e.getWhoClicked();
		
		try {
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(auto.getItemMeta().getDisplayName())) {
				PbPlugin.getTeams().setTeam(player, null);
				player.closeInventory();
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(blue.getItemMeta().getDisplayName())) {
				PbPlugin.getTeams().setTeam(player, TeamType.BLUE);
				player.closeInventory();
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(red.getItemMeta().getDisplayName())) {
				PbPlugin.getTeams().setTeam(player, TeamType.RED);
				player.closeInventory();
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(quit.getItemMeta().getDisplayName())) {
				player.closeInventory();
			}
		} catch(NullPointerException ex) {}
	}
	
}

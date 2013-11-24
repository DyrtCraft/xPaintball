package pl.themolka.paintball;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class NavigateItems implements pl.themolka.paintball.api.NavigateItems {
	
	@Override
	public ItemStack compass() {
		ItemStack i = new ItemStack(Material.COMPASS, 1);
		return i;
	}
	
	@Override
	public ItemStack mapVoter() {
		ItemStack i = new ItemStack(Material.EMPTY_MAP, 1);
		ItemMeta iMeta = i.getItemMeta();
		iMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Vote new map!");
		i.setItemMeta(iMeta);
		return i;
	}
	
	@Override
	public ItemStack teamChooser() {
		ItemStack i = new ItemStack(Material.FIREWORK_CHARGE, 1);
		ItemMeta iMeta = i.getItemMeta();
		iMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Choose your team!");
		i.setItemMeta(iMeta);
		return i;
	}
	
}

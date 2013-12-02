package pl.themolka.paintball.game;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items implements pl.themolka.paintball.api.game.Items {
	
	public ItemStack defaultPistol() {
		ItemStack i = new ItemStack(Material.STICK, 1);
		ItemMeta iMeta = i.getItemMeta();
		iMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Default Pistol");
		i.setItemMeta(iMeta);
		return i;
	}
	
}

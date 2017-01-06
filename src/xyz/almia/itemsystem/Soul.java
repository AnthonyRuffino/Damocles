package xyz.almia.itemsystem;

import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import net.md_5.bungee.api.ChatColor;

public class Soul implements Listener{
	
	public ItemStack soulApple(xyz.almia.accountsystem.Character character){
		ItemStack item = new ItemStack(Material.GOLDEN_APPLE, 1);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(character.getUsername()+"'s Soul");
		im.setLore(Arrays.asList(
				ChatColor.GRAY+ "",
				""
				));
		return item;
	}
	
	@EventHandler
	public void death(PlayerDeathEvent event){
		@SuppressWarnings("unused")
		Player player = event.getEntity();
	}
	
	@EventHandler
	public void dropItem(PlayerDropItemEvent event){
		
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event){
		
	}
	
}

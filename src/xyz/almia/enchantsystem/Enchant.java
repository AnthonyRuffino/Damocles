package xyz.almia.enchantsystem;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import com.connorlinfoot.actionbarapi.ActionBarAPI;

import xyz.almia.cardinalsystem.Cardinal;

public class Enchant implements Listener{
	
	public static Plugin plugin = Cardinal.getPlugin();
	
	/*
	 *    TODO LIST:
	 *    - ADD SO KILLING A PLAYER GIVES YOU A PORTION OF THEIR ENCHANT_LEVEL
	 */	
	
	@EventHandler
	public void onRightClickEnchantmentTable(PlayerInteractEvent event){
		Player player = event.getPlayer();
		if(event.getClickedBlock() != null){
			if(event.getClickedBlock().getType().equals(Material.ENCHANTMENT_TABLE)){
				event.setCancelled(true);
				ActionBarAPI.sendActionBar(player, ChatColor.YELLOW+"An ominous feeling creeps over you.");
			}
		}
	}
	
	
}

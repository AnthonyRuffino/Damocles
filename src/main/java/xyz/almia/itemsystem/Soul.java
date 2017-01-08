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
import xyz.almia.accountsystem.Account;

public class Soul implements Listener{
	
	public static ItemStack soulApple(xyz.almia.accountsystem.Character character){
		ItemStack item = new ItemStack(Material.GOLDEN_APPLE, 1);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.DARK_GRAY+ character.getUsername()+"'s Soul");
		im.setLore(Arrays.asList(
				ChatColor.GRAY+ "You have " + ChatColor.RED+ character.getSouls()+ ChatColor.GRAY + " souls.",
				ChatColor.GRAY+"Max souls is" + ChatColor.RED+ " 5"+ChatColor.GRAY+"."
				));
		item.setItemMeta(im);
		return item;
	}
	
	@EventHandler
	public void death(PlayerDeathEvent event){
		Player player = event.getEntity();
		Account account = new Account(player);
		try{
			xyz.almia.accountsystem.Character character = account.getLoadedCharacter();
			event.getDrops().remove(soulApple(character));
			character.setSouls(character.getSouls()-1);
		}catch(Exception e) {}
	}
	
	@EventHandler
	public void dropItem(PlayerDropItemEvent event){
		Player player = event.getPlayer();
		Account account = new Account(player);
		try{
			xyz.almia.accountsystem.Character character = account.getLoadedCharacter();
			if(event.getItemDrop().getItemStack().equals(soulApple(character))){
				event.getItemDrop().remove();
			}
		}catch(Exception e) {}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event){
		Player player = (Player)event.getWhoClicked();
		Account account = new Account(player);
		try{
			xyz.almia.accountsystem.Character character = account.getLoadedCharacter();
			if(event.getCurrentItem() != null){
				if(event.getCurrentItem().equals(soulApple(character))){
					event.setCancelled(true);
				}
			}
		}catch(Exception e){}

	}
	
}

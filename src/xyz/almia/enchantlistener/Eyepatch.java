package xyz.almia.enchantlistener;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import xyz.almia.cardinalsystem.Cardinal;
import xyz.almia.itemsystem.Armor;
import xyz.almia.itemsystem.EnchantTypes;
import xyz.almia.itemsystem.Enchantments;
import xyz.almia.itemsystem.ItemHandler;

public class Eyepatch {
	
	public static void checkForEyepatchEnchant(){
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(Cardinal.getPlugin(), new Runnable() {

			@Override
			public void run() {
				
				for(Player player : Bukkit.getOnlinePlayers()){
					if(player.getInventory().getHelmet() != null){
						ItemStack item = player.getInventory().getHelmet();
						if(ItemHandler.getEnchantType(item).equals(EnchantTypes.HELMET)){
							Armor detailItem = new Armor(item);
							if(detailItem.getEnchants() != null){
								List<Enchantments> enchantments = detailItem.getEnchants();
								if(enchantments.contains(Enchantments.EYEPATCH)){
									ItemMeta im = item.getItemMeta();
									@SuppressWarnings("unused")
									String[] enchantAndDamage = null;
									for(String s : im.getLore()){
										if(s.contains(ChatColor.GRAY + "Eyepatch ")){
											enchantAndDamage = s.split(" ");
										}
									}
									if(player.getActivePotionEffects().contains(PotionEffectType.BLINDNESS)){
										player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 0, 0));
									}
								}
							}
						}
					}
				}
				
			}
        	
        }, 0, 1);
	}
	
}
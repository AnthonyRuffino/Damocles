package xyz.almia.enchantlistener;

import java.util.List;
import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.almia.itemsystem.EnchantTypes;
import xyz.almia.itemsystem.Enchantments;
import xyz.almia.itemsystem.ItemHandler;
import xyz.almia.itemsystem.Weapon;
import xyz.almia.utils.RomanNumerals;

public class BloodThirst implements Listener{
	ItemHandler itemhandler = new ItemHandler();
	
	@EventHandler
	public void bloodThirstTrigger(EntityDeathEvent event){
		if(event.getEntity() instanceof LivingEntity){
			if(event.getEntity().getKiller() != null){
				Player player = event.getEntity().getKiller();
				
				if(player.getInventory().getItemInMainHand() != null){
					ItemStack item = player.getInventory().getItemInMainHand();
					if(itemhandler.getEnchantType(item).equals(EnchantTypes.SWORD)){
						Weapon detailItem = new Weapon(item);
						if(detailItem.getEnchants() != null){
							List<Enchantments> enchantments = detailItem.getEnchants();
							if(enchantments.contains(Enchantments.BLOODTHIRST)){
								int chance = 100;
								int rand = new Random().nextInt(100);
								ItemMeta im = item.getItemMeta();
								String[] enchantAndDamage = null;
								for(String s : im.getLore()){
									if(s.contains(ChatColor.GRAY + "Bloodthirst ")){
										enchantAndDamage = s.split(" ");
									}
								}
								@SuppressWarnings("unused")
								int level = RomanNumerals.romanToInt(enchantAndDamage[1]);
								
								if((rand <= chance)){
									player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 120, 0));
								}else{
									return;
								}
							}
							return;
						}
					}
				}
				
			}
		}
	}
	
}

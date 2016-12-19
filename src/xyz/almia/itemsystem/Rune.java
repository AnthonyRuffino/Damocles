package xyz.almia.itemsystem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import xyz.almia.utils.Data;
import xyz.almia.utils.RomanNumerals;

public class Rune implements Listener{
	
	public static ItemStack setAmount(ItemStack item, int amount){
		if(amount == 0){
			return new ItemStack(Material.AIR, 1);
		}else{
			item.setAmount(amount);
			return item;
		}
	}
	
	public static ItemStack createSlotRune(int slots){
		ItemStack rune = new ItemStack(Material.EYE_OF_ENDER, 1);
		ItemMeta runeMeta = rune.getItemMeta();
		String name = ChatColor.YELLOW + "Slot Rune";
		String amount = Data.encodeItemData(slots + "");
		runeMeta.setDisplayName(name + amount);
		runeMeta.setLore(Arrays.asList(new String[] { 
				ChatColor.WHITE + "" + ChatColor.BOLD + "+" + slots + " Slots",
				ChatColor.GRAY + "Place rune on item to apply slots."}));
		rune.setItemMeta(runeMeta);
		return rune;
	}
	
	public static int getSlotsFromRune(ItemStack item){
		if(item.hasItemMeta()){
			ItemMeta runeMeta = item.getItemMeta();
			String amount = Data.decodeItemData(runeMeta.getDisplayName());
			return Integer.valueOf(amount);
		}
		return -1;
	}
	
	public static ItemStack createRune(Enchantments enchant, int level, int success, int destroy){
		ItemStack rune = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta runeMeta = rune.getItemMeta();
		String name = ChatColor.YELLOW + "Rune of " + ChatColor.UNDERLINE + Enchantments.getName(enchant) + ChatColor.RESET + " " + ChatColor.YELLOW + RomanNumerals.intToRoman(level);
		String enchantment = Data.encodeItemData(enchant.toString() + "'" + level + "'" + success + "'" + destroy);
		runeMeta.setDisplayName(name + enchantment);
		runeMeta.setLore(Arrays.asList(new String[] { ChatColor.GREEN + "" + success + "% Success Rate",
				ChatColor.RED + "" + destroy + "% Destroy Rate On Fail.",
				ChatColor.YELLOW + "Level " + ChatColor.GOLD + "" + level,
				ChatColor.GOLD + "" + Enchantments.getType(enchant).toString() + " Enchantment",
				ChatColor.GRAY + "Place rune on item to enchant."}));
		rune.setItemMeta(runeMeta);
		return rune;
	}
	
	public static Map<String, Integer> getRune(ItemStack rune){
		Map<String, Integer> value = new HashMap<String, Integer>();
		if(rune.hasItemMeta()){
			ItemMeta runeMeta = rune.getItemMeta();
			String enchantmentStats = Data.decodeItemData(runeMeta.getDisplayName());
			String[] stats = enchantmentStats.split("'");
			String enchantmentName = stats[0];
			int enchantLevel = Integer.valueOf(stats[1]);
			value.put("level", enchantLevel);
			value.put(enchantmentName, 10);
			int enchantSucceed = Integer.valueOf(stats[2]);
			value.put("success", enchantSucceed);
			int enchantDestroy = Integer.valueOf(stats[3]);
			value.put("destroy", enchantDestroy);
		}
		return value;
	}
	
	public static boolean isRune(ItemStack rune){
		if(rune.hasItemMeta()){
			if(rune.getType().equals(Material.NETHER_STAR)){
				return true;
			}
			return false;
		}
		return false;
	}
	
	@EventHandler
	public void onMapRightClick(PlayerInteractEvent event){
		if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			if(event.getItem() != null){
				if(event.getItem().getType().equals(Material.MAP) || event.getItem().getType().equals(Material.EMPTY_MAP)){
					if(isProtectionRune(event.getItem())){
						event.setCancelled(true);
						event.getPlayer().updateInventory();
					}
				}
			}
		}
	}
	
	public static ItemStack createProtectionRune(){
		ItemStack rune = new ItemStack(Material.EMPTY_MAP, 1);
		ItemMeta runeMeta = rune.getItemMeta();
		String name = ChatColor.YELLOW + "Protection Rune";
		String hidden = Data.encodeItemData("protection");
		runeMeta.setDisplayName(name + hidden);
		runeMeta.setLore(Arrays.asList(new String[] {
				ChatColor.GRAY + "Prevents an item from being destroyed",
				ChatColor.GRAY + "due to a failed Enchantment Rune.",
				ChatColor.YELLOW + "Place on item to apply."
				}));
		rune.setItemMeta(runeMeta);
		return rune;
	}
	
	public static boolean isProtectionRune(ItemStack rune){
		if(rune.hasItemMeta()){
			ItemMeta runeMeta = rune.getItemMeta();
			if(runeMeta.getDisplayName().contains(ChatColor.YELLOW + "Protection Rune")){
				return true;
			}
			return false;
		}
		return false;
	}
}

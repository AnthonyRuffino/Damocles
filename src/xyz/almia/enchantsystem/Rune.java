package xyz.almia.enchantsystem;

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
import org.bukkit.plugin.Plugin;
import xyz.almia.cardinalsystem.Cardinal;
import xyz.almia.itemsystem.NBTHandler;
import xyz.almia.utils.RomanNumerals;

public class Rune implements Listener{
	
	Plugin plugin = Cardinal.getPlugin();
	xyz.almia.enchantsystem.Enchantment enchantclass = new xyz.almia.enchantsystem.Enchantment();
	
	public Rune(){}
	
	public ItemStack setAmount(ItemStack item, int amount){
		if(amount == 0){
			return new ItemStack(Material.AIR, 1);
		}else{
			item.setAmount(amount);
			return item;
		}
	}
	
	public ItemStack createSlotRune(int slots){
		ItemStack rune = new ItemStack(Material.EYE_OF_ENDER, 1);
		ItemMeta runeMeta = rune.getItemMeta();
		runeMeta.setDisplayName(ChatColor.YELLOW + "Slot Rune");
		runeMeta.setLore(Arrays.asList(new String[] { 
				ChatColor.WHITE + "" + ChatColor.BOLD + "+" + slots + " Slots",
				ChatColor.GRAY + "Place rune on item to apply slots."}));
		rune.setItemMeta(runeMeta);
		rune = new NBTHandler(rune).setIntTag("slots", slots);
		return rune;
	}
	
	public int getSlotsFromRune(ItemStack item){
		if(item.hasItemMeta()){
			if(item.getItemMeta().hasDisplayName()){
				if(item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Slot Rune")){
					return new NBTHandler(item).getIntTag("slots");
				}
			}
		}
		return -1;
	}
	
	public ItemStack createRune(Enchantments enchant, int level, int success, int destroy){
		ItemStack rune = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta runeMeta = rune.getItemMeta();
		runeMeta.setDisplayName(ChatColor.YELLOW + "Rune of " + ChatColor.UNDERLINE + enchantclass.getName(enchant) + ChatColor.RESET + " " + ChatColor.YELLOW + RomanNumerals.intToRoman(level));
		runeMeta.setLore(Arrays.asList(new String[] { ChatColor.GREEN + "" + success + "% Success Rate",
				ChatColor.RED + "" + destroy + "% Destroy Rate On Fail.",
				ChatColor.YELLOW + "Level " + ChatColor.GOLD + "" + level,
				ChatColor.GOLD + "" + enchantclass.getType(enchant).toString() + " Enchantment",
				ChatColor.GRAY + "Place rune on item to enchant."}));
		rune.setItemMeta(runeMeta);
		
		rune = new NBTHandler(rune).setStringTag("enchant", enchant.toString());
		rune = new NBTHandler(rune).setIntTag("level", level);
		rune = new NBTHandler(rune).setIntTag("success", success);
		rune = new NBTHandler(rune).setIntTag("destroy", destroy);
		
		return rune;
	}
	
	public Map<String, Integer> getRune(ItemStack rune){
		Map<String, Integer> value = new HashMap<String, Integer>();
		if(rune.hasItemMeta()){
			
			Enchantments enchant = Enchantments.valueOf(new NBTHandler(rune).getStringTag("enchant"));
			int success = new NBTHandler(rune).getIntTag("success");
			int level = new NBTHandler(rune).getIntTag("level");
			int destroy = new NBTHandler(rune).getIntTag("destroy");
			value.put("level", level);
			value.put(enchant.toString(), 10);
			value.put("success", success);
			value.put("destroy", destroy);
		}
		return value;
	}
	
	public boolean isRune(ItemStack rune){
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
	
	public ItemStack createProtectionRune(){
		ItemStack rune = new ItemStack(Material.EMPTY_MAP, 1);
		ItemMeta runeMeta = rune.getItemMeta();
		runeMeta.setDisplayName(ChatColor.YELLOW + "Protection Rune");
		runeMeta.setLore(Arrays.asList(new String[] {
				ChatColor.GRAY + "Prevents an item from being destroyed",
				ChatColor.GRAY + "due to a failed Enchantment Rune.",
				ChatColor.YELLOW + "Place on item to apply."
				}));
		rune.setItemMeta(runeMeta);
		
		new NBTHandler(rune).setIntTag("protect", 1);
		
		return rune;
	}
	
	public boolean isProtectionRune(ItemStack rune){
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

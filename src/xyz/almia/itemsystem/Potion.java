package xyz.almia.itemsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import xyz.almia.utils.RomanNumerals;

public class Potion {
	
	private ItemStack item;
	
	public Potion(ItemStack item){
		this.item = item;
	}
	
	public ItemStack create(PotionTypes type, int tier){
		setup(type, new Random().nextInt(tier*5)+1, tier);
		return this.item;
	}
	
	public boolean isCreated(){
		ItemMeta im = item.getItemMeta();
		List<String> lore = im.getLore();
		if(lore.contains(ChatColor.GRAY+"Restores")){
			return true;
		}
		return false;
	}
	
	public void setup(PotionTypes type, int amount, int tier){
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.DARK_GRAY+"Potion of "+StringUtils.capitalize(type.toString().toLowerCase())+" "+RomanNumerals.intToRoman(tier));
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY+"Restores "+ChatColor.RED+amount+ChatColor.GRAY+" HitPoints.");
		lore.add(ChatColor.GRAY+"Tier "+RomanNumerals.intToRoman(tier));
		lore.add(ChatColor.YELLOW+"A "+StringUtils.capitalize(type.toString().toLowerCase())+ " potion.");
		im.setLore(lore);
		im.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		item.setItemMeta(im);
		PotionMeta potion = (PotionMeta)item.getItemMeta();
		if(type.equals(PotionTypes.HEALING)){
			potion.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL));
		}
		if(type.equals(PotionTypes.MANA)){
			potion.setBasePotionData(new PotionData(PotionType.SPEED));
		}
		item.setItemMeta(potion);
	}
	
	public int getTier(){
		if(item.hasItemMeta()){
			if(item.getItemMeta().hasLore()){
				String[] slotAmount = null;
				for(String s : item.getItemMeta().getLore()){
					if(ChatColor.stripColor(s).contains("Tier")){
						slotAmount = ChatColor.stripColor(s).split(" ");
					}
				}
				if(slotAmount == null){
					return 0;
				}else{
					return RomanNumerals.romanToInt(slotAmount[1]);
				}
			}else{
				return 0;
			}
		}else{
			return 0;
		}
	}
	
	public void setTier(int tier){
		setup(getPotionType(), getAmount(), tier);
	}
	
	public PotionTypes getPotionType(){
		if(item.hasItemMeta()){
			if(item.getItemMeta().hasLore()){
				String[] slotAmount = null;
				for(String s : item.getItemMeta().getLore()){
					if(ChatColor.stripColor(s).contains("potion.")){
						slotAmount = ChatColor.stripColor(s).split(" ");
					}
				}
				if(slotAmount == null){
					return PotionTypes.NONE;
				}else{
					return PotionTypes.valueOf(ChatColor.stripColor(slotAmount[1]).toUpperCase());
				}
			}else{
				return PotionTypes.NONE;
			}
		}else{
			return PotionTypes.NONE;
		}
	}
	
	public void setPotionType(PotionTypes type){
		setup(type, getAmount(), getTier());
	}
	
	public void setAmount(int amount){
		setup(getPotionType(), amount, getTier());
	}
	
	public int getAmount(){
		if(item.hasItemMeta()){
			if(item.getItemMeta().hasLore()){
				String[] slotAmount = null;
				for(String s : item.getItemMeta().getLore()){
					if(ChatColor.stripColor(s).contains("Restores")){
						slotAmount = ChatColor.stripColor(s).split(" ");
					}
				}
				if(slotAmount == null){
					return 0;
				}else{
					return Integer.valueOf(slotAmount[1]);
				}
			}else{
				return 0;
			}
		}else{
			return 0;
		}
	}
	
}

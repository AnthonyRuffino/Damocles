package xyz.almia.itemsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.almia.utils.RomanNumerals;

public class Weapon{
	
	private ItemStack item;
	
	public Weapon(ItemStack item){
		this.item = item;
	}
	
	public void setup(HashMap<Enchantments, Integer> enchants, int slots, int intel, int str, int hp, int agi ,int damage, int reforges, int weight, int upgrades, boolean isprotected, List<String> flavortext){
		
		ItemMeta im = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add("");
		
		if(!(enchants == null)){
			for(Map.Entry<Enchantments, Integer> entry : enchants.entrySet()){
				lore.add(ChatColor.GRAY+ Enchantments.getName(entry.getKey()) + " " + RomanNumerals.intToRoman(entry.getValue()));
			}
		}
		lore.add(ChatColor.GOLD+""+slots+" Slots");
		lore.add("");
		
		lore.add(ChatColor.GRAY+"Int: +"+ intel);
		lore.add(ChatColor.GRAY+"Str: +"+ str);
		lore.add(ChatColor.GRAY+"Hp: +"+ hp);
		lore.add(ChatColor.GRAY+"Agi: +"+ agi);
		lore.add("");
		lore.add(ChatColor.BLUE+"+"+damage+" Damage");
		lore.add("");
		lore.add(ChatColor.DARK_GRAY+"Weight: "+weight);
		lore.add(ChatColor.DARK_GRAY+"Number of upgrades available: "+ upgrades);
		lore.add(ChatColor.DARK_GRAY+"Number of reforges available: "+ reforges);
		lore.add("");
		
		if(isprotected){
			lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "PROTECTED");
			lore.add("");
		}
		
		if(!(flavortext == null)){
			for(String s : flavortext){
				lore.add(s);
			}
		}
		
		im.setLore(lore);
		im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(im);
		
	}
	
	public int getInt(){
		if(item.hasItemMeta()){
			if(item.getItemMeta().hasLore()){
				String[] amount = null;
				for(String s : item.getItemMeta().getLore()){
					if(ChatColor.stripColor(s).contains("Int")){
						s = s.replace("+", "");
						amount = ChatColor.stripColor(s).split(" ");
					}
				}
				if(amount == null){
					return 0;
				}else{
					return Integer.valueOf(amount[1]);
				}
			}else{
				return 0;
			}
		}else{
			return 0;
		}
	}
	
	public int getStr(){
		if(item.hasItemMeta()){
			if(item.getItemMeta().hasLore()){
				String[] amount = null;
				for(String s : item.getItemMeta().getLore()){
					if(ChatColor.stripColor(s).contains("Str")){
						s = s.replace("+", "");
						amount = ChatColor.stripColor(s).split(" ");
					}
				}
				if(amount == null){
					return 0;
				}else{
					return Integer.valueOf(amount[1]);
				}
			}else{
				return 0;
			}
		}else{
			return 0;
		}
	}
	
	public int getHp(){
		if(item.hasItemMeta()){
			if(item.getItemMeta().hasLore()){
				String[] amount = null;
				for(String s : item.getItemMeta().getLore()){
					if(ChatColor.stripColor(s).contains("Hp")){
						s = s.replace("+", "");
						amount = ChatColor.stripColor(s).split(" ");
					}
				}
				if(amount == null){
					return 0;
				}else{
					return Integer.valueOf(amount[1]);
				}
			}else{
				return 0;
			}
		}else{
			return 0;
		}
	}
	
	public int getAgi(){
		if(item.hasItemMeta()){
			if(item.getItemMeta().hasLore()){
				String[] amount = null;
				for(String s : item.getItemMeta().getLore()){
					if(ChatColor.stripColor(s).contains("Agi")){
						s = s.replace("+", "");
						amount = ChatColor.stripColor(s).split(" ");
					}
				}
				if(amount == null){
					return 0;
				}else{
					return Integer.valueOf(amount[1]);
				}
			}else{
				return 0;
			}
		}else{
			return 0;
		}
	}
	
	public int getUpgrades(){
		if(item.hasItemMeta()){
			if(item.getItemMeta().hasLore()){
				String[] amount = null;
				for(String s : item.getItemMeta().getLore()){
					if(ChatColor.stripColor(s).contains("upgrades")){
						amount = ChatColor.stripColor(s).split(" ");
					}
				}
				if(amount == null){
					return 0;
				}else{
					return Integer.valueOf(amount[4]);
				}
			}else{
				return 0;
			}
		}else{
			return 0;
		}
	}
	
	public void setInt(int amount){
		setup(getEnchantsAndLevel(), getSlots(), amount, getStr(), getHp(), getAgi(), getDamage(), getReforges(), getWeight(), getUpgrades(), isProtected(), getFlavorText());
	}
	
	public void setStr(int amount){
		setup(getEnchantsAndLevel(), getSlots(), getInt(), amount, getHp(), getAgi(), getDamage(), getReforges(), getWeight(), getUpgrades(), isProtected(), getFlavorText());
	}
	
	public void setHp(int amount){
		setup(getEnchantsAndLevel(), getSlots(), getInt(), getStr(), amount, getAgi(), getDamage(), getReforges(), getWeight(), getUpgrades(), isProtected(), getFlavorText());
	}
	
	public void setAgi(int amount){
		setup(getEnchantsAndLevel(), getSlots(), getInt(), getStr(), getHp(), amount, getDamage(), getReforges(), getWeight(), getUpgrades(), isProtected(), getFlavorText());
	}
	
	public void setUpgrades(int amount){
		setup(getEnchantsAndLevel(), getSlots(), getInt(), getStr(), getHp(), getAgi(), getDamage(), getReforges(), getWeight(), amount, isProtected(), getFlavorText());
	}
	
	public ItemStack getItemStack(){
		return item;
	}
	
	public void addEnchant(Enchantments enchant, int level){
		HashMap<Enchantments, Integer> enchantments = getEnchantsAndLevel();
		if(enchantments == null)
			enchantments = new HashMap<Enchantments, Integer>();
		enchantments.put(enchant, level);
		setup(enchantments, getSlots(), getInt(), getStr(), getHp(), getAgi(), getDamage(), getReforges(), getWeight(), getUpgrades(), isProtected(), getFlavorText());
	}
	
	public void removeEnchant(Enchantments enchant){
		HashMap<Enchantments, Integer> enchantments = getEnchantsAndLevel();
		enchantments.remove(enchant);
		setup(enchantments, getSlots(), getInt(), getStr(), getHp(), getAgi(), getDamage(), getReforges(), getWeight(), getUpgrades(), isProtected(), getFlavorText());
	}
	
	public List<String> getFlavorText(){
		if(item.hasItemMeta()){
			if(item.getItemMeta().hasLore()){
				
				int pos1 = 0;
				int pos2 = item.getItemMeta().getLore().size() - 1;
				
				for(String s : item.getItemMeta().getLore()){
					
					if(isProtected()){
						if(s.equals(ChatColor.WHITE+""+ChatColor.BOLD+"PROTECTED")){
							pos1 = item.getItemMeta().getLore().indexOf(s) + 2;
							break;
						}
					}else{
						if(s.contains("reforges")){
							pos1 = item.getItemMeta().getLore().indexOf(s) + 2;
							break;
						}
					}
					
				}
				
				List<String> flavortext = new ArrayList<String>();
				for(int i = pos1; i<=pos2; i++){
					flavortext.add(item.getItemMeta().getLore().get(i));
				}
				
				return flavortext;
			}
		}
		return null;
	}

	public void setFlavorText(List<String> flavortext){
		setup(getEnchantsAndLevel(), getSlots(), getInt(), getStr(), getHp(), getAgi(), getDamage(), getReforges(), getWeight(), getUpgrades(), isProtected(), flavortext);
	}
	
	public int getWeight(){
		if(item.hasItemMeta()){
			if(item.getItemMeta().hasLore()){
				String[] slotAmount = null;
				for(String s : item.getItemMeta().getLore()){
					if(ChatColor.stripColor(s).contains("Weight")){
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

	public void setWeight(int weight){
		setup(getEnchantsAndLevel(), getSlots(), getInt(), getStr(), getHp(), getAgi(), getDamage(), getReforges(), weight, getUpgrades(), isProtected(), getFlavorText());
	}
	
	public void setReforges(int reforges){
		setup(getEnchantsAndLevel(), getSlots(), getInt(), getStr(), getHp(), getAgi(), getDamage(), reforges, getWeight(), getUpgrades(), isProtected(), getFlavorText());
	}
	
	public int getReforges(){
		if(item.hasItemMeta()){
			if(item.getItemMeta().hasLore()){
				String[] slotAmount = null;
				for(String s : item.getItemMeta().getLore()){
					if(ChatColor.stripColor(s).contains("reforges")){
						slotAmount = ChatColor.stripColor(s).split(" ");
					}
				}
				if(slotAmount == null){
					return 0;
				}else{
					return Integer.valueOf(slotAmount[4]);
				}
			}else{
				return 0;
			}
		}else{
			return 0;
		}
	}
	
	public void setDamage(int damage){
		setup(getEnchantsAndLevel(), getSlots(), getInt(), getStr(), getHp(), getAgi(), damage, getReforges(), getWeight(), getUpgrades(), isProtected(), getFlavorText());
	}
	
	public int getDamage(){
		if(item.hasItemMeta()){
			if(item.getItemMeta().hasLore()){
				String[] slotAmount = null;
				for(String s : item.getItemMeta().getLore()){
					if(ChatColor.stripColor(s).contains("Damage")){
						s = s.replace("+", "");
						slotAmount = ChatColor.stripColor(s).split(" ");
					}
				}
				if(slotAmount == null){
					return 1;
				}else{
					return Integer.valueOf(slotAmount[0]);
				}
			}else{
				return 1;
			}
		}else{
			return 1;
		}
	}
	
	public void setSlots(int slots){
		setup(getEnchantsAndLevel(), slots, getInt(), getStr(), getHp(), getAgi(), getDamage(), getReforges(), getWeight(), getUpgrades(), isProtected(), getFlavorText());
	}
	
	public int getSlots(){
		if(item.hasItemMeta()){
			if(item.getItemMeta().hasLore()){
				String[] slotAmount = null;
				for(String s : item.getItemMeta().getLore()){
					if(ChatColor.stripColor(s).contains("Slots")){
						slotAmount = ChatColor.stripColor(s).split(" ");
					}
				}
				if(slotAmount == null){
					return -1;
				}else{
					return Integer.valueOf(slotAmount[0]);
				}
			}else{
				return -1;
			}
		}else{
			return -1;
		}
	}
	
	public void unProtect(){
		setup(getEnchantsAndLevel(), getSlots(), getInt(), getStr(), getHp(), getAgi(), getDamage(), getReforges(), getWeight(), getUpgrades(), false, getFlavorText());
	}
	
	public void protect(){
		setup(getEnchantsAndLevel(), getSlots(), getInt(), getStr(), getHp(), getAgi(), getDamage(), getReforges(), getWeight(), getUpgrades(), true, getFlavorText());
	}
	
	public boolean isProtected(){
		if(item.hasItemMeta()){
			if(item.getItemMeta().hasLore()){
				if(item.getItemMeta().getLore().contains("PROTECTED")){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public boolean isItemSet(){
		if(item.hasItemMeta()){
			return true;
		}else{
			return false;
		}
	}
	
	public HashMap<Enchantments, Integer> getEnchantsAndLevel(){
		HashMap<Enchantments, Integer> enchants = new HashMap<Enchantments, Integer>();
		
		if(item.hasItemMeta()){
			if(item.getItemMeta().hasLore()){
				
				List<String> lore = item.getItemMeta().getLore();
				for(String s : lore){
					for(Enchantments enchant : Enchantments.values()){
						String enchantString = ChatColor.stripColor(s);
						if(enchantString.contains(Enchantments.getName(enchant))){
							String[] enchantAndDamage = null;
							enchantAndDamage = s.split(" ");
							int value = Enchantments.getValue(enchant);
							
							int level = RomanNumerals.romanToInt(enchantAndDamage[value]);
							
							enchants.put(enchant, level);
							
						}
					}
				}
				
				return enchants;
				
			}
		}
		
		return null;
	}
	
	public List<Enchantments> getEnchants(){
		List<Enchantments> enchants = new ArrayList<Enchantments>();
		if(item.hasItemMeta()){
			if(item.getItemMeta().hasLore()){
				List<String> lore = item.getItemMeta().getLore();
				for(String s : lore){
					for(Enchantments enchant : Enchantments.values()){
						String enchantString = ChatColor.stripColor(s);
						if(enchantString.toLowerCase().contains(enchant.name().toLowerCase())){
							enchants.add(enchant);
						}
					}
				}
				return enchants;
			}else{
				return null;
			}
		}
		return null;
	}
	
}

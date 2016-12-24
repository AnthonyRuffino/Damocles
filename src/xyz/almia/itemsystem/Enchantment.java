package xyz.almia.itemsystem;

import org.apache.commons.lang.WordUtils;
import org.bukkit.plugin.Plugin;

import xyz.almia.cardinalsystem.Cardinal;

public class Enchantment {
	
	private Cardinal cardinal = new Cardinal();
	Plugin plugin = cardinal.getPlugin();
	
	public Enchantment(){}
	
	public int getMaxLevel(Enchantments enchant){
		return plugin.getConfig().getInt("enchant."+enchant.toString().toLowerCase());
	}
	
	public int getValue(Enchantments enchant){
		switch(enchant){
		case AEGIS:
			return 1;
		case ASSASSIN:
			return 1;
		case BAT_VISION:
			return 2;
		case BLOODTHIRST:
			return 1;
		case DEMON_SIPHON:
			return 2;
		case EYEPATCH:
			return 1;
		case FLAME:
			return 1;
		case HOLY_SMITE:
			return 2;
		case JUMP:
			return 1;
		case LIFESTEAL:
			return 1;
		case PETRIFY:
			return 1;
		case PROTECTION:
			return 1;
		case SHARPENED:
			return 1;
		case SNARE:
			return 1;
		case SOULSHOT:
			return 1;
		case SPEED:
			return 1;
		case SWIPE:
			return 1;
		case VOLLEY:
			return 1;
		case WILD_MARK:
			return 2;
		}
		return 0;
	}
	
	public String getName(Enchantments enchant){
		String s = enchant.toString().toLowerCase();
		s = s.replace("_", " ");
		s = WordUtils.capitalizeFully(s);
		return s;
	}
	
	public EnchantTypes getType(Enchantments enchant){
		switch(enchant){
		case PROTECTION:
			return EnchantTypes.ARMOR;
		case SHARPENED:
			return EnchantTypes.SWORD;
		case FLAME:
			return EnchantTypes.SWORD;
		case ASSASSIN:
			return EnchantTypes.SWORD;
		case LIFESTEAL:
			return EnchantTypes.SWORD;
		case JUMP:
			return EnchantTypes.BOOTS;
		case SPEED:
			return EnchantTypes.BOOTS;
		case SOULSHOT:
			return EnchantTypes.BOW;
		case BAT_VISION:
			return EnchantTypes.HELMET;
		case VOLLEY:
			return EnchantTypes.BOW;
		case SNARE:
			return EnchantTypes.BOW;
		case AEGIS:
			return EnchantTypes.SWORD;
		case WILD_MARK:
			return EnchantTypes.BOW;
		case HOLY_SMITE:
			return EnchantTypes.BOW;
		case DEMON_SIPHON:
			return EnchantTypes.SWORD;
		case BLOODTHIRST:
			return EnchantTypes.SWORD;
		case PETRIFY:
			return EnchantTypes.SWORD;
		case EYEPATCH:
			return EnchantTypes.HELMET;
		case SWIPE:
			return EnchantTypes.SWORD;
		default:
			return EnchantTypes.NONE;
		}
	}
	
}

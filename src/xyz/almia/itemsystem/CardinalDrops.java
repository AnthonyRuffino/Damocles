package xyz.almia.itemsystem;

import java.util.Arrays;
import java.util.HashMap;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;
import xyz.almia.enchantsystem.Enchantments;

public class CardinalDrops {
	
	public CardinalDrops(){}
	
	public ItemStack getItem(Items drop){
		ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta itemmeta = item.getItemMeta();
		HashMap<Enchantments, Integer> enchants = new HashMap<Enchantments, Integer>();
		switch(drop){
		case BAT:
			itemmeta.setDisplayName(ChatColor.DARK_GRAY+"Baseball Bat");
			item.setItemMeta(itemmeta);
			Weapon bat = new Weapon(item);
			bat.setup(enchants, 0, 0, 0, 0, 0, 3, 0, 8, 0, false, 32, 32, Arrays.asList("A plain baseball bat."));
			item = bat.getItemStack();
			item.setDurability((short)3);
			return item;
		case BRONZE_SWORD:
			itemmeta.setDisplayName(ChatColor.DARK_GRAY+"Bronze Sword");
			item.setItemMeta(itemmeta);
			Weapon bsword = new Weapon(item);
			bsword.setup(enchants, 0, 0, 0, 0, 0, 5, 0, 20, 0, false, 37, 39, Arrays.asList("A weak sword made of bronze."));
			item = bsword.getItemStack();
			item.setDurability((short)7);
			return item;
		case EXCALIBUR:
			itemmeta.setDisplayName(ChatColor.GOLD+"Excalibur");
			item.setItemMeta(itemmeta);
			Weapon excalibur = new Weapon(item);
			excalibur.setup(enchants, 3, 0, 0, 0, 0, 12, 0, 7, 3, false, 300, 300, Arrays.asList("The strongest sword ever forged."));
			item = excalibur.getItemStack();
			item.setDurability((short)1);
			return item;
		case KENDO_SWORD:
			itemmeta.setDisplayName(ChatColor.DARK_GRAY+"Kendo Sword");
			item.setItemMeta(itemmeta);
			Weapon kendo = new Weapon(item);
			kendo.setup(enchants, 0, 0, 0, 0, 0, 3, 0, 8, 0, false, 32, 32, Arrays.asList("A wooden sword used for kendo."));
			item = kendo.getItemStack();
			item.setDurability((short)4);
			return item;
		case MURAMASA:
			itemmeta.setDisplayName(ChatColor.RED+"Muramasa");
			item.setItemMeta(itemmeta);
			Weapon muramasa = new Weapon(item);
			muramasa.setup(enchants, 3, 0, 0, 0, 0, 9, 0, 12, 3, false, 256, 256, Arrays.asList("Sword said to poison everything it touches."));
			item = muramasa.getItemStack();
			item.setDurability((short)5);
			return item;
		case PLATINUM_SCIMITAR:
			itemmeta.setDisplayName(ChatColor.AQUA+"Platinum Scimitar");
			item.setItemMeta(itemmeta);
			Weapon pscimitar = new Weapon(item);
			pscimitar.setup(enchants, 2, 0, 0, 0, 0, 8, 0, 16, 0, false, 128, 128, Arrays.asList("A scimitar made of platinum."));
			item = pscimitar.getItemStack();
			item.setDurability((short)6);
			return item;
		case RORAN:
			itemmeta.setDisplayName(ChatColor.GRAY+"Roran");
			item.setItemMeta(itemmeta);
			Weapon roran = new Weapon(item);
			roran.setup(enchants, 1, 0, 0, 0, 0, 10, 0, 13, 0, false, 143, 143, Arrays.asList("A sturdy Hammer."));
			item = roran.getItemStack();
			item.setDurability((short)2);
			return item;
		case STEEL_LONG_SWORD:
			itemmeta.setDisplayName(ChatColor.GRAY+"Steel Long Sword");
			item.setItemMeta(itemmeta);
			Weapon slsword = new Weapon(item);
			slsword.setup(enchants, 4, 0, 0, 0, 0, 12, 0, 20, 0, false, 86, 89, Arrays.asList("A long sword made of steel."));
			item = slsword.getItemStack();
			item.setDurability((short)9);
			return item;
		case STEEL_RAPIER:
			itemmeta.setDisplayName(ChatColor.GRAY+"Steel Rapier");
			item.setItemMeta(itemmeta);
			Weapon srapier = new Weapon(item);
			srapier.setup(enchants, 4, 0, 0, 0, 0, 11, 0, 10, 0, false, 37, 39, Arrays.asList("A rapier made of steel."));
			item = srapier.getItemStack();
			item.setDurability((short)9);
			return item;
		case STEEL_SHORT_SWORD:
			itemmeta.setDisplayName(ChatColor.GRAY+"Steel Short Sword");
			item.setItemMeta(itemmeta);
			Weapon shsword = new Weapon(item);
			shsword.setup(enchants, 3, 0, 0, 0, 0, 10, 0, 17, 0, false, 86, 89, Arrays.asList("A short sword made of steel."));
			item = shsword.getItemStack();
			item.setDurability((short)8);
			return item;
		}
		return null;
	}
	
}

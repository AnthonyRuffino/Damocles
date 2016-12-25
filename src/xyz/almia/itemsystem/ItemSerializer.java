package xyz.almia.itemsystem;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import net.minecraft.server.v1_11_R1.NBTTagByte;
import net.minecraft.server.v1_11_R1.NBTTagCompound;
import xyz.almia.enchantsystem.Enchantments;
import xyz.almia.utils.ConfigManager;

public class ItemSerializer {
	
	public static ItemStack deserializeItem(Items item){
		ConfigManager.load("items.yml");
		FileConfiguration file = ConfigManager.get("items.yml");
		ItemStack itemstack = new ItemStack(Material.valueOf(file.getString(item.toString()+".material")));
		ItemMeta itemmeta = itemstack.getItemMeta();
		itemmeta.setDisplayName(file.getString(item.toString()+".name"));
		itemmeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		itemmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		itemstack.setItemMeta(itemmeta);
		Weapon ritem = new Weapon(itemstack);
		
		HashMap<Enchantments, Integer> enchantsAndLevel = new HashMap<Enchantments, Integer>();
		
		for(Enchantments enchant : Enchantments.values()){
			try{
				int value = file.getInt(item.toString()+".enchants."+enchant.toString());
				if(value > 0){
					enchantsAndLevel.put(enchant, value);
				}
			}catch(NullPointerException e){ }
		}
		
		ritem.setup(enchantsAndLevel, file.getInt(item.toString()+".slots"), file.getInt(item.toString()+".int"), file.getInt(item.toString()+".str"), file.getInt(item.toString()+".hp"), file.getInt(item.toString()+".agi"), file.getInt(item.toString()+".damage"), file.getInt(item.toString()+".reforges"), file.getInt(item.toString()+".weight"), file.getInt(item.toString()+".upgrades"), file.getBoolean(item.toString()+".protected"), file.getInt(item.toString()+".durability"), file.getInt(item.toString()+".maxdurability"), file.getStringList(item.toString()+".flavortext"));
		
        net.minecraft.server.v1_11_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemstack);
        NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
        compound.set("Unbreakable", new NBTTagByte((byte) 1));
        nmsStack.setTag(compound);
        itemstack = CraftItemStack.asBukkitCopy(nmsStack);
        itemstack.setDurability((short) file.getInt(item.toString()+".id"));
        
		return itemstack;
	}
	
	public static void serializeItem(Items item, ItemStack itemstack){
		Weapon ritem = new Weapon(itemstack);
		ConfigManager.load("items.yml");
		ConfigManager.get("items.yml").set(item.toString()+".name", itemstack.getItemMeta().getDisplayName());
		ConfigManager.get("items.yml").set(item.toString()+".id", 0);
		ConfigManager.get("items.yml").set(item.toString()+".material", itemstack.getType().toString());
		ConfigManager.get("items.yml").set(item.toString()+".enchants", null);
		for (Map.Entry<Enchantments, Integer> entry : ritem.getEnchantsAndLevel().entrySet()) {
		    Enchantments enchant = entry.getKey();
		    int value = entry.getValue();
		    ConfigManager.get("items.yml").set(item.toString()+".enchants."+enchant.toString(), value);
		}
		ConfigManager.get("items.yml").set(item.toString()+".slots", ritem.getSlots());
		ConfigManager.get("items.yml").set(item.toString()+".int", ritem.getInt());
		ConfigManager.get("items.yml").set(item.toString()+".str", ritem.getStr());
		ConfigManager.get("items.yml").set(item.toString()+".hp", ritem.getHp());
		ConfigManager.get("items.yml").set(item.toString()+".agi", ritem.getAgi());
		ConfigManager.get("items.yml").set(item.toString()+".damage", ritem.getDamage());
		ConfigManager.get("items.yml").set(item.toString()+".weight", ritem.getWeight());
		ConfigManager.get("items.yml").set(item.toString()+".upgrades", ritem.getUpgrades());
		ConfigManager.get("items.yml").set(item.toString()+".reforges", ritem.getReforges());
		ConfigManager.get("items.yml").set(item.toString()+".protected", ritem.isProtected());
		ConfigManager.get("items.yml").set(item.toString()+".durability", ritem.getDurability());
		ConfigManager.get("items.yml").set(item.toString()+".maxdurability", ritem.getMaxDurability());
		ConfigManager.get("items.yml").set(item.toString()+".flavortext", ritem.getFlavorText());
		ConfigManager.save("items.yml");
	}
	
}

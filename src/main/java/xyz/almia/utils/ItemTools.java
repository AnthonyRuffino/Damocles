package xyz.almia.utils;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
 
public class ItemTools {
 
    /*
    * ItemTools class by FisheyLP
    */
 
    public static void serializeItem(ItemStack[] itemStacks, FileConfiguration config, File file, String path) {
        config.set(path, itemStacks);
        try {
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static ItemStack deserializeItem(FileConfiguration config, String path) {
        return config.getItemStack(path, null);
    }
    public static ItemStack deserializeItem(Plugin plugin, String path) {
        return deserializeItem(plugin.getConfig(), path);
    }
 
    public static void serializeInventory(Inventory inv, FileConfiguration config, File file, String path) {
        for (int i = 0; i < inv.getSize(); i++)
            serializeItem(inv.getContents(), config, file, path+"."+i);
    }
    public static void serializeInventory(Inventory inv, Plugin plugin, String path) {
        serializeInventory(inv, plugin.getConfig(), new File(plugin.getDataFolder(), "config.yml"), path);
    }
 
    public static Inventory deserializeInventory(FileConfiguration config, String path) {
        Inventory inv = Bukkit.createInventory(null, config.getConfigurationSection(path).getKeys(false).size());
        for(String key : config.getConfigurationSection(path).getKeys(false))
            inv.setItem(Integer.parseInt(key), deserializeItem(config, path+"."+key));
        return inv;
    }
    public static Inventory deserializeInventory(Plugin plugin, String path) {
        return deserializeInventory(plugin.getConfig(), path);
    }
 
}

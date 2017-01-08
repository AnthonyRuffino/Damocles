package xyz.almia.utils;

import java.util.Collections;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemFilter {
 
    private Material material = null;
    private int amount = -1;
    private short durability = -1;
    private byte data = -1;
    private String displayName = null;
    private boolean displayNameContains = false;
    private List<String> lore = null;
    private boolean loreContains = false;
 
    public ItemFilter material(Material material) {
        this.material = material;
        return this;
    }
    public ItemFilter amount(int amount) {
        this.amount = amount;
        return this;
    }
    public ItemFilter durability(short durability) {
        this.durability = durability;
        return this;
    }
    public ItemFilter data(byte data) {
        this.data = data;
        return this;
    }
    public ItemFilter displayName(String displayName, boolean contains) {
        this.displayName = displayName;
        this.displayNameContains = contains;
        return this;
    }
    public ItemFilter lore(List<String> lore, boolean contains) {
        this.lore = lore;
        this.loreContains = contains;
        return this;
    }
 
    @SuppressWarnings("deprecation")
    public boolean matches(ItemStack item) {
        boolean matches = true;
 
        Material material = item.getType();
        int amount = item.getAmount();
        short durability = item.getDurability();
        byte data = item.getData().getData();
        String displayName = item.hasItemMeta() ? item.getItemMeta().hasDisplayName() ? item.getItemMeta().getDisplayName() : null : null;
        List<String> lore = item.hasItemMeta() ? item.getItemMeta().hasLore() ? item.getItemMeta().getLore() : null : null;
 
        if (this.material != null && this.material != material) matches = false;
        if (this.amount != -1 && this.amount != amount) matches = false;
        if (this.durability != -1 && this.durability != durability) matches = false;
        if (this.data != -1 && this.data != data) matches = false;
        if (this.lore != null) {
            if (!loreContains && !this.lore.equals(lore)) matches = false;
            if (!Collections.disjoint(this.lore, lore)) matches = false;
        }
        if (this.displayName != null) {
            if (!displayNameContains && !this.displayName.equals(displayName)) matches = false;
            if (!this.displayName.contains(displayName)) matches = false;
        }
        return matches;
    }
}

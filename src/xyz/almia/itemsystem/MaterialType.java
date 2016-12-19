package xyz.almia.itemsystem;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum MaterialType {
	DIAMOND, CHAINMAIL, IRON, GOLD, LEATHER, NONE;
	
	public static MaterialType getMaterialType(ItemStack item){
		ArrayList<Material> diamond = new ArrayList<Material>();
		diamond.add(Material.DIAMOND_AXE);
		diamond.add(Material.DIAMOND_HOE);
		diamond.add(Material.DIAMOND_SWORD);
		diamond.add(Material.DIAMOND_SPADE);
		diamond.add(Material.DIAMOND_PICKAXE);
		diamond.add(Material.DIAMOND_BOOTS);
		diamond.add(Material.DIAMOND_CHESTPLATE);
		diamond.add(Material.DIAMOND_LEGGINGS);
		diamond.add(Material.DIAMOND_HELMET);
		ArrayList<Material> iron = new ArrayList<Material>();
		iron.add(Material.IRON_AXE);
		iron.add(Material.IRON_HOE);
		iron.add(Material.IRON_SWORD);
		iron.add(Material.IRON_SPADE);
		iron.add(Material.IRON_PICKAXE);
		iron.add(Material.IRON_BOOTS);
		iron.add(Material.IRON_CHESTPLATE);
		iron.add(Material.IRON_LEGGINGS);
		iron.add(Material.IRON_HELMET);
		ArrayList<Material> gold = new ArrayList<Material>();
		gold.add(Material.GOLD_AXE);
		gold.add(Material.GOLD_HOE);
		gold.add(Material.GOLD_SWORD);
		gold.add(Material.GOLD_SPADE);
		gold.add(Material.GOLD_PICKAXE);
		gold.add(Material.GOLD_BOOTS);
		gold.add(Material.GOLD_CHESTPLATE);
		gold.add(Material.GOLD_LEGGINGS);
		gold.add(Material.GOLD_HELMET);
		ArrayList<Material> leather = new ArrayList<Material>();
		leather.add(Material.WOOD_AXE);
		leather.add(Material.WOOD_HOE);
		leather.add(Material.WOOD_SWORD);
		leather.add(Material.WOOD_SPADE);
		leather.add(Material.WOOD_PICKAXE);
		leather.add(Material.LEATHER_BOOTS);
		leather.add(Material.LEATHER_CHESTPLATE);
		leather.add(Material.LEATHER_LEGGINGS);
		leather.add(Material.LEATHER_HELMET);
		ArrayList<Material> chainmail = new ArrayList<Material>();
		chainmail.add(Material.STONE_AXE);
		chainmail.add(Material.STONE_HOE);
		chainmail.add(Material.STONE_SWORD);
		chainmail.add(Material.STONE_SPADE);
		chainmail.add(Material.STONE_PICKAXE);
		chainmail.add(Material.CHAINMAIL_BOOTS);
		chainmail.add(Material.CHAINMAIL_CHESTPLATE);
		chainmail.add(Material.CHAINMAIL_LEGGINGS);
		chainmail.add(Material.CHAINMAIL_HELMET);
		
		if(diamond.contains(item.getType())){
			return MaterialType.DIAMOND;
		}else if(iron.contains(item.getType())){
			return MaterialType.IRON;
		}else if(gold.contains(item.getType())){
			return MaterialType.GOLD;
		}else if(leather.contains(item.getType())){
			return  MaterialType.LEATHER;
		}else if(chainmail.contains(item.getType())){
			return MaterialType.CHAINMAIL;
		}else{
			return MaterialType.NONE;
		}
	}
}

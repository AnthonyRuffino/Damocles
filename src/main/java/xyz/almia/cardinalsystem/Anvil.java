package xyz.almia.cardinalsystem;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import xyz.almia.accountsystem.Account;
import xyz.almia.itemsystem.Weapon;

public class Anvil{
	
	int cost;
	ItemStack item;
	Item droppeditem;
	Weapon weapon;
	Block anvil;
	xyz.almia.accountsystem.Character character;
	Player player;
	Inventory inventory;
	
	public Anvil(ItemStack item, Block anvil, Player player, Inventory inventory){
		this.item = item;
		this.player = player;
		this.anvil = anvil;
		this.inventory = inventory;
		this.weapon = new Weapon(this.item);
		this.character = new Account(this.player).getLoadedCharacter();
	}
	
	public Weapon getWeapon(){
		return this.weapon;
	}
	
	public Inventory getInventory(){
		return this.inventory;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public ItemStack getItemStack(){
		return this.item;
	}
	
	public Location getLocation(){
		return this.anvil.getLocation();
	}
	
	public void setShowItem(){
		droppeditem = this.player.getWorld().dropItem(this.getLocation().add(0.5, 1, 0.5), this.getItemStack());
		droppeditem.setVelocity(new Vector(0,0,0));
	}
	
	public void deleteShowItem(){
		droppeditem.remove();
	}
	
	public int getCost(){
		return (weapon.getMaxDurability() - weapon.getDurability())*3;
	}
	
}

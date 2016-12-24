package xyz.almia.utils;

import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import net.minecraft.server.v1_11_R1.NBTTagCompound;

/**
 * NBTHandler v1
 *
 * Easily Create and Manipulate NBTTags.
 *
 * @author Kowagatte
 */

public class NBTHandler {
	
	ItemStack item;
	net.minecraft.server.v1_11_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
	
	public NBTHandler(ItemStack item){
		this.item = item;
	}
	
	public NBTTagCompound getBaseCompound(){
		return (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
	}
	
}

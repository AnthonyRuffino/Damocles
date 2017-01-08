package xyz.almia.itemsystem;

import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import net.minecraft.server.v1_11_R1.NBTBase;
import net.minecraft.server.v1_11_R1.NBTTagCompound;
import net.minecraft.server.v1_11_R1.NBTTagInt;
import net.minecraft.server.v1_11_R1.NBTTagString;

public class NBTHandler {
	
	ItemStack item;
	net.minecraft.server.v1_11_R1.ItemStack nmsStack;
	
	public NBTHandler(ItemStack item){
		this.item = item;
		nmsStack = CraftItemStack.asNMSCopy(item);
	}
	
	public NBTBase getTag(String tag){
        NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
        return compound.get(tag);
	}
	
	public int getIntTag(String tag){
		NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
		return compound.getInt(tag);
	}
	public String getStringTag(String tag){
		NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
		return compound.getString(tag);
	}
	
	public ItemStack setTag(String tag, NBTBase value){
        NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
        compound.set(tag, value);
		nmsStack.setTag(compound);
		return CraftItemStack.asBukkitCopy(nmsStack);
	}
	
	public ItemStack setIntTag(String tag, int value){
        NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
        compound.set(tag, new NBTTagInt(value));
		nmsStack.setTag(compound);
		return CraftItemStack.asBukkitCopy(nmsStack);
	}
	
	public ItemStack setStringTag(String tag, String value){
        NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
        compound.set(tag, new NBTTagString(value));
		nmsStack.setTag(compound);
		return CraftItemStack.asBukkitCopy(nmsStack);
	}
	
	public ItemStack setCompound(NBTTagCompound compound){
		nmsStack.setTag(compound);
		return CraftItemStack.asBukkitCopy(nmsStack);
	}
	
}

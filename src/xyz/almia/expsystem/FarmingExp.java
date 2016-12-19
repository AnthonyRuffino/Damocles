package xyz.almia.expsystem;

import org.bukkit.plugin.Plugin;
import xyz.almia.cardinalsystem.Cardinal;

public class FarmingExp {
	
	static Plugin plugin = Cardinal.getPlugin();
	
	public static int sugarCane(){
		return plugin.getConfig().getInt("Cardinal.professions.farming.sugarcane");
	}
	
	public static int wheat(){
		return plugin.getConfig().getInt("Cardinal.professions.farming.wheat");
	}
	
	public static int carrot(){
		return plugin.getConfig().getInt("Cardinal.professions.farming.carrot");
	}
	
	public static int potato(){
		return plugin.getConfig().getInt("Cardinal.professions.farming.potato");
	}
	
	public static int pumpkin(){
		return plugin.getConfig().getInt("Cardinal.professions.farming.pumpkin");
	}
	
	public static int melon(){
		return plugin.getConfig().getInt("Cardinal.professions.farming.melon");
	}
	
}

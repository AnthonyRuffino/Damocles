package xyz.almia.expsystem;

import org.bukkit.plugin.Plugin;
import xyz.almia.cardinalsystem.Cardinal;

public class CookingExp {
	
	static Plugin plugin = Cardinal.getPlugin();
	
	public static int bacon(){
		return plugin.getConfig().getInt("Cardinal.professions.cooking.bacon");
	}
	
	public static int steak(){
		return plugin.getConfig().getInt("Cardinal.professions.cooking.steak");
	}
	
	public static int rabbit(){
		return plugin.getConfig().getInt("Cardinal.professions.cooking.rabbit");
	}
	
	public static int trout(){
		return plugin.getConfig().getInt("Cardinal.professions.cooking.trout");
	}
	
	public static int mutton(){
		return plugin.getConfig().getInt("Cardinal.professions.cooking.mutton");
	}
	
	public static int potato(){
		return plugin.getConfig().getInt("Cardinal.professions.cooking.potato");
	}
	
}

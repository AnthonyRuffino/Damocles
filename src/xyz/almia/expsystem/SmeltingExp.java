package xyz.almia.expsystem;

import org.bukkit.plugin.Plugin;

import xyz.almia.cardinalsystem.Cardinal;

public class SmeltingExp {
	static Plugin plugin = Cardinal.getPlugin();
	
	public static int iron(){
		return plugin.getConfig().getInt("Cardinal.professions.forging.iron");
	}
	
	public static int coal(){
		return plugin.getConfig().getInt("Cardinal.professions.forging.coal");
	}
	
	public static int gold(){
		return plugin.getConfig().getInt("Cardinal.professions.forging.gold");
	}
	
	public static int diamond(){
		return plugin.getConfig().getInt("Cardinal.professions.forging.diamond");
	}
	
	public static int emerald(){
		return plugin.getConfig().getInt("Cardinal.professions.forging.emerald");
	}
}

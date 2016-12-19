package xyz.almia.expsystem;

import org.bukkit.plugin.Plugin;
import xyz.almia.cardinalsystem.Cardinal;

public class MiningExp {
	
	static Plugin plugin = Cardinal.getPlugin();
	
	public static int iron(){
		return plugin.getConfig().getInt("Cardinal.professions.mining.iron");
	}
	
	public static int coal(){
		return plugin.getConfig().getInt("Cardinal.professions.mining.coal");
	}
	
	public static int gold(){
		return plugin.getConfig().getInt("Cardinal.professions.mining.gold");
	}
	
	public static int diamond(){
		return plugin.getConfig().getInt("Cardinal.professions.mining.diamond");
	}
	
	public static int emerald(){
		return plugin.getConfig().getInt("Cardinal.professions.mining.emerald");
	}
	
	public static int stone(){
		return plugin.getConfig().getInt("Cardinal.professions.mining.stone");
	}
	
}

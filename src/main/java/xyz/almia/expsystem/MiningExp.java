package xyz.almia.expsystem;

import org.bukkit.plugin.Plugin;
import xyz.almia.cardinalsystem.Cardinal;

public class MiningExp {
	
	Plugin plugin = Cardinal.getPlugin();
	
	public int iron(){
		return plugin.getConfig().getInt("Cardinal.professions.mining.iron");
	}
	
	public int coal(){
		return plugin.getConfig().getInt("Cardinal.professions.mining.coal");
	}
	
	public int gold(){
		return plugin.getConfig().getInt("Cardinal.professions.mining.gold");
	}
	
	public int diamond(){
		return plugin.getConfig().getInt("Cardinal.professions.mining.diamond");
	}
	
	public int emerald(){
		return plugin.getConfig().getInt("Cardinal.professions.mining.emerald");
	}
	
	public int stone(){
		return plugin.getConfig().getInt("Cardinal.professions.mining.stone");
	}
	
}

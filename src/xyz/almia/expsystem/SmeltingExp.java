package xyz.almia.expsystem;

import org.bukkit.plugin.Plugin;

import xyz.almia.cardinalsystem.Cardinal;

public class SmeltingExp {
	private Cardinal cardinal = new Cardinal();
	Plugin plugin = cardinal.getPlugin();
	
	public SmeltingExp() {}
	
	public int iron(){
		return plugin.getConfig().getInt("Cardinal.professions.forging.iron");
	}
	
	public int coal(){
		return plugin.getConfig().getInt("Cardinal.professions.forging.coal");
	}
	
	public int gold(){
		return plugin.getConfig().getInt("Cardinal.professions.forging.gold");
	}
	
	public int diamond(){
		return plugin.getConfig().getInt("Cardinal.professions.forging.diamond");
	}
	
	public int emerald(){
		return plugin.getConfig().getInt("Cardinal.professions.forging.emerald");
	}
}

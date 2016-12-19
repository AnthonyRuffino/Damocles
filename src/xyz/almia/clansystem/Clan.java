package xyz.almia.clansystem;

import java.util.List;
import org.bukkit.plugin.Plugin;
import xyz.almia.cardinalsystem.Cardinal;

public class Clan {
	
	private Clans clan;
	private Plugin plugin = Cardinal.getPlugin();
	
	public Clan(Clans clan){
		this.clan = clan;
	}
	
	public List<String> getClansmen(){
		return plugin.getConfig().getStringList("Kings."+clan.toString().toLowerCase()+".clansmen");
	}
	
	public void addClansmen(String username){
		List<String> clansmen = plugin.getConfig().getStringList("Kings."+clan.toString().toLowerCase()+".clansmen");
		clansmen.add(username);
		plugin.getConfig().set("Kings."+clan.toString().toLowerCase()+".clansmen", clansmen);
		plugin.saveConfig();
	}
	
	public void removeClansmen(String uuid){
		List<String> clansmen = plugin.getConfig().getStringList("Kings."+clan.toString().toLowerCase()+".clansmen");
		clansmen.remove(uuid);
		plugin.getConfig().set("Kings."+clan.toString().toLowerCase()+".clansmen", clansmen);
		plugin.saveConfig();
	}
	
	public String getKing(){
		if(clan.equals(Clans.EXILED))
			return null;
		if(clan.equals(Clans.UNCLANNED))
			return null;
		return plugin.getConfig().getString("Kings."+clan.toString().toLowerCase()+".king");
	}
	
	public String getKingName(){
		String name = plugin.getConfig().getString("Kings."+clan.toString().toLowerCase()+".king");
		return name;
	}
	
	public void setKing(String name){
		if(clan.equals(Clans.EXILED))
			return;
		if(clan.equals(Clans.UNCLANNED))
			return;
		plugin.getConfig().set("Kings."+clan.toString().toLowerCase()+".king", name);
		plugin.saveConfig();
	}
	
	public boolean isAKing(){
		if(plugin.getConfig().getString("Kings."+clan.toString().toLowerCase()+".king").equalsIgnoreCase("unknown")){
			return false;
		}else{
			return true;
		}
	}
	
	public List<String> getRejected(){
		if(clan.equals(Clans.EXILED))
			return null;
		if(clan.equals(Clans.UNCLANNED))
			return null;
		return plugin.getConfig().getStringList("Kings."+clan.toString().toLowerCase()+".rejected");
	}
	
	public void addRejected(String name){
		List<String> rejected = plugin.getConfig().getStringList("Kings."+clan.toString().toLowerCase()+".rejected");
		rejected.add(name);
		plugin.getConfig().set("Kings."+clan.toString().toLowerCase()+".rejected", rejected);
		plugin.saveConfig();
	}
	
	public void removeRejected(String name){
		List<String> rejected = plugin.getConfig().getStringList("Kings."+clan.toString().toLowerCase()+".rejected");
		rejected.remove(name);
		plugin.getConfig().set("Kings."+clan.toString().toLowerCase()+".rejected", rejected);
		plugin.saveConfig();
	}
	
	public String getProposed(){
		if(clan.equals(Clans.EXILED))
			return null;
		if(clan.equals(Clans.UNCLANNED))
			return null;
		return plugin.getConfig().getString("Kings."+clan.toString().toLowerCase()+".proposed");
	}
	
	public void setProposed(String name){
		if(clan.equals(Clans.EXILED))
			return;
		if(clan.equals(Clans.UNCLANNED))
			return;
		plugin.getConfig().set("Kings."+clan.toString().toLowerCase()+".proposed", name);
		plugin.saveConfig();
	}
	
	public boolean isAProposed(){
		if(plugin.getConfig().getString("Kings."+clan.toString().toLowerCase()+".proposed").equalsIgnoreCase("unknown")){
			return false;
		}else{
			return true;
		}
	}
	
}

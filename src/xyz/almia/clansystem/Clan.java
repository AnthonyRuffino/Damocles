package xyz.almia.clansystem;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.plugin.Plugin;

import xyz.almia.accountsystem.PlayerSetup;
import xyz.almia.cardinalsystem.Cardinal;

public class Clan {
	
	private Clans clan;
	private Cardinal cardinal = new Cardinal();
	Plugin plugin = cardinal.getPlugin();
	
	public Clan(Clans clan){
		this.clan = clan;
	}
	
	public List<xyz.almia.accountsystem.Character> getClansmen(){
		List<xyz.almia.accountsystem.Character> characters = new ArrayList<xyz.almia.accountsystem.Character>();
		for(String name : plugin.getConfig().getStringList("Kings."+clan.toString().toLowerCase()+".clansmen")){
			characters.add(new PlayerSetup().getCharacterFromUsername(name));
		}
		return characters;
	}
	
	public void addClansmen(xyz.almia.accountsystem.Character chara){
		List<String> clansmen = plugin.getConfig().getStringList("Kings."+clan.toString().toLowerCase()+".clansmen");
		clansmen.add(chara.getUsername());
		plugin.getConfig().set("Kings."+clan.toString().toLowerCase()+".clansmen", clansmen);
		plugin.saveConfig();
	}
	
	public void removeClansmen(xyz.almia.accountsystem.Character chara){
		List<String> clansmen = plugin.getConfig().getStringList("Kings."+clan.toString().toLowerCase()+".clansmen");
		clansmen.remove(chara.getUsername());
		plugin.getConfig().set("Kings."+clan.toString().toLowerCase()+".clansmen", clansmen);
		plugin.saveConfig();
	}
	
	public xyz.almia.accountsystem.Character getKing(){
		if(clan.equals(Clans.EXILED))
			return null;
		if(clan.equals(Clans.UNCLANNED))
			return null;
		try{
			return new PlayerSetup().getCharacterFromUsername(plugin.getConfig().getString("Kings."+clan.toString().toLowerCase()+".king"));
		}catch(Exception e){
			return null;
		}
	}
	
	public String getKingName(){
		return plugin.getConfig().getString("Kings."+clan.toString().toLowerCase()+".king");
	}
	
	public void setKing(xyz.almia.accountsystem.Character chara){
		if(clan.equals(Clans.EXILED))
			return;
		if(clan.equals(Clans.UNCLANNED))
			return;
		
		if(chara == null){
			plugin.getConfig().set("Kings."+clan.toString().toLowerCase()+".king", "unknown");
			plugin.saveConfig();
			return;
		}
		
		plugin.getConfig().set("Kings."+clan.toString().toLowerCase()+".king", chara.getUsername());
		plugin.saveConfig();
		return;
	}
	
	public boolean isThereAKing(){
		if(plugin.getConfig().getString("Kings."+clan.toString().toLowerCase()+".king").equalsIgnoreCase("unknown")){
			return false;
		}else{
			return true;
		}
	}
	
	public List<xyz.almia.accountsystem.Character> getRejected(){
		if(clan.equals(Clans.EXILED))
			return null;
		if(clan.equals(Clans.UNCLANNED))
			return null;
		
		List<xyz.almia.accountsystem.Character> characters = new ArrayList<xyz.almia.accountsystem.Character>();
		for(String name : plugin.getConfig().getStringList("Kings."+clan.toString().toLowerCase()+".rejected")){
			characters.add(new PlayerSetup().getCharacterFromUsername(name));
		}
		return characters;
	}
	
	public void addRejected(xyz.almia.accountsystem.Character chara){
		List<String> rejected = plugin.getConfig().getStringList("Kings."+clan.toString().toLowerCase()+".rejected");
		rejected.add(chara.getUsername());
		plugin.getConfig().set("Kings."+clan.toString().toLowerCase()+".rejected", rejected);
		plugin.saveConfig();
	}
	
	public void removeRejected(xyz.almia.accountsystem.Character chara){
		List<String> rejected = plugin.getConfig().getStringList("Kings."+clan.toString().toLowerCase()+".rejected");
		rejected.remove(chara.getUsername());
		plugin.getConfig().set("Kings."+clan.toString().toLowerCase()+".rejected", rejected);
		plugin.saveConfig();
	}
	
	public xyz.almia.accountsystem.Character getProposed(){
		if(clan.equals(Clans.EXILED))
			return null;
		if(clan.equals(Clans.UNCLANNED))
			return null;
		try{
			return new PlayerSetup().getCharacterFromUsername(plugin.getConfig().getString("Kings."+clan.toString().toLowerCase()+".proposed"));
		}catch(Exception e){
			return null;
		}
	}
	
	public void setProposed(xyz.almia.accountsystem.Character chara){
		if(clan.equals(Clans.EXILED))
			return;
		if(clan.equals(Clans.UNCLANNED))
			return;
		
		if(chara == null){
			plugin.getConfig().set("Kings."+clan.toString().toLowerCase()+".proposed", "unknown");
			plugin.saveConfig();
			return;
		}
		plugin.getConfig().set("Kings."+clan.toString().toLowerCase()+".proposed", chara.getUsername());
		plugin.saveConfig();
		return;
	}
	
	public boolean isSomeoneProposed(){
		if(plugin.getConfig().getString("Kings."+clan.toString().toLowerCase()+".proposed").equalsIgnoreCase("unknown")){
			return false;
		}else{
			return true;
		}
	}
	
}

package xyz.almia.accountsystem;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import xyz.almia.cardinalsystem.Cardinal;
import xyz.almia.utils.ConfigManager;

public class Account {
	
	private Cardinal cardinal = new Cardinal();
	Plugin plugin = cardinal.getPlugin();
	public Player player;
	FileConfiguration config;
	
	public Account(Player player){
		this.player = player;
		ConfigManager.load(player.getUniqueId()+";acc.yml");
		config = ConfigManager.get(player.getUniqueId()+";acc.yml");
	}
	
	public AccountStatus getStatus(){
		return AccountStatus.valueOf(config.getString("status"));
	}
	
	public void logout(){
		getLoadedCharacter().setRegening(false);
		config.set("status", AccountStatus.LOGGINGIN.toString());
		config.set("lastLoaded", getLoadedCharacterID());
		config.set("loaded", -1);
		ConfigManager.save(player.getUniqueId()+";acc.yml");
	}
	
	public Character loadCharacter(int ID){
		if(ID == -1){
			return null;
		}else if(ID > (getCharacterLimit() - 1)){
			return null;
		}else{
			config.set("status", AccountStatus.LOGGEDIN.toString());
			config.set("loaded", ID);
			ConfigManager.save(player.getUniqueId()+";acc.yml");
			return new Character(player, ID);
		}
	}
	
	public Character getLoadedCharacter(){
		return loadCharacter(getLoadedCharacterID());
	}
	
	public int getLoadedCharacterID(){
		return config.getInt("loaded");
	}
	
	public Character getLastLoadedCharacter(){
		if(getLastLoadedCharacterID() == -1){
			return null;
		}
		return new Character(player, getLastLoadedCharacterID());
	}
	
	public int getLastLoadedCharacterID(){
		return config.getInt("lastLoaded");
	}
	
	public void unLoadCharacter(){
		config.set("lastLoaded", getLoadedCharacterID());
		config.set("loaded", -1);
		ConfigManager.save(player.getUniqueId()+";acc.yml");
	}
	
	public void firstTimeSetup(){
		config.set("status", AccountStatus.LOGGINGIN.toString());
		config.set("accountlimit", 3);
		config.set("loaded", -1);
		config.set("lastLoaded", -1);
		ConfigManager.save(player.getUniqueId()+";acc.yml");
		new Character(player, 0).create();
		new Character(player, 1).create();
		new Character(player, 2).create();
	}
	
	public int getCharacterLimit(){
		return config.getInt("accountlimit");
	}
	
	public void setCharacterLimit(int value){
		config.set("accountlimit", value);
		ConfigManager.save(player.getUniqueId()+";acc.yml");
	}
	
	public Character getCharacterFromID(int ID){
		return new Character(player, ID);
	}
	
}

package xyz.almia.accountsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.almia.cardinalsystem.Cardinal;
import xyz.almia.menu.AccountMenu;
import xyz.almia.utils.ConfigManager;
import xyz.almia.utils.Message;
import xyz.almia.utils.Swears;

public class PlayerSetup implements Listener{
	
	public static Character getCharacterFromUsername(String username){
		if(username.equalsIgnoreCase("UNKNOWN"))
			return null;
		List<Character> characters = getCharacters();
		for(Character character : characters){
			if(character.getUsername().equalsIgnoreCase(username)){
				return character;
			}
		}
		return null;
	}
	
	public static List<Character> getCharacters(){
		ConfigManager.load("players.yml");
		List<String> serchars = ConfigManager.get("players.yml").getStringList("players");
		List<Character> chars = new ArrayList<Character>();
		for(String s : serchars){
			chars.add(deserializeCharacter(s));
		}
		return chars;
	}
	
	public List<String> getCharacterNames(){
		List<Character> chars = getCharacters();
		List<String> names = new ArrayList<String>();
		for(Character chara : chars){
			names.add(chara.getUsername());
		}
		return names;
	}
	
	public static Character deserializeCharacter(String s){
		String[] args = s.split(";");
		Player player = Bukkit.getPlayer(UUID.fromString(args[0]));
		try{
			Character character = new Character(player, Integer.valueOf(args[2]));
			return character;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		Player player = (Player) event.getWhoClicked();
		Account account = new Account(player);
		if(event.getInventory().getName().contains(player.getName())){
			if(event.getCurrentItem() != null){
				
				if(event.getCurrentItem().hasItemMeta()){
					
					if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Character Slot 1")){
						account.loadCharacter(0);
					}
					
					if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Character Slot 2")){
						account.loadCharacter(1);
					}
					
					if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Character Slot 3")){
						account.loadCharacter(2);
					}
					
				}
				
			}
			
		}
	}
	
	public UserReason nameSelection(String name){
		List<String> alreadyexists = getCharacterNames();
		
		for(String s : alreadyexists){
			if(s.equalsIgnoreCase(name)){
				return UserReason.NAMETAKEN;
			}
		}
		
		if(name.contains(" ")){
			return UserReason.SPACE;
		}
		
		if(name.contains("king") || name.contains("damocles")){
			return UserReason.KING;
		}
		
		for(Swears swear : Swears.values()){
			if(name.contains(swear.toString().toLowerCase())){
				return UserReason.PROFANITY;
			}
		}
		
		return UserReason.NONE;
		
	}
	
	
	public void sendNameSelectionProcess(Player player){
		for(int i=0; i < 50;){
			player.sendMessage("");
			i++;
		}
		Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
		Message.sendCenteredMessage(player, ChatColor.BOLD + "Please type what you would like to name your character into chat!");
		Message.sendCenteredMessage(player, ChatColor.YELLOW+ "No profanity, No spaces, or the use of 'king' or 'damocles' in your name.");
		Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		Player player = event.getPlayer();
		try{
			Character fchar = new Account(player).getLoadedCharacter();
			
			if(fchar.getCharacterStatus().equals(CharacterStatus.CHOOSE_USERNAME)){
				event.setCancelled(true);
				
				if(nameSelection(event.getMessage()).equals(UserReason.NONE)){
					
					for(int i=0; i < 50;){
						player.sendMessage("");
						i++;
					}
					
					Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
					Message.sendCenteredMessage(player, ChatColor.BOLD + "Name available!");
					Message.sendCenteredMessage(player, ChatColor.YELLOW+ "Changing username now.");
					Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
					
					fchar.setCharacterStatus(CharacterStatus.NORMAL);
					fchar.setUsername(event.getMessage());
					
				}else{
					
					for(int i=0; i < 50;){
						player.sendMessage("");
						i++;
					}
					
					Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
					Message.sendCenteredMessage(player, ChatColor.BOLD + "Name Unavailable");
					Message.sendCenteredMessage(player, ChatColor.YELLOW+ nameSelection(event.getMessage()).toString() + " Error.");
					Message.sendCenteredMessage(player, ChatColor.YELLOW+ "No profanity, No spaces, or the use of 'king' or 'damocles' in your name.");
					Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
					
				}
				
			}
			
		}catch(NullPointerException exception){
		}
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		Player player = event.getPlayer();
		Account account = new Account(player);
		Character character = account.getLoadedCharacter();
		
		if(account.getStatus().equals(AccountStatus.LOGGINGIN)){
			event.setCancelled(true);
		}else{
			if(character.getCharacterStatus().equals(CharacterStatus.CHOOSE_USERNAME)){
				event.setCancelled(true);
			}
		}
		
	}
	
	@EventHandler
	public void onPlayersFirstJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		
		if(!(player.hasPlayedBefore())){
			
			Account account = new Account(player);
			account.firstTimeSetup();
			Inventory inv = AccountMenu.getAccountMenu(player);
			
			Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
			Message.sendCenteredMessage(player, ChatColor.BOLD + "Welcome " + player.getName() + "!");
			Message.sendCenteredMessage(player, ChatColor.YELLOW+ "Please create a character.");
			Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
			
			new BukkitRunnable(){
				@Override
				public void run() {
					if(account.getStatus().equals(AccountStatus.LOGGINGIN)){
						player.openInventory(inv);
					}
				}
			}.runTaskTimer(Cardinal.getPlugin(), 0, 20);
			
		}else{
			Account account = new Account(player);
			Inventory inv = AccountMenu.getAccountMenu(player);
			new BukkitRunnable(){
				@Override
				public void run() {
					if(account.getStatus().equals(AccountStatus.LOGGINGIN)){
						player.openInventory(inv);
					}
				}
			}.runTaskTimer(Cardinal.getPlugin(), 0, 20);
			Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
			Message.sendCenteredMessage(player, ChatColor.BOLD + "Welcome Back " + player.getName() + "!");
			Message.sendCenteredMessage(player, ChatColor.YELLOW+ "Please select a character.");
			Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
		}
	}
	
	
}

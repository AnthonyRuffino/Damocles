package xyz.almia.selectionsystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import mkremins.fanciful.FancyMessage;
import xyz.almia.accountsystem.Account;
import xyz.almia.accountsystem.AccountStatus;
import xyz.almia.accountsystem.PlayerSetup;
import xyz.almia.cardinalsystem.Cardinal;
import xyz.almia.clansystem.Clan;
import xyz.almia.clansystem.Clans;
import xyz.almia.utils.Message;

public class Selection implements Listener{
	
	
	public static void promoteToKing(){
		new BukkitRunnable(){
			public void run(){
				
				for(Player player : Bukkit.getServer().getOnlinePlayers()){
					Account account = new Account(player);					
					if(account.getStatus().equals(AccountStatus.LOGGEDIN)){
						
						if(account.getLoadedCharacter().getLevel() >= 5){
							
							if(new PlayerSetup().isInClan(account.getLoadedCharacter())){
								
								Clans clan = new PlayerSetup().getClan(account.getLoadedCharacter());
								
								Clan clanProfile = new Clan(clan);
								
								xyz.almia.accountsystem.Character character = account.getLoadedCharacter();
								
								if(!(clan.equals(Clans.EXILED))){
								
								if(!(clanProfile.isThereAKing())){
									
									if(!(clanProfile.isSomeoneProposed())){
										
										if(!(clanProfile.getRejected().contains(character))){
											
											clanProfile.setProposed(character);
											
	        								if(clan.equals(Clans.COLORLESS)){
	        									Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
	        									Message.sendCenteredMessage(player, ChatColor.BOLD + "Clan Help");
	        									Message.sendCenteredMessage(player, ChatColor.YELLOW+"You have been randomly selected to become the "+ ChatColor.DARK_GRAY + clan.toString().toLowerCase().substring(0, 1).toUpperCase() + clan.toString().toLowerCase().substring(1) + ChatColor.YELLOW +" King.");
	        									new FancyMessage("Accept")
	        									.command("/clan accept")
	        									.color(ChatColor.GREEN)
	        									.then("     /     ")
	            								.then("Reject")
	            								.command("/clan reject")
	            								.color(ChatColor.RED)
	            								.send(player);
	        									Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
	        								}else{
	        									Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
	        									Message.sendCenteredMessage(player, ChatColor.BOLD + "Clan Help");
	        									Message.sendCenteredMessage(player, ChatColor.YELLOW+"You have been randomly selected to become the "+ ChatColor.valueOf(clan.toString().toUpperCase()) + clan.toString().toLowerCase().substring(0, 1).toUpperCase() + clan.toString().toLowerCase().substring(1) + ChatColor.YELLOW + " King.");
	        									new FancyMessage("Accept")
	        									.command("/clan accept")
	        									.color(ChatColor.GREEN)
	        									.then("     /     ")
	        									.then("Reject")
	        									.command("/clan reject")
	        									.color(ChatColor.RED)
	        									.send(player);
	        									Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
	        								}
											
										}
										
									}
									
								}
								
							}
								
							}
							
						}
						
					}
					
				}
				
			}
		}.runTaskTimer(Cardinal.getPlugin(), 0, 20);
	}
	
}

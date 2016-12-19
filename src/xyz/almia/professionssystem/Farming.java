package xyz.almia.professionssystem;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import xyz.almia.accountsystem.Account;
import xyz.almia.accountsystem.Profession;
import xyz.almia.expsystem.FarmingExp;

public class Farming implements Listener{
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onStockBreak(BlockBreakEvent event){
		Player player = event.getPlayer();
		Block b = event.getBlock();
			
			if(b.getType().equals(Material.POTATO)){
				if(b.getData() == 7){
					new Account(player).getLoadedCharacter().addPExp(Profession.HERBALISM, FarmingExp.potato());
				}
			}
			if(b.getType().equals(Material.CARROT)){
				if(b.getData() == 7){
					new Account(player).getLoadedCharacter().addPExp(Profession.HERBALISM, FarmingExp.carrot());
				}
			}
			if(b.getType().equals(Material.MELON_BLOCK)){
				if(!(b.hasMetadata("natural"))){
					new Account(player).getLoadedCharacter().addPExp(Profession.HERBALISM, FarmingExp.melon());
				}
			}
			if(b.getType().equals(Material.PUMPKIN)){
				if(!(b.hasMetadata("natural"))){
					new Account(player).getLoadedCharacter().addPExp(Profession.HERBALISM, FarmingExp.pumpkin());
				}
			}
			if(b.getType().equals(Material.CROPS)){
				if(b.getData() == 7){
					new Account(player).getLoadedCharacter().addPExp(Profession.HERBALISM, FarmingExp.wheat());
				}
			}
			if(b.getType().equals(Material.SUGAR_CANE_BLOCK)){
				if(!(b.hasMetadata("natural"))){
					new Account(player).getLoadedCharacter().addPExp(Profession.HERBALISM, FarmingExp.sugarCane());
				}
			}
	}
	
}

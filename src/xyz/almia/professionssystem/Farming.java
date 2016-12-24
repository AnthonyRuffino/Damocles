package xyz.almia.professionssystem;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;

import xyz.almia.accountsystem.Account;
import xyz.almia.accountsystem.Profession;
import xyz.almia.cardinalsystem.Cardinal;
import xyz.almia.expsystem.FarmingExp;

public class Farming implements Listener{
	
	private Cardinal cardinal = new Cardinal();
	Plugin plugin = cardinal.getPlugin();
	FarmingExp farmingexp = new FarmingExp();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onStockBreak(BlockBreakEvent event){
		Player player = event.getPlayer();
		Block b = event.getBlock();
			
			if(b.getType().equals(Material.POTATO)){
				if(b.getData() == 7){
					new Account(player).getLoadedCharacter().addPExp(Profession.HERBALISM, farmingexp.potato());
				}
			}
			if(b.getType().equals(Material.CARROT)){
				if(b.getData() == 7){
					new Account(player).getLoadedCharacter().addPExp(Profession.HERBALISM, farmingexp.carrot());
				}
			}
			if(b.getType().equals(Material.MELON_BLOCK)){
				if(!(b.hasMetadata("natural"))){
					new Account(player).getLoadedCharacter().addPExp(Profession.HERBALISM, farmingexp.melon());
				}
			}
			if(b.getType().equals(Material.PUMPKIN)){
				if(!(b.hasMetadata("natural"))){
					new Account(player).getLoadedCharacter().addPExp(Profession.HERBALISM, farmingexp.pumpkin());
				}
			}
			if(b.getType().equals(Material.CROPS)){
				if(b.getData() == 7){
					new Account(player).getLoadedCharacter().addPExp(Profession.HERBALISM, farmingexp.wheat());
				}
			}
			if(b.getType().equals(Material.SUGAR_CANE_BLOCK)){
				if(!(b.hasMetadata("natural"))){
					new Account(player).getLoadedCharacter().addPExp(Profession.HERBALISM, farmingexp.sugarCane());
				}
			}
	}
	
}

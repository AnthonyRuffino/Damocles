package xyz.almia.cardinalsystem;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import mkremins.fanciful.FancyMessage;
import net.milkbowl.vault.economy.Economy;
import xyz.almia.abilities.DarkMagic;
import xyz.almia.abilities.Teleport;
import xyz.almia.accountsystem.Account;
import xyz.almia.accountsystem.EventCanceller;
import xyz.almia.accountsystem.PlayerSetup;
import xyz.almia.accountsystem.Tasks;
import xyz.almia.chatsystem.ChatSystem;
import xyz.almia.clansystem.Clan;
import xyz.almia.clansystem.Clans;
import xyz.almia.damagesystem.DamageSystem;
import xyz.almia.enchantlistener.BatVision;
import xyz.almia.enchantlistener.BloodThirst;
import xyz.almia.enchantlistener.Eyepatch;
import xyz.almia.enchantlistener.Jump;
import xyz.almia.enchantlistener.Speed;
import xyz.almia.enchantsystem.Enchant;
import xyz.almia.itemsystem.Enchantments;
import xyz.almia.itemsystem.ItemHandler;
import xyz.almia.itemsystem.ItemSerializer;
import xyz.almia.itemsystem.Items;
import xyz.almia.itemsystem.Potion;
import xyz.almia.itemsystem.PotionTypes;
import xyz.almia.itemsystem.Rune;
import xyz.almia.lootsystem.Chest;
import xyz.almia.menu.ClanMenu;
import xyz.almia.menu.PlayerMenu;
import xyz.almia.menu.SelectionMenu;
import xyz.almia.professionssystem.Farming;
import xyz.almia.professionssystem.Fishing;
import xyz.almia.professionssystem.Mining;
import xyz.almia.professionssystem.Smelting;
import xyz.almia.soulsystem.SoulSystem;
import xyz.almia.utils.Message;

public class Cardinal extends JavaPlugin implements Listener{
	
    public static Economy econ = null;
	
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
	
	public static Plugin plugin;
	public static Plugin getPlugin() {
		return plugin;
	}
	
	public void registerConfig(){
		getConfig().addDefault("Cardinal", null);
		getConfig().addDefault("Cardinal.professions.mining.coal", 5);
		getConfig().addDefault("Cardinal.professions.mining.iron", 15);
		getConfig().addDefault("Cardinal.professions.mining.gold", 35);
		getConfig().addDefault("Cardinal.professions.mining.emerald", 40);
		getConfig().addDefault("Cardinal.professions.mining.diamond", 25);
		getConfig().addDefault("Cardinal.professions.mining.stone", 1);
		getConfig().addDefault("Cardinal.professions.forging.coal", 5);
		getConfig().addDefault("Cardinal.professions.forging.iron", 15);
		getConfig().addDefault("Cardinal.professions.forging.gold", 35);
		getConfig().addDefault("Cardinal.professions.forging.emerald", 40);
		getConfig().addDefault("Cardinal.professions.forging.diamond", 25);
		getConfig().addDefault("Cardinal.professions.cooking.bacon", 200);
		getConfig().addDefault("Cardinal.professions.cooking.steak", 200);
		getConfig().addDefault("Cardinal.professions.cooking.rabbit", 100);
		getConfig().addDefault("Cardinal.professions.cooking.trout", 150);
		getConfig().addDefault("Cardinal.professions.cooking.mutton", 100);
		getConfig().addDefault("Cardinal.professions.cooking.potato", 50);
		
		getConfig().addDefault("Cardinal.professions.enchanting.sword", 200);
		getConfig().addDefault("Cardinal.professions.enchanting.tool", 125);
		getConfig().addDefault("Cardinal.professions.enchanting.rod", 50);
		getConfig().addDefault("Cardinal.professions.enchanting.bow", 150);
		getConfig().addDefault("Cardinal.professions.enchanting.helmet", 200);
		getConfig().addDefault("Cardinal.professions.enchanting.chestplate", 300);
		getConfig().addDefault("Cardinal.professions.enchanting.boots", 175);
		getConfig().addDefault("Cardinal.professions.enchanting.leggings", 300);
		
		getConfig().addDefault("Cardinal.professions.farming.sugarcane", 50);
		getConfig().addDefault("Cardinal.professions.farming.wheat", 50);
		getConfig().addDefault("Cardinal.professions.farming.carrot", 25);
		getConfig().addDefault("Cardinal.professions.farming.potato", 25);
		getConfig().addDefault("Cardinal.professions.farming.pumpkin", 50);
		getConfig().addDefault("Cardinal.professions.farming.melon", 75);
		
		getConfig().addDefault("Cardinal.enchant.ZOMBIE", 120);
		getConfig().addDefault("Cardinal.enchant.CAVE_SPIDER", 56);
		getConfig().addDefault("Cardinal.enchant.MUSHROOM_COW", 30);
		getConfig().addDefault("Cardinal.enchant.ENDERMAN", 140);
		getConfig().addDefault("Cardinal.enchant.BLAZE", 80);
		getConfig().addDefault("Cardinal.enchant.CREEPER", 110);
		getConfig().addDefault("Cardinal.enchant.ENDERMITE", 30);
		getConfig().addDefault("Cardinal.enchant.GHAST", 60);
		getConfig().addDefault("Cardinal.enchant.GIANT", 140);
		getConfig().addDefault("Cardinal.enchant.GUARDIAN", 72);
		getConfig().addDefault("Cardinal.enchant.IRON_GOLEM", 140);
		getConfig().addDefault("Cardinal.enchant.MAGMA_CUBE", 74);
		getConfig().addDefault("Cardinal.enchant.PIG_ZOMBIE", 110);
		getConfig().addDefault("Cardinal.enchant.SILVERFISH", 48);
		getConfig().addDefault("Cardinal.enchant.SKELETON", 120);
		getConfig().addDefault("Cardinal.enchant.SLIME", 45);
		getConfig().addDefault("Cardinal.enchant.SPIDER", 100);
		getConfig().addDefault("Cardinal.enchant.SQUID", 60);
		getConfig().addDefault("Cardinal.enchant.WITCH", 85);
		getConfig().addDefault("Cardinal.enchant.PIG", 20);
		getConfig().addDefault("Cardinal.enchant.COW", 20);
		getConfig().addDefault("Cardinal.enchant.CHICKEN", 17);
		getConfig().addDefault("Cardinal.enchant.BAT", 17);
		getConfig().addDefault("Cardinal.enchant.HORSE", 43);
		getConfig().addDefault("Cardinal.enchant.PLAYER", 70);
		getConfig().addDefault("Cardinal.enchant.SHEEP", 20);
		getConfig().addDefault("Cardinal.enchant.RABBIT", 17);
		
		String main = "Kings";
		getConfig().addDefault(main, null);

		String white = "Kings.white";
		getConfig().addDefault(white, null);
		String whiteking = "Kings.white.king";
		getConfig().addDefault(whiteking, "unknown");
		String whiteofficer = "Kings.white.officer";
		getConfig().addDefault(whiteofficer, "unkown");

		String gold = "Kings.gold";
		getConfig().addDefault(gold, null);
		String goldking = "Kings.gold.king";
		getConfig().addDefault(goldking, "unknown");
		String goldofficer = "Kings.gold.officer";
		getConfig().addDefault(goldofficer, "unkown");

		String blue = "Kings.blue";
		getConfig().addDefault(blue, null);
		String blueking = "Kings.blue.king";
		getConfig().addDefault(blueking, "unknown");
		String blueofficer = "Kings.blue.officer";
		getConfig().addDefault(blueofficer, "unknown");

		String red = "Kings.red";
		getConfig().addDefault(red, null);
		String redking = "Kings.red.king";
		getConfig().addDefault(redking, "unknown");
		String redofficer = "Kings.red.officer";
		getConfig().addDefault(redofficer, "unknown");

		String black = "Kings.black";
		getConfig().addDefault(black, null);
		String blackking = "Kings.black.king";
		getConfig().addDefault(blackking, "unknown");
		String blackofficer = "Kings.black.officer";
		getConfig().addDefault(blackofficer, "unkown");

		String green = "Kings.green";
		getConfig().addDefault(green, null);
		String greenking = "Kings.green.king";
		getConfig().addDefault(greenking, "unknown");
		String greenofficer = "Kings.green.officer";
		getConfig().addDefault(greenofficer, "unknown");

		String colorless = "Kings.colorless";
		getConfig().addDefault(colorless, null);
		String colorlessking = "Kings.colorless.king";
		getConfig().addDefault(colorlessking, "unknown");
		String colorlessofficer = "Kings.colorless.officer";
		getConfig().addDefault(colorlessofficer, "unknown");


		getConfig().addDefault("Kings.white.clansmen", new ArrayList<String>());
		getConfig().addDefault("Kings.gold.clansmen", new ArrayList<String>());
		getConfig().addDefault("Kings.blue.clansmen", new ArrayList<String>());
		getConfig().addDefault("Kings.red.clansmen", new ArrayList<String>());
		getConfig().addDefault("Kings.black.clansmen", new ArrayList<String>());
		getConfig().addDefault("Kings.green.clansmen", new ArrayList<String>());
		getConfig().addDefault("Kings.colorless.clansmen", new ArrayList<String>());
		getConfig().addDefault("Kings.exiled.clansmen", new ArrayList<String>());
		
		getConfig().addDefault("Kings.white.rejected", new ArrayList<String>());
		getConfig().addDefault("Kings.gold.rejected", new ArrayList<String>());
		getConfig().addDefault("Kings.blue.rejected", new ArrayList<String>());
		getConfig().addDefault("Kings.red.rejected", new ArrayList<String>());
		getConfig().addDefault("Kings.black.rejected", new ArrayList<String>());
		getConfig().addDefault("Kings.green.rejected", new ArrayList<String>());
		getConfig().addDefault("Kings.colorless.rejected", new ArrayList<String>());
		
		getConfig().addDefault("Kings.white.proposed", "unknown");
		getConfig().addDefault("Kings.black.proposed", "unknown");
		getConfig().addDefault("Kings.blue.proposed", "unknown");
		getConfig().addDefault("Kings.red.proposed", "unknown");
		getConfig().addDefault("Kings.gold.proposed", "unknown");
		getConfig().addDefault("Kings.green.proposed", "unknown");
		getConfig().addDefault("Kings.colorless.proposed", "unknown");
		
		getConfig().addDefault("enchant.aegis", 5);
		getConfig().addDefault("enchant.assassin", 3);
		getConfig().addDefault("enchant.bat_vision", 1);
		getConfig().addDefault("enchant.bloodthirsty", 1);
		getConfig().addDefault("enchant.demon_siphon", 4);
		getConfig().addDefault("enchant.eyepatch", 1);
		getConfig().addDefault("enchant.flame", 2);
		getConfig().addDefault("enchant.holy_smite", 5);
		getConfig().addDefault("enchant.jump", 3);
		getConfig().addDefault("enchant.lifesteal", 3);
		getConfig().addDefault("enchant.petrify", 5);
		getConfig().addDefault("enchant.protection", 4);
		getConfig().addDefault("enchant.sharpened", 5);
		getConfig().addDefault("enchant.snare", 4);
		getConfig().addDefault("enchant.soulshot", 1);
		getConfig().addDefault("enchant.speed", 3);
		getConfig().addDefault("enchant.swipe", 5);
		getConfig().addDefault("enchant.volley", 1);
		getConfig().addDefault("enchant.wild_mark", 5);
		
		getConfig().addDefault("players", new ArrayList<String>());
		
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	public void registerEvents(){
		Bukkit.getPluginManager().registerEvents(this, this);
		Bukkit.getPluginManager().registerEvents(new EventCanceller(), this);
		Bukkit.getPluginManager().registerEvents(new ItemHandler(), this);
		Bukkit.getPluginManager().registerEvents(new ClanMenu(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerSetup(), this);
		Bukkit.getPluginManager().registerEvents(new Rune(), this);
		Bukkit.getPluginManager().registerEvents(new BloodThirst(), this);
		Bukkit.getPluginManager().registerEvents(new Smelting(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerMenu(), this);
		Bukkit.getPluginManager().registerEvents(new Mining(), this);
		Bukkit.getPluginManager().registerEvents(new Enchant(), this);
		Bukkit.getPluginManager().registerEvents(new Fishing(), this);
		Bukkit.getPluginManager().registerEvents(new Farming(), this);
		Bukkit.getPluginManager().registerEvents(new DamageSystem(), this);
		Bukkit.getPluginManager().registerEvents(new SoulSystem(), this);
		Bukkit.getPluginManager().registerEvents(new ChatSystem(), this);
		Bukkit.getPluginManager().registerEvents(new Teleport(), this);
		Bukkit.getPluginManager().registerEvents(new DarkMagic(), this);
		Bukkit.getPluginManager().registerEvents(new SelectionMenu(), this);
	}
	
	public void registerEnchants(){
		Eyepatch.checkForEyepatchEnchant();
		BatVision.checkForBatEnchant();
		Speed.checkForSpeedEnchant();
		Jump.checkForJumpEnchant();
		DarkMagic.darkMagic();
	}

	public void onEnable(){
        if (!setupEconomy() ) {
            System.out.println(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
		plugin = this;
		registerConfig();
		registerEvents();
		registerEnchants();
		new Tasks().runTasks();
	}
	
	public void onDisable(){
		plugin = null;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(!(sender instanceof Player)){
			sender.sendMessage("You must be a player to use commands!");
		}
		Player player = (Player)sender;
		Account account = new Account(player);
		xyz.almia.accountsystem.Character character = account.getLoadedCharacter();
		Clans whatClan = character.getClan();
		Clan clan = new Clan(whatClan);
		
		if(cmd.getName().equalsIgnoreCase("logout")){
			account.getLoadedCharacter().setLastLocation(player.getLocation());
			account.logout();
		}
		
		if(cmd.getName().equalsIgnoreCase("help")){
			Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
			Message.sendCenteredMessage(player, ChatColor.BOLD + "Help");
			Message.sendCenteredMessage(player, " ");
			Message.sendCenteredMessage(player, ChatColor.YELLOW+"/Clan "+ChatColor.GOLD+ ": For all the Clan Commands.");
			Message.sendCenteredMessage(player, ChatColor.YELLOW+"/Rune "+ChatColor.GOLD+ ": For all the Enchant Commands.");
			Message.sendCenteredMessage(player, ChatColor.YELLOW+"/Stats "+ChatColor.GOLD+ ": For your players Stats.");
			Message.sendCenteredMessage(player, ChatColor.YELLOW+"/Rank "+ChatColor.GOLD+ ": For all the Rank Commands.");
			Message.sendCenteredMessage(player, ChatColor.YELLOW+"/Bounty "+ChatColor.GOLD+": Not implemented.");
			Message.sendCenteredMessage(player, ChatColor.YELLOW+"/Guild "+ChatColor.GOLD+ ": Not implemented.");
			Message.sendCenteredMessage(player, ChatColor.YELLOW+"/Party "+ChatColor.GOLD+ ": Not implemented.");
			Message.sendCenteredMessage(player, ChatColor.YELLOW+"/Trade "+ChatColor.GOLD+ ": Not implemented.");
			Message.sendCenteredMessage(player, ChatColor.YELLOW+"/Debug "+ChatColor.GOLD+ ": Not implemented.");
			Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("serialize")){
			if(args.length == 1){
				if(args[0].equals(Items.EXCALIBUR.toString().toLowerCase())){
					ItemSerializer.serializeItem(Items.EXCALIBUR, player.getInventory().getItemInMainHand());
					player.sendMessage("serialized!");
					return true;
				}
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("hat")){
			player.getInventory().setHelmet(player.getInventory().getItemInMainHand());
		}
		
		if(cmd.getName().equalsIgnoreCase("cardinal")){

			
			Inventory inv = Bukkit.createInventory(null, 9, "items");
			
			inv.addItem(ItemSerializer.deserializeItem(Items.EXCALIBUR));
			inv.addItem(ItemSerializer.deserializeItem(Items.BAT));
			inv.addItem(ItemSerializer.deserializeItem(Items.KENDO_SWORD));
			inv.addItem(ItemSerializer.deserializeItem(Items.MURAMASA));
			inv.addItem(ItemSerializer.deserializeItem(Items.RORAN));
			inv.addItem(new Potion(new ItemStack(Material.POTION)).create(PotionTypes.HEALING, 1));
			inv.addItem(new Potion(new ItemStack(Material.POTION)).create(PotionTypes.MANA, 1));
			
			player.openInventory(inv);
			
			return true;
		}
		
		/*
		 * http://www.xvideos.com/profiles/just-amber
		 */
		
		if(cmd.getName().equalsIgnoreCase("rank")){
			
			if(player.isOp() || (new Account(player).getLoadedCharacter().getRank().equals(xyz.almia.accountsystem.Rank.GAMEMASTER))){
			
			if(args.length == 0){
				Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
				Message.sendCenteredMessage(player, ChatColor.BOLD + "Rank Help");
				Message.sendCenteredMessage(player, " ");
				Message.sendCenteredMessage(player, ChatColor.YELLOW+"/Rank set <Player> <Rank>");
				Message.sendCenteredMessage(player, ChatColor.YELLOW+"/Rank get <Player>");
				Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
				return true;
			}
			if(args[0].equalsIgnoreCase("help")){
				Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
				Message.sendCenteredMessage(player, ChatColor.BOLD + "Rank Help");
				Message.sendCenteredMessage(player, " ");
				Message.sendCenteredMessage(player, ChatColor.YELLOW+"/Rank set <Player> <Rank>");
				Message.sendCenteredMessage(player, ChatColor.YELLOW+"/Rank get <Player>");
				Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
				return true;
			}
			if(args[0].equalsIgnoreCase("set")){
				if(args.length == 3){
					try{
						xyz.almia.accountsystem.Character chara = PlayerSetup.getCharacterFromUsername(args[1]);
						xyz.almia.accountsystem.Rank rank = xyz.almia.accountsystem.Rank.valueOf(args[2].toUpperCase());
						chara.setRank(rank);
						Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
						Message.sendCenteredMessage(player, ChatColor.BOLD + "Rank");
						Message.sendCenteredMessage(player, ChatColor.YELLOW+"You have set "+args[1]+" to a "+args[2]+"!");
						Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
						return true;
					}catch(Exception e){
						Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
						Message.sendCenteredMessage(player, ChatColor.BOLD + "Rank Help");
						Message.sendCenteredMessage(player, ChatColor.YELLOW+"Invalid Arguments : Unknown Player or Rank");
						Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
						return true;
					}
				}else{
					Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
					Message.sendCenteredMessage(player, ChatColor.BOLD + "Rank Help");
					Message.sendCenteredMessage(player, ChatColor.YELLOW+"Invalid Arguments : /Rank set <Player> <Rank>");
					Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
					return true;
				}
			}
			if(args[0].equalsIgnoreCase("get")){
				if(args.length == 2){
					try{
						xyz.almia.accountsystem.Character target = PlayerSetup.getCharacterFromUsername(args[1]);
						Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
						Message.sendCenteredMessage(player, ChatColor.BOLD + "Rank");
						Message.sendCenteredMessage(player, ChatColor.YELLOW+args[1]+" is a "+ target.getRank()+"!");
						Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
						return true;
					}catch(Exception e){
						Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
						Message.sendCenteredMessage(player, ChatColor.BOLD + "Rank Help");
						Message.sendCenteredMessage(player, ChatColor.YELLOW+"Invalid Arguments : Unknown Player");
						Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
						return true;
					}
				}else{
					Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
					Message.sendCenteredMessage(player, ChatColor.BOLD + "Rank Help");
					Message.sendCenteredMessage(player, ChatColor.YELLOW+"Invalid Arguments : /Rank get <Player>");
					Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
					return true;
				}
			}
			Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
			Message.sendCenteredMessage(player, ChatColor.BOLD + "Rank Help");
			Message.sendCenteredMessage(player, ChatColor.YELLOW+"Unknown Rank command!");
			Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
			return true;
		}else{
			Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
			Message.sendCenteredMessage(player, ChatColor.BOLD + "Rank Help");
			Message.sendCenteredMessage(player, ChatColor.YELLOW+"You must be a GameMaster to use the Rank command!");
			Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
			return true;
		}
		}
		
		if(cmd.getName().equalsIgnoreCase("stats")){
			Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
			Message.sendCenteredMessage(player, ChatColor.BOLD + character.getUsername() + " Stats");
			Message.sendCenteredMessage(player, ChatColor.YELLOW+ "Your current Level is " + ChatColor.GOLD + character.getLevel());
			Message.sendCenteredMessage(player, ChatColor.YELLOW+ "Your current xp is " + ChatColor.GOLD + character.getExp()+ ChatColor.YELLOW+ " / "+ ChatColor.GOLD + (character.getLevel() * 1028));
			Message.sendCenteredMessage(player, ChatColor.YELLOW+ "You currently belong to the "+ character.getClan().toString().toLowerCase().substring(0, 1).toUpperCase() + character.getClan().toString().toLowerCase().substring(1) + " clan.");
			Message.sendCenteredMessage(player, ChatColor.YELLOW+ "your position is " + ChatColor.GRAY + character.getClanRank().toString().toLowerCase().substring(0, 1).toUpperCase() + character.getClanRank().toString().toLowerCase().substring(1));	
			Message.sendCenteredMessage(player, " ");
			Message.sendCenteredMessage(player, ChatColor.BOLD + "No Active Bonus' !");
			Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
			player.openInventory(PlayerMenu.createMenu(player));
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("chest")){
			Chest.createLootChest(player.getLocation(), null);
		}
		
		if(cmd.getName().equalsIgnoreCase("clan")){
			
		      if (args.length == 0){
					Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
					Message.sendCenteredMessage(player, ChatColor.BOLD + "Clan Help");
					Message.sendCenteredMessage(player, ChatColor.YELLOW + "/Clan help");
					Message.sendCenteredMessage(player, ChatColor.YELLOW + "/Clan choose");
					Message.sendCenteredMessage(player, ChatColor.YELLOW + "/Clan info "+ChatColor.GOLD+"[Player]"+ChatColor.YELLOW+" or "+ChatColor.GOLD+"[Clan]");
					//Message.sendCenteredMessage(player, ChatColor.YELLOW + "/Clan join"+ChatColor.GOLD+" [Color]");
					Message.sendCenteredMessage(player, ChatColor.YELLOW + "/Clan promote"+ChatColor.GOLD+" [Player]");
					Message.sendCenteredMessage(player, ChatColor.YELLOW + "/Clan leave");
					Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
		            return true;
		      }
		      
		      if(args[0].equalsIgnoreCase("help")){
					Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
					Message.sendCenteredMessage(player, ChatColor.BOLD + "Clan Help");
					Message.sendCenteredMessage(player, ChatColor.YELLOW + "/Clan help");
					Message.sendCenteredMessage(player, ChatColor.YELLOW + "/Clan choose");
					Message.sendCenteredMessage(player, ChatColor.YELLOW + "/Clan info "+ChatColor.GOLD+"[Player]"+ChatColor.YELLOW+" or "+ChatColor.GOLD+"[Clan]");
					//Message.sendCenteredMessage(player, ChatColor.YELLOW + "/Clan join"+ChatColor.GOLD+" [Color]");
					Message.sendCenteredMessage(player, ChatColor.YELLOW + "/Clan promote"+ChatColor.GOLD+" [Player]");
					Message.sendCenteredMessage(player, ChatColor.YELLOW + "/Clan leave");
					Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
		            return true;
		      }
		      
		      if(args[0].equalsIgnoreCase("leave")){
		    	  if(character.isInClan()){
		    		  
		    		  if(clan.getProposed().getUsername().equalsIgnoreCase(character.getUsername()))
		    			  clan.setProposed(null);
		    		  
		    		  xyz.almia.clansystem.Rank rank = character.getClanRank();
		    		  switch(rank){
					case CLANSMEN:
						clan.removeClansmen(character);
						Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
						Message.sendCenteredMessage(player, ChatColor.BOLD + "Clan Info");
						Message.sendCenteredMessage(player, ChatColor.YELLOW+"You have left "+whatClan.toString()+" Clan!");
						Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
		    			return true;
					case KING:
						clan.setKing(null);
						Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
						Message.sendCenteredMessage(player, ChatColor.BOLD + "Clan Info");
						Message.sendCenteredMessage(player, ChatColor.YELLOW+"You have left "+whatClan.toString()+" Clan!");
						Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
		    			  return true;
					case NONE:
						break;
					default:
						break;
		    		  }
		    	  }else{
						Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
						Message.sendCenteredMessage(player, ChatColor.BOLD + "Clan Info");
						Message.sendCenteredMessage(player, ChatColor.YELLOW+"You are not in a Clan!");
						Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
	    			  return true;
		    	  }
		      }
		      
		      /*
		      if(args[0].equalsIgnoreCase("invite")){
		    	  if(args.length == 2){
			    	  Clans clan = Clan.getManager().getClan(player);
			    	  if(clan == Clans.UNCLANNED){
							Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
							Message.sendCenteredMessage(player, ChatColor.BOLD + "Clan Info");
							Message.sendCenteredMessage(player, ChatColor.YELLOW+"You are not in a Clan!");
							Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
		    			  return true;
			    	  }
			    	  Player target = Bukkit.getPlayer(args[1]);
			    	  if(player == target){
							Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
							Message.sendCenteredMessage(player, ChatColor.BOLD + "Clan Info");
							Message.sendCenteredMessage(player, ChatColor.YELLOW+"You cannot invite yourself to a Clan!");
							Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
			    		  return true;
			    	  }
			    	  boolean canInvite = false;
			    	  if(Clan.getManager().getKing(clan).equalsIgnoreCase(player.getUniqueId()+""))
			    		  canInvite = true;
			    	  if(Clan.getManager().getOfficer(clan).equalsIgnoreCase(player.getUniqueId()+""))
			    		  canInvite = true;
			    	  if(canInvite){
			    		  if(target != null){
			    			  Clan.getManager().addInvitedMember(target, clan);
			    			  return true;
			    		  }else{
								Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
								Message.sendCenteredMessage(player, ChatColor.BOLD + "Clan Info");
								Message.sendCenteredMessage(player, ChatColor.YELLOW+"The player ( "+args[1]+" ) does not exist or is offline!");
								Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
			    			  return true;
			    		  }
			    	  }else{
							Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
							Message.sendCenteredMessage(player, ChatColor.BOLD + "Clan Info");
							Message.sendCenteredMessage(player, ChatColor.YELLOW+"You are not a King or an Officer!");
							Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
			    		  return true;
			    	  }
		    	  }else{
						Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
						Message.sendCenteredMessage(player, ChatColor.BOLD + "Clan Info");
						Message.sendCenteredMessage(player, ChatColor.YELLOW+"Invalid usage, check /sevenkings help for correct command usage!");
						Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
		    		  return true;
		    	  }
		      }
		      */
		      
		      if(args[0].equalsIgnoreCase("choose")){
		    	  
		    	  if(character.getLevel() >= 5){
			    	  if(!(character.isInClan())){
			    		  player.openInventory(SelectionMenu.getInstance().generateSelectionInventory());
			    		  return true;
			    	  }else{
			    		  Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
			    		  Message.sendCenteredMessage(player, ChatColor.BOLD + "Clan Info");
			    		  Message.sendCenteredMessage(player, ChatColor.YELLOW+"ERROR: You are already in a clan!");
			    		  Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
			    		  return true;
			    	  } 
		    	  }else{
		    		  Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
		    		  Message.sendCenteredMessage(player, ChatColor.BOLD + "Game Info");
		    		  Message.sendCenteredMessage(player, ChatColor.YELLOW+"You must be Level 5 to choose a clan!");
		    		  Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
		    		  return true;
		    	  }
		    	  
		      }
		      
		      if(args[0].equalsIgnoreCase("accept")){
		    	  
		    	  if(whatClan.equals(Clans.UNCLANNED))
		    		  return true;
		    	  
		    	  if(whatClan.equals(Clans.EXILED))
		    		  return true;
		    	  
		    	  if(clan.getProposed().getUsername().equalsIgnoreCase(character.getUsername())){
		    		  if(clan.getKing() == null){
		    			  clan.removeClansmen(character);
		    			  clan.setProposed(null);
		    			  clan.setKing(character);
		    			  for(Player target : Bukkit.getOnlinePlayers()){
								if(whatClan.equals(Clans.COLORLESS)){
									Message.sendCenteredMessage(target, ChatColor.GREEN+"----------------------------------------------------");
									Message.sendCenteredMessage(target, ChatColor.BOLD + "Game Info");
									Message.sendCenteredMessage(target, ChatColor.YELLOW+character.getUsername()+" has become the " + ChatColor.DARK_GRAY + whatClan.toString().toLowerCase().substring(0, 1).toUpperCase() + whatClan.toString().toLowerCase().substring(1) + ChatColor.YELLOW + " King!");
									Message.sendCenteredMessage(target, ChatColor.GREEN+"----------------------------------------------------");
								}else{
									Message.sendCenteredMessage(target, ChatColor.GREEN+"----------------------------------------------------");
									Message.sendCenteredMessage(target, ChatColor.BOLD + "Game Info");
									Message.sendCenteredMessage(target, ChatColor.YELLOW+character.getUsername()+" has become the " + ChatColor.valueOf(whatClan.toString().toUpperCase()) + whatClan.toString().toLowerCase().substring(0, 1).toUpperCase() + whatClan.toString().toLowerCase().substring(1) + ChatColor.YELLOW + " King!");
									Message.sendCenteredMessage(target, ChatColor.GREEN+"----------------------------------------------------");
								}
							}
		    		  }
		    		  return true;
		    	  }
		    	  
		      }
		      
		      if(args[0].equalsIgnoreCase("reject")){
		    	  
		    	  if(whatClan.equals(Clans.UNCLANNED))
		    		  return true;
		    	  
		    	  if(whatClan.equals(Clans.EXILED))
		    		  return true;
		    	  
		    	  if(clan.getProposed().getUsername().equalsIgnoreCase(character.getUsername())){
		    		  if(clan.getKing() == null){
		    			  clan.addRejected(character);
							if(whatClan.equals(Clans.COLORLESS)){
								Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
								Message.sendCenteredMessage(player, ChatColor.BOLD + "Game Info");
								Message.sendCenteredMessage(player, ChatColor.YELLOW+"You have rejected becoming the " + ChatColor.DARK_GRAY + whatClan.toString().toLowerCase().substring(0, 1).toUpperCase() + whatClan.toString().toLowerCase().substring(1) + ChatColor.YELLOW + " King!");
								Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
							}else{
								Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
								Message.sendCenteredMessage(player, ChatColor.BOLD + "Game Info");
								Message.sendCenteredMessage(player, ChatColor.YELLOW+"You have rejected becoming the " + ChatColor.valueOf(whatClan.toString().toUpperCase()) + whatClan.toString().toLowerCase().substring(0, 1).toUpperCase() + whatClan.toString().toLowerCase().substring(1) + ChatColor.YELLOW + " King!");
								Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
							}
		    		  }
		    		  return true;
		    	  }
		    	  
		      }
		      
		      /*
		      if(args[0].equalsIgnoreCase("kick")){
		    	  if(args.length == 2){
			    	  if(Clan.getManager().isInClan(player)){
			    		  if(Clan.getManager().getRank(player) == Rank.KING || Clan.getManager().getRank(player) == Rank.OFFICER){
			    			  Player target = Bukkit.getPlayer(args[1]);
			    			  if(target != null){
			    				  if(Clan.getManager().getClan(target).equals(Clan.getManager().getClan(player))){
			    					  if(Clan.getManager().getRank(target) == Rank.CLANSMEN){
			    						  Clan.getManager().removeClanMember(target, Clan.getManager().getClan(player));
			    							Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
			    							Message.sendCenteredMessage(player, ChatColor.BOLD + "Clan Info");
			    							Message.sendCenteredMessage(player, ChatColor.YELLOW+"INFO: You have kicked "+args[1]+" from the Clan!");
			    							Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
			    							Message.sendCenteredMessage(target, ChatColor.GREEN+"----------------------------------------------------");
			    							Message.sendCenteredMessage(target, ChatColor.BOLD + "Clan Info");
			    							Message.sendCenteredMessage(target, ChatColor.YELLOW+"INFO: You have been kicked out of your clan!");
			    							Message.sendCenteredMessage(target, ChatColor.GREEN+"----------------------------------------------------");
			    			    		  return true;
			    					  }else{
			    							Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
			    							Message.sendCenteredMessage(player, ChatColor.BOLD + "Clan Info");
			    							Message.sendCenteredMessage(player, ChatColor.YELLOW+"ERROR: A King/Officer cannot kick a King/Officer!");
			    							Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
			    			    		  return true;
			    					  }
			    				  }else{
		    							Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
		    							Message.sendCenteredMessage(player, ChatColor.BOLD + "Clan Info");
		    							Message.sendCenteredMessage(player, ChatColor.YELLOW+"ERROR: "+ args[1]+" is not in your clan.");
		    							Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
			    		    		  return true;
			    				  }
			    			  }else{
	    							Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
	    							Message.sendCenteredMessage(player, ChatColor.BOLD + "Clan Info");
	    							Message.sendCenteredMessage(player, ChatColor.YELLOW+"ERROR: Player " + args[1] +" does not exist or is offline.");
	    							Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
					    		  return true;
			    			  }
			    		  }else{
  							Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
  							Message.sendCenteredMessage(player, ChatColor.BOLD + "Clan Info");
  							Message.sendCenteredMessage(player, ChatColor.YELLOW+"ERROR: Only the King/Officer can kick people!");
  							Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
				    		  return true;
			    		  }
			    	  }else{
							Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
							Message.sendCenteredMessage(player, ChatColor.BOLD + "Clan Info");
							Message.sendCenteredMessage(player, ChatColor.YELLOW+"ERROR: You are not in a Clan!");
							Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
			    		  return true;
			    	  }
		    	  }else{
						Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
						Message.sendCenteredMessage(player, ChatColor.BOLD + "Clan Info");
						Message.sendCenteredMessage(player, ChatColor.YELLOW+"ERROR: Invalid usage, check /sevenkings help for correct command usage!");
						Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
		    		  return true;
		    	  }
		      }
		      */
		     
		      if(args[0].equalsIgnoreCase("join")){
		    	  if(args.length == 2){
			    	  if(!(character.isInClan())){
			    		  try{
			    			  Clans clanchoice = Clans.valueOf(args[1].toUpperCase());
			    			  Clan theClan = new Clan(clanchoice);
			    			  theClan.addClansmen(character);
	    						Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
	    						Message.sendCenteredMessage(player, ChatColor.BOLD + "Clan Info");
	    						Message.sendCenteredMessage(player, ChatColor.YELLOW+"INFO: You have joined the "+args[1]+" Clan!");
	    						Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
	    						return true;
			    		  }catch(Exception IllegalArgumentException){
  							Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
  							Message.sendCenteredMessage(player, ChatColor.BOLD + "Clan Info");
  							Message.sendCenteredMessage(player, ChatColor.YELLOW+"ERROR: "+args[1]+ " is not a valid Clan!");
  							Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
				    		  return true;
			    		  }
			    	  }else{
							Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
							Message.sendCenteredMessage(player, ChatColor.BOLD + "Clan Info");
							Message.sendCenteredMessage(player, ChatColor.YELLOW+"ERROR: You are already in a Clan!");
							Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
			    		  return true; 
			    	  }  
		    	  }else{
						Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
						Message.sendCenteredMessage(player, ChatColor.BOLD + "Clan Info");
						Message.sendCenteredMessage(player, ChatColor.YELLOW+"ERROR: Invalid usage, check /sevenkings help for correct command usage!");
						Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
		    		  return true;
		    	  }
		      }
	
		      
		      if(args[0].equalsIgnoreCase("info")){
		    	  if(args.length == 2){
		    		  for(Clans clans : Clans.values()){
		    			  if(!clan.equals(Clans.UNCLANNED)){
		    				  if(args[1].equalsIgnoreCase(clan.toString().toLowerCase())){
		    					  player.openInventory(ClanMenu.generateClanMenu(clans));
		  						Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
		    					  new FancyMessage("You have opened the ")
		    					  .color(ChatColor.YELLOW)
		    					  .then(clan.toString().toLowerCase())
		    					  .color(ChatColor.valueOf(clan.toString().toUpperCase()))
		    					  .then("'s Clan Menu. Click ")
		    					  .color(ChatColor.YELLOW)
		    					  .then("here")
		    					  .command("/clan info "+args[1])
		    					  .color(ChatColor.GOLD)
		    					  .style(ChatColor.BOLD)
		    					  .then(" to reopen the menu.")
		    					  .color(ChatColor.YELLOW)
		    					  .send(player);
		  						Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
		    					  
		    				  }
		    			  }
		    		  }
		    		  //Get player menu here.
		    	  }else{
		    		  Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
		    		  Message.sendCenteredMessage(player, ChatColor.YELLOW+"Invalid Arguemnts for proper usage use /Help");
		    		  Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
		    		  return true;
		    	  }
		      }
			
		}
		
		if(cmd.getName().equalsIgnoreCase("rune")){
			
			if(args.length == 0){
				Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
				Message.sendCenteredMessage(player, ChatColor.BOLD + "Rune Help");
				Message.sendCenteredMessage(player, ChatColor.YELLOW + "/Rune list "+ChatColor.GOLD+": for a list of Runes.");
				Message.sendCenteredMessage(player, ChatColor.YELLOW + "/Rune <Enchantment> <level> <Success Rate> <Destroy Rate>");
				Message.sendCenteredMessage(player, ChatColor.YELLOW + "/Rune slot <Amount> "+ChatColor.GOLD+": gives a Slot Rune.");
				Message.sendCenteredMessage(player, ChatColor.YELLOW + "/Rune protection "+ChatColor.GOLD+": gives a Protection Rune.");
				Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
				return true;
			}
			
			if(args[0].equalsIgnoreCase("list")){
				if(args.length == 1){
					Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
					Message.sendCenteredMessage(player, ChatColor.BOLD + "Runes");
					
					String enchants = "";
					for(Enchantments enchant : Enchantments.values()){
						enchants = enchants + ChatColor.GOLD+ Enchantments.getName(enchant) + ChatColor.YELLOW+ ", ";
					}
					Message.sendCenteredMessage(player, enchants);
					Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
					return true;
				}else{
		    		  Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
		    		  Message.sendCenteredMessage(player, ChatColor.YELLOW+"Invalid Arguemnts for proper usage use /Rune");
		    		  Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
		    		  return true;
				}
			}else if(args[0].equalsIgnoreCase("slot")){
				if(args.length == 2){
					try{
						int slots = Integer.valueOf(args[1]);
						player.getInventory().addItem(Rune.createSlotRune(slots));
						return true;
					}catch(Exception e){
			    		  Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
			    		  Message.sendCenteredMessage(player, ChatColor.YELLOW+args[1]+" is not a whole number or your inventory is full!");
			    		  Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
			    		  return true;
					}
				}else{
		    		  Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
		    		  Message.sendCenteredMessage(player, ChatColor.YELLOW+"Invalid Arguemnts for proper usage use /Rune");
		    		  Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
		    		  return true;
				}
			}else if(args[0].equalsIgnoreCase("protection")){
				if(args.length == 1){
					try{
						player.getInventory().addItem(Rune.createProtectionRune());
						return true;
					}catch(Exception e){
			    		  Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
			    		  Message.sendCenteredMessage(player, ChatColor.YELLOW+"Your inventory is full.");
			    		  Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
			    		  return true;
					}
				}else{
		    		  Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
		    		  Message.sendCenteredMessage(player, ChatColor.YELLOW+"Invalid Arguemnts for proper usage use /Rune");
		    		  Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
		    		  return true;
				}
			}
			
			
			try{
				Enchantments enchant = Enchantments.valueOf(args[0].toUpperCase());
				int level = Integer.valueOf(args[1]);
				int success = Integer.valueOf(args[2]);
				int destroy = Integer.valueOf(args[3]);
				if(level > Enchantments.getMaxLevel(enchant)){
					player.sendMessage(ChatColor.YELLOW + "Error: The max level for " + Enchantments.getName(enchant) + " is " + Enchantments.getMaxLevel(enchant));
					return true;
				}
				player.getInventory().addItem(Rune.createRune(enchant, level, success, destroy));
				return true;
			}catch(Exception e){
	    		  Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
	    		  Message.sendCenteredMessage(player, ChatColor.YELLOW+args[0]+" is not a rune, or "+args[1]+", "+args[2]+", or "+args[3]+" are not whole numbers!");
	    		  Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
	    		  return true;
			}
			

		}
				
    	return true;
    }
	
}
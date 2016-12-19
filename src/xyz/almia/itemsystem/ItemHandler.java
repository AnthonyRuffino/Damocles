package xyz.almia.itemsystem;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import mkremins.fanciful.FancyMessage;
import net.minecraft.server.v1_11_R1.NBTTagCompound;
import net.minecraft.server.v1_11_R1.NBTTagInt;
import net.minecraft.server.v1_11_R1.NBTTagList;
import net.minecraft.server.v1_11_R1.NBTTagString;
import xyz.almia.accountsystem.Account;
import xyz.almia.utils.Message;

public class ItemHandler implements Listener{
	
	public static ItemTypes getType(ItemStack item){
		switch(item.getType()){
		case DIAMOND_SPADE: case GOLD_SPADE: case IRON_SPADE: case WOOD_SPADE: case STONE_SPADE: case DIAMOND_PICKAXE: case IRON_PICKAXE: case WOOD_PICKAXE: case STONE_PICKAXE: case GOLD_PICKAXE: case DIAMOND_AXE: case IRON_AXE: case WOOD_AXE: case GOLD_AXE: case STONE_AXE:
			return ItemTypes.TOOL;
		case DIAMOND_HELMET: case IRON_HELMET: case CHAINMAIL_HELMET: case GOLD_HELMET: case LEATHER_HELMET: case DIAMOND_CHESTPLATE: case IRON_CHESTPLATE: case CHAINMAIL_CHESTPLATE: case GOLD_CHESTPLATE: case LEATHER_CHESTPLATE: case DIAMOND_LEGGINGS: case IRON_LEGGINGS: case CHAINMAIL_LEGGINGS: case GOLD_LEGGINGS: case LEATHER_LEGGINGS: case DIAMOND_BOOTS: case IRON_BOOTS: case CHAINMAIL_BOOTS: case GOLD_BOOTS: case LEATHER_BOOTS:
			return ItemTypes.ARMOR;
		case SHIELD:
			return ItemTypes.SHIELD;
		case DIAMOND_SWORD: case IRON_SWORD: case WOOD_SWORD: case GOLD_SWORD: case STONE_SWORD: case BOW:
			return ItemTypes.WEAPON;
		case POTION:
			return ItemTypes.POTION;
		default:
			return ItemTypes.ALL;
		}
	}
	
	@EventHandler
	public void onPotionConsume(PlayerItemConsumeEvent event){
		ItemStack consumed = event.getItem();
		if(getType(consumed).equals(ItemTypes.POTION)){
			Potion potion = new Potion(consumed);
			Account player = new Account(event.getPlayer());
			xyz.almia.accountsystem.Character character = player.getLoadedCharacter();
			switch(potion.getPotionType()){
			case HEALING:
				character.setHealth(character.getHealth() + potion.getAmount());
				String item = new FancyMessage("                                You drank a ")
						.color(ChatColor.YELLOW)
						.then("Potion")
						.color(ChatColor.GOLD)
						.style(ChatColor.BOLD)
						.itemTooltip(consumed)
						.then("!")
						.color(ChatColor.YELLOW)
						.toJSONString();
	    		  Message.sendCenteredMessage(event.getPlayer(), ChatColor.GREEN+"----------------------------------------------------");
	    		  FancyMessage.deserialize(item).send(event.getPlayer());				   
	    		  Message.sendCenteredMessage(event.getPlayer(), ChatColor.GREEN+"----------------------------------------------------");
				return;
			case MANA:
				character.setMana(character.getMana() + potion.getAmount());
				String item2 = new FancyMessage("                                You drank a ")
						.color(ChatColor.YELLOW)
						.then("Potion")
						.color(ChatColor.GOLD)
						.style(ChatColor.BOLD)
						.itemTooltip(consumed)
						.then("!")
						.color(ChatColor.YELLOW)
						.toJSONString();
	    		  Message.sendCenteredMessage(event.getPlayer(), ChatColor.GREEN+"----------------------------------------------------");
	    		  FancyMessage.deserialize(item2).send(event.getPlayer());				   
	    		  Message.sendCenteredMessage(event.getPlayer(), ChatColor.GREEN+"----------------------------------------------------");
				return;
			case NONE:
				return;
			default:
				return;
			}
		}
	}
	
	public static EnchantTypes getEnchantType(ItemStack item){
		switch(item.getType()){
		case DIAMOND_HELMET: case IRON_HELMET: case CHAINMAIL_HELMET: case GOLD_HELMET: case LEATHER_HELMET:
			return EnchantTypes.HELMET;
		case DIAMOND_CHESTPLATE: case IRON_CHESTPLATE: case CHAINMAIL_CHESTPLATE: case GOLD_CHESTPLATE: case LEATHER_CHESTPLATE:
			return EnchantTypes.CHESTPLATE;
		case DIAMOND_LEGGINGS: case IRON_LEGGINGS: case CHAINMAIL_LEGGINGS: case GOLD_LEGGINGS: case LEATHER_LEGGINGS:
			return EnchantTypes.LEGGINGS;
		case DIAMOND_BOOTS: case IRON_BOOTS: case CHAINMAIL_BOOTS: case GOLD_BOOTS: case LEATHER_BOOTS:
			return EnchantTypes.BOOTS;
		case DIAMOND_SWORD: case IRON_SWORD: case WOOD_SWORD: case GOLD_SWORD: case STONE_SWORD:
			return EnchantTypes.SWORD;
		case BOW:
			return EnchantTypes.BOW;
		default:
			return EnchantTypes.NONE;
		}
	}
	
	public static ItemStack buildItem(Material material,String name, List<String> lore, int amount, int durability){
		ItemStack x = new ItemStack(material, amount);
        net.minecraft.server.v1_11_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(x);
        NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
        NBTTagList modifiers = new NBTTagList();
        NBTTagCompound toughness = new NBTTagCompound();
        toughness.set("AttributeName", new NBTTagString("armorToughness"));
        toughness.set("Name", new NBTTagString("armorToughness"));
        toughness.set("Amount", new NBTTagInt(0));
        toughness.set("Operation", new NBTTagInt(0));
        toughness.set("UUIDLeast", new NBTTagInt(894654));
        toughness.set("UUIDMost", new NBTTagInt(2872));
        modifiers.add(toughness);
        NBTTagCompound armor = new NBTTagCompound();
        armor.set("AttributeName", new NBTTagString("generic.armor"));
        armor.set("Name", new NBTTagString("generic.armor"));
        armor.set("Amount", new NBTTagInt(0));
        armor.set("Operation", new NBTTagInt(0));
        armor.set("UUIDLeast", new NBTTagInt(894654));
        armor.set("UUIDMost", new NBTTagInt(2872));
        modifiers.add(armor);
        compound.set("AttributeModifiers", modifiers);
        nmsStack.setTag(compound);
        x = CraftItemStack.asBukkitCopy(nmsStack);
		ItemMeta im = x.getItemMeta();
		im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		x.setItemMeta(im);
		return x;
	}
	
	@EventHandler
	public void onEnderPearl(PlayerInteractEvent event){
		if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			if(event.getItem() != null){
				if(event.getItem().getType().equals(Material.EYE_OF_ENDER)){
					if(event.getItem().hasItemMeta()){
						if(event.getItem().getItemMeta().getDisplayName().contains(ChatColor.YELLOW + "Slot Rune")){
							event.setCancelled(true);
							event.getPlayer().updateInventory();
						}
					}
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onItemApply(InventoryClickEvent event){
		Player player = (Player) event.getWhoClicked();
		if(event.getCurrentItem() != null){
			
			if(!(ItemHandler.getType(event.getCurrentItem()).equals(ItemTypes.POTION)) && !(ItemHandler.getType(event.getCurrentItem()).equals(ItemTypes.NONE))){
				if(event.getCursor() != null){
					
					if(event.getCursor().getType().equals(Material.EMPTY_MAP)){
						
						if(ItemHandler.getType(event.getCurrentItem()).equals(ItemTypes.ARMOR)){
							Armor detailItem = new Armor(event.getCurrentItem());
							if(!(detailItem.isProtected())){
								event.setCancelled(true);
								detailItem.protect();
								event.setCursor(Rune.setAmount(event.getCursor(), event.getCursor().getAmount() - 1));
					    		Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
					    		Message.sendCenteredMessage(player, ChatColor.YELLOW+"You have successfully Protected your equip!");
					    		Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
					    		player.getWorld().spawnParticle(Particle.CRIT_MAGIC, player.getLocation(), 50);
							}else{
					    		Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
					    		Message.sendCenteredMessage(player, ChatColor.YELLOW+"Item is already Protected!");
					    		Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
								event.setCancelled(true);
								return;
							}
						}else if(ItemHandler.getType(event.getCurrentItem()).equals(ItemTypes.WEAPON)){
							Weapon detailItem = new Weapon(event.getCurrentItem());
							if(!(detailItem.isProtected())){
								event.setCancelled(true);
								detailItem.protect();
								event.setCursor(Rune.setAmount(event.getCursor(), event.getCursor().getAmount() - 1));
					    		Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
					    		Message.sendCenteredMessage(player, ChatColor.YELLOW+"You have successfully Protected your equip!");
					    		Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
					    		player.getWorld().spawnParticle(Particle.CRIT_MAGIC, player.getLocation(), 50);
							}else{
					    		Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
					    		Message.sendCenteredMessage(player, ChatColor.YELLOW+"Item is already Protected!");
					    		Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
								event.setCancelled(true);
								return;
							}
						}else{
				    		Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
				    		Message.sendCenteredMessage(player, ChatColor.YELLOW+"An error has occured.");
				    		Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
							return;
						}
						
					}
					
					if(event.getCursor().getType().equals(Material.EYE_OF_ENDER)){
						
						if(ItemHandler.getType(event.getCurrentItem()).equals(ItemTypes.ARMOR)){
							Armor detailItem = new Armor(event.getCurrentItem());
							if(event.getCursor().hasItemMeta()){
								event.setCancelled(true);
								int slots = Rune.getSlotsFromRune(event.getCursor());
								detailItem.setSlots(detailItem.getSlots() + Rune.getSlotsFromRune(event.getCursor()));
								event.setCursor(Rune.setAmount(event.getCursor(), event.getCursor().getAmount() - 1));
								String item = new FancyMessage("                     You added "+slots+" to ")
										.color(ChatColor.YELLOW)
										.then("this")
										.color(ChatColor.GOLD)
										.style(ChatColor.BOLD)
										.itemTooltip(event.getCurrentItem())
										.then(" item!")
										.color(ChatColor.YELLOW)
										.toJSONString();
					    		  Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
					    		  FancyMessage.deserialize(item).send(player);				   
					    		  Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
					    		  player.getWorld().spawnParticle(Particle.CRIT_MAGIC, player.getLocation(), 50);
							}
						}else if(ItemHandler.getType(event.getCurrentItem()).equals(ItemTypes.WEAPON)){
							Weapon detailItem = new Weapon(event.getCurrentItem());
							if(event.getCursor().hasItemMeta()){
								event.setCancelled(true);
								int slots = Rune.getSlotsFromRune(event.getCursor());
								detailItem.setSlots(detailItem.getSlots() + Rune.getSlotsFromRune(event.getCursor()));
								event.setCursor(Rune.setAmount(event.getCursor(), event.getCursor().getAmount() - 1));
								String item = new FancyMessage("                     You added "+slots+" to ")
										.color(ChatColor.YELLOW)
										.then("this")
										.color(ChatColor.GOLD)
										.style(ChatColor.BOLD)
										.itemTooltip(event.getCurrentItem())
										.then(" item!")
										.color(ChatColor.YELLOW)
										.toJSONString();
					    		  Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
					    		  FancyMessage.deserialize(item).send(player);				   
					    		  Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
					    		  player.getWorld().spawnParticle(Particle.CRIT_MAGIC, player.getLocation(), 50);
							}
						}else{
				    		Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
				    		Message.sendCenteredMessage(player, ChatColor.YELLOW+"An error has occured.");
				    		Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
							return;
						}
					}
					
					if(event.getCursor().getType().equals(Material.NETHER_STAR)){
						
						if(event.getCurrentItem().equals(null) || event.getCurrentItem().equals(Material.AIR)){
							return;
						}
						
						if(ItemHandler.getType(event.getCurrentItem()).equals(ItemTypes.ARMOR)){
							
							Armor detailItem = new Armor(event.getCurrentItem());
							
							Map<String, Integer> values = Rune.getRune(event.getCursor());
							Enchantments enchantment = null;
							int enchantLevel = 0;
							int enchantSuccess = 0;
							int enchantDestroy = 0;
							for(Entry<String, Integer> string : values.entrySet()){
								for(Enchantments enchantments : Enchantments.values()){
									if(enchantments.toString().equals(string.getKey().toUpperCase())){
										enchantment = Enchantments.valueOf(string.getKey().toUpperCase());
									}
								}
							}
							enchantSuccess = values.get("success");
							enchantDestroy = values.get("destroy");
							enchantLevel = values.get("level");
							
							if(detailItem.getEnchants() != null){
								List<Enchantments> enchantments = detailItem.getEnchants();
								if(enchantments.contains(enchantment)){
						    		Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
						    		Message.sendCenteredMessage(player, ChatColor.YELLOW+"This item already has an enchantment with the same name.");
						    		Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
									return;
								}
							}
							
							if(detailItem.getSlots()  <= 0){
								return;
							}
							
							if(detailItem.getSlots() > 0){
								
								
								if(Enchantments.getType(enchantment).equals(EnchantTypes.ARMOR)){
									event.setCancelled(true);
									ItemStack item = enchant(player, event.getCurrentItem(), enchantment, enchantLevel, enchantSuccess, enchantDestroy);
									event.setCurrentItem(item);
									event.setCursor(Rune.setAmount(event.getCursor(), event.getCursor().getAmount() - 1));
									return;
								}else if(Enchantments.getType(enchantment).equals(EnchantTypes.BOOTS)){
									if(ItemHandler.getEnchantType(event.getCurrentItem()).equals(EnchantTypes.BOOTS)){
										event.setCancelled(true);
										ItemStack item = enchant(player, event.getCurrentItem(), enchantment, enchantLevel, enchantSuccess, enchantDestroy);
										event.setCurrentItem(item);
										event.setCursor(Rune.setAmount(event.getCursor(), event.getCursor().getAmount() - 1));
										return;
									}
								}else if(Enchantments.getType(enchantment).equals(EnchantTypes.LEGGINGS)){
									if(ItemHandler.getEnchantType(event.getCurrentItem()).equals(EnchantTypes.LEGGINGS)){
										event.setCancelled(true);
										ItemStack item = enchant(player, event.getCurrentItem(), enchantment, enchantLevel, enchantSuccess, enchantDestroy);
										event.setCurrentItem(item);
										event.setCursor(Rune.setAmount(event.getCursor(), event.getCursor().getAmount() - 1));
										return;
									}
								}else if(Enchantments.getType(enchantment).equals(EnchantTypes.CHESTPLATE)){
									if(ItemHandler.getEnchantType(event.getCurrentItem()).equals(EnchantTypes.CHESTPLATE)){
										event.setCancelled(true);
										ItemStack item = enchant(player, event.getCurrentItem(), enchantment, enchantLevel, enchantSuccess, enchantDestroy);
										event.setCurrentItem(item);
										event.setCursor(Rune.setAmount(event.getCursor(), event.getCursor().getAmount() - 1));
										return;
									}
								}else if(Enchantments.getType(enchantment).equals(EnchantTypes.HELMET)){
									if(ItemHandler.getEnchantType(event.getCurrentItem()).equals(EnchantTypes.HELMET)){
										event.setCancelled(true);
										ItemStack item = enchant(player, event.getCurrentItem(), enchantment, enchantLevel, enchantSuccess, enchantDestroy);
										event.setCurrentItem(item);
										event.setCursor(Rune.setAmount(event.getCursor(), event.getCursor().getAmount() - 1));
										return;
									}
								}else{
									event.setCancelled(true);
								}
								
								
							}
							player.updateInventory();
						}else if(ItemHandler.getType(event.getCurrentItem()).equals(ItemTypes.WEAPON)){
							
							Weapon detailItem = new Weapon(event.getCurrentItem());
							
							Map<String, Integer> values = Rune.getRune(event.getCursor());
							Enchantments enchantment = null;
							int enchantLevel = 0;
							int enchantSuccess = 0;
							int enchantDestroy = 0;
							for(Entry<String, Integer> string : values.entrySet()){
								for(Enchantments enchantments : Enchantments.values()){
									if(enchantments.toString().equals(string.getKey().toUpperCase())){
										enchantment = Enchantments.valueOf(string.getKey().toUpperCase());
									}
								}
							}
							enchantSuccess = values.get("success");
							enchantDestroy = values.get("destroy");
							enchantLevel = values.get("level");
							
							if(detailItem.getEnchants() != null){
								List<Enchantments> enchantments = detailItem.getEnchants();
								if(enchantments.contains(enchantment)){
						    		Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
						    		Message.sendCenteredMessage(player, ChatColor.YELLOW+"This item already has an enchantment with the same name.");
						    		Message.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
									return;
								}
							}
							
							if(detailItem.getSlots()  <= 0){
								return;
							}
							
							if(detailItem.getSlots() > 0){
								
								
								if(Enchantments.getType(enchantment).equals(EnchantTypes.SWORD)){
									if(ItemHandler.getEnchantType(event.getCurrentItem()).equals(EnchantTypes.SWORD)){
										event.setCancelled(true);
										ItemStack item = enchant(player, event.getCurrentItem(), enchantment, enchantLevel, enchantSuccess, enchantDestroy);
										event.setCurrentItem(item);
										event.setCursor(Rune.setAmount(event.getCursor(), event.getCursor().getAmount() - 1));
										return;
									}
								}else if(Enchantments.getType(enchantment).equals(EnchantTypes.BOW)){
									if(ItemHandler.getEnchantType(event.getCurrentItem()).equals(EnchantTypes.BOW)){
										event.setCancelled(true);
										ItemStack item = enchant(player, event.getCurrentItem(), enchantment, enchantLevel, enchantSuccess, enchantDestroy);
										event.setCurrentItem(item);
										event.setCursor(Rune.setAmount(event.getCursor(), event.getCursor().getAmount() - 1));
										return;
									}
								}else{
									event.setCancelled(true);
									return;
								}
								
								
							}
							player.updateInventory();
						}else{
							return;
						}
						
						
						
						
					}
				}
			}
		}
	}
	
	public static ItemStack enchant(Player source, ItemStack item, Enchantments enchant, int level, int success, int destroy){
		if(ItemHandler.getType(item).equals(ItemTypes.ARMOR)){
			Armor detailItem = new Armor(item);
			int random = new Random().nextInt(100);
			if(random <= success){
				Message.sendCenteredMessage(source, ChatColor.GREEN+"----------------------------------------------------");
				String items = new FancyMessage("                     You added "+Enchantments.getName(enchant)+" to ")
						.color(ChatColor.YELLOW)
						.then("this")
						.color(ChatColor.GOLD)
						.style(ChatColor.BOLD)
						.itemTooltip(item)
						.then(" item!")
						.color(ChatColor.YELLOW)
						.toJSONString();
				FancyMessage.deserialize(items).send(source);		
				Message.sendCenteredMessage(source, ChatColor.GREEN+"----------------------------------------------------");
				source.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, source.getLocation(), 10);
				detailItem.setSlots(detailItem.getSlots() - 1);
				detailItem.addEnchant(enchant, level);
			}else{
				int randDestroy = new Random().nextInt(100);
				if(randDestroy <= destroy){
					Message.sendCenteredMessage(source, ChatColor.GREEN+"----------------------------------------------------");
					String items = new FancyMessage("                     You have failed to add "+Enchantments.getName(enchant)+" to ")
							.color(ChatColor.YELLOW)
							.then("this")
							.color(ChatColor.GOLD)
							.style(ChatColor.BOLD)
							.itemTooltip(item)
							.then(" item!")
							.color(ChatColor.YELLOW)
							.toJSONString();
					FancyMessage.deserialize(items).send(source);		
					Message.sendCenteredMessage(source, ChatColor.GREEN+"----------------------------------------------------");
					source.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, source.getLocation(), 10);
					if(detailItem.isProtected()){
						detailItem.unProtect();
						return item;
					}
					return new ItemStack(Material.AIR, 1);
				}else{
					return item;
				}
			}
			return item;
		}
		if(ItemHandler.getType(item).equals(ItemTypes.WEAPON)){
			Weapon detailItem = new Weapon(item);
			int random = new Random().nextInt(100);
			if(random <= success){
				Message.sendCenteredMessage(source, ChatColor.GREEN+"----------------------------------------------------");
				String items = new FancyMessage("                     You added "+Enchantments.getName(enchant)+" to ")
						.color(ChatColor.YELLOW)
						.then("this")
						.color(ChatColor.GOLD)
						.style(ChatColor.BOLD)
						.itemTooltip(item)
						.then(" item!")
						.color(ChatColor.YELLOW)
						.toJSONString();
				FancyMessage.deserialize(items).send(source);		
				Message.sendCenteredMessage(source, ChatColor.GREEN+"----------------------------------------------------");
				source.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, source.getLocation(), 10);
				detailItem.setSlots(detailItem.getSlots() - 1);
				detailItem.addEnchant(enchant, level);
			}else{
				int randDestroy = new Random().nextInt(100);
				if(randDestroy <= destroy){
					Message.sendCenteredMessage(source, ChatColor.GREEN+"----------------------------------------------------");
					String items = new FancyMessage("                     You have failed to add "+Enchantments.getName(enchant)+" to ")
							.color(ChatColor.YELLOW)
							.then("this")
							.color(ChatColor.GOLD)
							.style(ChatColor.BOLD)
							.itemTooltip(item)
							.then(" item!")
							.color(ChatColor.YELLOW)
							.toJSONString();
					FancyMessage.deserialize(items).send(source);		
					Message.sendCenteredMessage(source, ChatColor.GREEN+"----------------------------------------------------");
					source.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, source.getLocation(), 10);
					if(detailItem.isProtected()){
						detailItem.unProtect();
						return item;
					}
					return new ItemStack(Material.AIR, 1);
				}else{
					return item;
				}
			}
			return item;
		}
		return item;
	}
	
}
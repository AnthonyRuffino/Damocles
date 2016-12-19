package xyz.almia.lootsystem;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import xyz.almia.cardinalsystem.Cardinal;

public class Chest {
	
	public static void createLootChest(Location loc, List<ItemStack> contents){
		loc.getBlock().setType(Material.CHEST);
		
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(Cardinal.getPlugin(), new Runnable() {
            @Override
            public void run() {
        		new BukkitRunnable(){
        			double t = 0;
        			public void run(){
        				
        				
        				
        				t += Math.PI/8;
        				Location newLoc = loc;
        				for(double phi = 0; phi <= 2*Math.PI; phi += Math.PI/2){
        					double x = 0.2*(2*Math.PI - t) * Math.cos(t + phi);
        					double y = 0.3*t;
        					double z = 0.2*(2*Math.PI - t) * Math.sin(t + phi);
        					newLoc.add(x, y, z);
        					loc.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, newLoc, 1);
        					newLoc.subtract(x, y, z);
        					
        					if( t >= 2*Math.PI){
        						this.cancel();
        					}
        				}
        				
        				
        				
        				
        			}
        		}.runTaskTimer(Cardinal.getPlugin(), 0, 1);
            }
        }, 0L, 20L);
	}
}

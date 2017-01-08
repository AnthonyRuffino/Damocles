package xyz.almia.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public class MaterialToString {
	
	public static String getName(Material mat){
		String name = mat.toString().replace("_", " ").toLowerCase();
		String[] fragments = name.split(" ");
		String fragmentTotal = "";
		String cap = "";
		
		for(int i = 0; i < fragments.length; i++){
			cap = ChatColor.WHITE + fragments[i].substring(0, 1).toUpperCase() + fragments[i].substring(1);
			if(i == 0){
				fragmentTotal = cap;
			}else{
				fragmentTotal = fragmentTotal + " " + cap;
			}
		}
		return fragmentTotal;
	}
	
}

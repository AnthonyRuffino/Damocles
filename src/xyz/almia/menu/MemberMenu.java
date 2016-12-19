package xyz.almia.menu;

import org.bukkit.plugin.Plugin;

import xyz.almia.cardinalsystem.Cardinal;

public class MemberMenu {
	
    private static MemberMenu membermenu;
    
    public static Plugin plugin = Cardinal.getPlugin();
    
    private MemberMenu() {}
    
    public static MemberMenu getManager() {
        if (membermenu == null)
            membermenu = new MemberMenu();
        return membermenu;
    }
	
}

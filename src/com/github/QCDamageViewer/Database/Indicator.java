package com.github.QCDamageViewer.Database;

import org.bukkit.ChatColor;
import org.bukkit.Location;

import net.minecraft.server.v1_12_R1.EntityArmorStand;
import net.minecraft.server.v1_12_R1.WorldServer;

public class Indicator {
	
	public static EntityArmorStand getIndicator(WorldServer ws, Location loc, double damage) {
		
		EntityArmorStand stand = new EntityArmorStand(ws);
		
		stand.setLocation(loc.getX(), loc.getY(), loc.getZ(), 0, 0);
        stand.setCustomName(ChatColor.RED + " " + damage);
        stand.setCustomNameVisible(true);
        stand.setNoGravity(true);
        stand.setSmall(true);
        stand.setInvisible(true);
        
        return stand;
	}
}

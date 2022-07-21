package com.github.QCDamageViewer.Event;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import com.github.QCDamageViewer.Main;
import com.github.QCDamageViewer.Database.Indicator;

import net.minecraft.server.v1_12_R1.EntityArmorStand;
import net.minecraft.server.v1_12_R1.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_12_R1.PacketPlayOutSpawnEntityLiving;

public class onDamage implements Listener {

	public onDamage(Main plugin)
	{
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event)
	{

		if (!(event.getDamager() instanceof Player)) { return; }
		
		CraftPlayer attacker = (CraftPlayer) event.getDamager();
		BukkitScheduler scheduler = Bukkit.getScheduler();
		Plugin plugin = Bukkit.getPluginManager().getPlugin("QCDamageViewer");
		
		// Create Indicator
		Location loc = event.getEntity().getLocation();
		loc.setY( (loc.getY()) - 0.3 );
		
		EntityArmorStand indicator = Indicator.getIndicator( ((CraftWorld) attacker.getWorld()).getHandle(),
															loc,
															(event.getFinalDamage()) / 2 );
		int indicatorId = indicator.getId();
		
		
		// Create Packet
		PacketPlayOutSpawnEntityLiving spawnPacket = new PacketPlayOutSpawnEntityLiving(indicator);
		PacketPlayOutEntityDestroy killPacket = new PacketPlayOutEntityDestroy(indicatorId);
		
		// send Spawn Packet
		attacker.getHandle().playerConnection.sendPacket(spawnPacket);
		
		// send Kill Packet after 1 sec
		scheduler.runTaskLater(plugin, () -> {
			
	        attacker.getHandle().playerConnection.sendPacket(killPacket);
	        
		}, 20L);
		
	}
}

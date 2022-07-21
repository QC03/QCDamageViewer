package com.github.QCDamageViewer;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.QCDamageViewer.Event.onDamage;

public class Main extends JavaPlugin implements Listener {
	
	ConsoleCommandSender console = Bukkit.getConsoleSender();
	
	@Override
	public void onEnable() {
		
		console.sendMessage( ChatColor.AQUA + "[QC Damage Viewer] Enable Plugin.");
		
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(new onDamage(this), this);

	}
	
	@Override
	public void onDisable() {
		
		console.sendMessage( ChatColor.AQUA + "[QC Damage Viewer] Disable Plugin.");
		
	}
	
}
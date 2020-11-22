package in.glennjam.homecoords;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

public class Main extends JavaPlugin {
	
	@Override
    public void onEnable() {
		saveDefaultConfig();
		getLogger().info("---------------------------------------");
		getLogger().info("HomeCoords v1.0 by TheRealSimShady");
		getLogger().info("HomeCoords is enabled!");
		getLogger().info("For support, DM Simsnet#1754 on Discord");
		getLogger().info("---------------------------------------");
	}
    @Override
    public void onDisable() {
		getLogger().info("---------------------------------------");
		getLogger().info("HomeCoords v1.0 by TheRealSimShady");
		getLogger().info("HomeCoords is disabled!");
		getLogger().info("For support, DM Simsnet#1754 on Discord");
		getLogger().info("---------------------------------------");
    }
    
    @Override
    public boolean onCommand(CommandSender sender,
                             Command cmd,
                             String label,
                             String[] args) {
        if (cmd.getName().equalsIgnoreCase("sethome") && (sender instanceof Player)) {
        		Player player = (Player) sender;
            	String self = player.getName();
            	int sx = player.getLocation().getBlockX();
            	int sz = player.getLocation().getBlockZ();
            	this.getConfig().set(self + ".x", sx);
            	this.getConfig().set(self + ".z", sz);
            	this.saveConfig();
            	sender.sendMessage(ChatColor.GREEN + "X: " + ChatColor.GOLD + sx + ChatColor.AQUA + " | " + ChatColor.GREEN + "Z: " + ChatColor.GOLD + sz);
                sender.sendMessage(ChatColor.GREEN + "Your current coordinates have been saved!");
                return true;
        }
        
        if (cmd.getName().equalsIgnoreCase("home") && (sender instanceof Player)) {
        	Player player = (Player) sender;
        	String self = player.getName();
        	int cx = this.getConfig().getInt(self + ".x");
        	int cz = this.getConfig().getInt(self + ".z");
        	sender.sendMessage(ChatColor.GREEN + "Your home coordinates are:");
        	sender.sendMessage(ChatColor.GREEN + "X: " + ChatColor.GOLD + cx + ChatColor.AQUA + " | " + ChatColor.GREEN + "Z: " + ChatColor.GOLD + cz);
        	return true;
        }
        
        if (cmd.getName().equalsIgnoreCase("hca") && (sender instanceof Player)) {
        	if (args.length > 0) {
        		if (args[0].equalsIgnoreCase("reload")) {
        			this.reloadConfig();
        			sender.sendMessage(ChatColor.YELLOW + "HomeCoords configuration reloaded!");
        			return true;
        		}
        		if (args[0].equalsIgnoreCase("get")) {
        			if (args.length > 0) {
            			Player p = Bukkit.getPlayer(args[1]);
            			String target = p.getName().toString();
                    	int tx = this.getConfig().getInt(target + ".x");
                    	int tz = this.getConfig().getInt(target + ".z");
                    	sender.sendMessage(ChatColor.GOLD + target + "'s" + ChatColor.GREEN + " home coordinates are:");
                    	sender.sendMessage(ChatColor.GREEN + "X: " + ChatColor.GOLD + tx + ChatColor.AQUA + " | " + ChatColor.GREEN + "Z: " + ChatColor.GOLD + tz);
                    	return true;
        			}
        			else {
        				sender.sendMessage(ChatColor.RED + "Usage: /hca get <player>");
        				return true;
        			}
        		}
        		if (args[0].equalsIgnoreCase("set")) {
        			if (args.length > 0) {
        				Player p = Bukkit.getPlayer(args[1]);
        				String target = p.getName().toString();
        				int tx = Integer.valueOf(args[2]);
        				int tz = Integer.valueOf(args[3]);
                    	this.getConfig().set(target + ".x", tx);
                    	this.getConfig().set(target + ".z", tz);
                    	this.saveConfig();
                    	sender.sendMessage(ChatColor.GREEN + "Player: " + ChatColor.GOLD + target + ChatColor.AQUA + " | " + ChatColor.GREEN + "X: " + ChatColor.GOLD + tx + ChatColor.AQUA + " | " + ChatColor.GREEN + "Z: " + ChatColor.GOLD + tz);
                        sender.sendMessage(ChatColor.GREEN + "Coordinates have been saved!");
        			}
        			else {
        				sender.sendMessage(ChatColor.RED + "Usage: /hca set <player> <x> <z>");
        			}
        			return true;
        		}
        	}
        	else {
        		sender.sendMessage(ChatColor.RED + "HomeCoords v1.0");
        		sender.sendMessage(ChatColor.RED + "Created by " + ChatColor.GREEN + "TheRealSimShady");
        		sender.sendMessage("-------------------------------------------------");
        		sender.sendMessage(ChatColor.GOLD + "Available options:");
        		sender.sendMessage(ChatColor.AQUA + "/hca get <player>");
        		sender.sendMessage(ChatColor.GRAY + "  Displays a player's coordinates.");
        		sender.sendMessage(ChatColor.AQUA + "/hca set <player> <x> <z>");
        		sender.sendMessage(ChatColor.GRAY + "  Set custom coordinates for an existing player.");
        		sender.sendMessage(ChatColor.AQUA + "/hca reload");
        		sender.sendMessage(ChatColor.GRAY + "  Reloads the configuration.");
        		
        		return true;
        	}
        }
        return false;
    }
}
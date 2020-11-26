package in.glennjam.homecoords;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Comparator;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

public class Main extends JavaPlugin {
	
	@Override
    public void onEnable() {
		saveDefaultConfig();
		getLogger().info("---------------------------------------");
		getLogger().info("HomeCoords v2.0 by TheRealSimShady");
		getLogger().info("HomeCoords is enabled!");
		getLogger().info("For support, DM Simsnet#1754 on Discord");
		getLogger().info("---------------------------------------");
	}
    @Override
    public void onDisable() {
		getLogger().info("---------------------------------------");
		getLogger().info("HomeCoords v2.0 by TheRealSimShady");
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
        	if (args.length == 1 && sender.hasPermission("homecoords.multiple") && sender instanceof Player) {
        		String shome = String.valueOf(args[0]);
                int sx = player.getLocation().getBlockX();
                int sz = player.getLocation().getBlockZ();
                this.getConfig().addDefault(self + ".homes", shome);
                this.getConfig().options().copyDefaults(true);
        		this.getConfig().set(self + ".homes." + shome + ".x", sx);
        		this.getConfig().set(self + ".homes." + shome + ".z", sz);
                this.saveConfig();
                sender.sendMessage(ChatColor.GREEN + "X: " + ChatColor.GOLD + sx + ChatColor.AQUA + " | " + ChatColor.GREEN + "Z: " + ChatColor.GOLD + sz);
                sender.sendMessage(ChatColor.GREEN + "Your current coordinates have been saved!");
                return true;
        	}
        	if (args.length == 1 && !(sender.hasPermission("homecoords.multiple")) && sender instanceof Player) {
        		sender.sendMessage(ChatColor.RED + "You are limited to 1 home only.  Please remove any arguments and try again.");
                return true;
        	}
        	else if (args.length == 0) {
        		String shome = "home";
                int sx = player.getLocation().getBlockX();
                int sz = player.getLocation().getBlockZ();
                this.getConfig().addDefault(self + ".homes", shome);
                this.getConfig().options().copyDefaults(true);
        		this.getConfig().set(self + ".homes." + shome + ".x", sx);
        		this.getConfig().set(self + ".homes." + shome + ".z", sz);
                this.saveConfig();
                sender.sendMessage(ChatColor.GREEN + "X: " + ChatColor.GOLD + sx + ChatColor.AQUA + " | " + ChatColor.GREEN + "Z: " + ChatColor.GOLD + sz);
                sender.sendMessage(ChatColor.GREEN + "Your current coordinates have been saved!");
                return true;
        	}
        	else {
        		sender.sendMessage(ChatColor.RED + "Usage: /sethome [home]");
        	}
            return true;
        }
        
        if (cmd.getName().equalsIgnoreCase("home") && (sender instanceof Player)) {
        	Player player = (Player) sender;
        	String self = player.getName();
        	if (args.length == 0) {
        			String shome = "home";
                	int cx = this.getConfig().getInt(self + ".homes." + shome + ".x");
                	int cz = this.getConfig().getInt(self + ".homes." + shome + ".z");
                	sender.sendMessage(ChatColor.GREEN + "Your home coordinates are:");
                	sender.sendMessage(ChatColor.GREEN + "X: " + ChatColor.GOLD + cx + ChatColor.AQUA + " | " + ChatColor.GREEN + "Z: " + ChatColor.GOLD + cz);
                	return true;
        	}
        	else if (args.length == 1 && !this.getConfig().contains(self + ".homes." + String.valueOf(args[0]))) {
        		sender.sendMessage(ChatColor.RED + "That home does not exist!  Check your arguments and try again.");
        		return true;
        	}
        	else if (args.length == 1) {
        		String shome = String.valueOf(args[0]);
        		int cx = this.getConfig().getInt(self + ".homes." + shome + ".x");
            	int cz = this.getConfig().getInt(self + ".homes." + shome + ".z");
        		sender.sendMessage(ChatColor.GREEN + "Your home " + ChatColor.GOLD + shome + "'s" + ChatColor.GREEN + " coordinates are:");
        		sender.sendMessage(ChatColor.GREEN + "X: " + ChatColor.GOLD + cx + ChatColor.AQUA + " | " + ChatColor.GREEN + "Z: " + ChatColor.GOLD + cz);
        		return true;
        	}
        	else {
        		sender.sendMessage(ChatColor.RED + "Usage: /home [home]");
        	}
        	return true;
        }
        
        if (cmd.getName().equalsIgnoreCase("hca") && (sender instanceof Player)) {
        	if (args.length > 0) {
        		if (args[0].equalsIgnoreCase("reload")) {
        			this.reloadConfig();
        			sender.sendMessage(ChatColor.GREEN + "HomeCoords configuration reloaded!");
        			return true;
        		}
        		if (args[0].equalsIgnoreCase("get")) {
        			if (args.length == 3) {
            			Player p = Bukkit.getPlayer(args[1]);
            			String target = p.getName().toString();
            			String thome = String.valueOf(args[2]);
                    	int tx = this.getConfig().getInt(target + ".homes." + thome + ".x");
                    	int tz = this.getConfig().getInt(target + ".homes." + thome + ".z");
                    	sender.sendMessage(ChatColor.GOLD + target + "'s" + ChatColor.GREEN + " home " + ChatColor.GOLD + thome + "'s" + ChatColor.GREEN + " coordinates are:");
                    	sender.sendMessage(ChatColor.GREEN + "X: " + ChatColor.GOLD + tx + ChatColor.AQUA + " | " + ChatColor.GREEN + "Z: " + ChatColor.GOLD + tz);
                    	return true;
        			}
        			else if (args.length < 3) {
        				sender.sendMessage(ChatColor.RED + "Usage: /hca get <player> <home>");
        				return true;
        			}
        		}
        		if (args[0].equalsIgnoreCase("set")) {
        			if (args.length == 5) {
        				Player p = Bukkit.getPlayer(args[1]);
        				String target = p.getName().toString();
        				String thome = String.valueOf(args[2]);
        				int tx = Integer.valueOf(args[3]);
        				int tz = Integer.valueOf(args[4]);
                    	this.getConfig().set(target + ".homes." + thome + ".x", tx);
                    	this.getConfig().set(target + ".homes." + thome + ".z", tz);
                    	this.saveConfig();
                    	sender.sendMessage(ChatColor.GREEN + "Player: " + ChatColor.GOLD + target + ChatColor.AQUA + " | " + ChatColor.GREEN + "Home: " + ChatColor.GOLD + thome + ChatColor.AQUA + " | " + ChatColor.GREEN + "X: " + ChatColor.GOLD + tx + ChatColor.AQUA + " | " + ChatColor.GREEN + "Z: " + ChatColor.GOLD + tz);
                        sender.sendMessage(ChatColor.GREEN + "Coordinates have been saved!");
        			}
        			else if (args.length < 5) {
        				sender.sendMessage(ChatColor.RED + "Usage: /hca set <player> <home> <x> <z>");
        			}
        			return true;
        		}
        		if (args[0].equalsIgnoreCase("reset")) {
        			if (args.length == 3) {
        				Player p = Bukkit.getPlayer(args[1]);
        				String target = p.getName().toString();
        				String thome = String.valueOf(args[2]);
                    	this.getConfig().set(target + ".homes." + thome + ".x", 0);
                    	this.getConfig().set(target + ".homes." + thome + ".z", 0);
                    	this.saveConfig();
                    	sender.sendMessage(ChatColor.GREEN + "Player: " + ChatColor.GOLD + target + ChatColor.AQUA + " | " + ChatColor.GREEN + "Home: " + ChatColor.GOLD + thome + ChatColor.AQUA + " | " + ChatColor.GREEN + "X: " + ChatColor.GOLD + "0" + ChatColor.AQUA + " | " + ChatColor.GREEN + "Z: " + ChatColor.GOLD + "0");
                        sender.sendMessage(ChatColor.GREEN + "Coordinates have been reset!");
        			}
        			else {
        				sender.sendMessage(ChatColor.RED + "Usage: /hca reset <player> <home> <x> <z>");
        			}
        			return true;
        		}
        		if (args[0].equalsIgnoreCase("list")) {
        			Player p = Bukkit.getPlayer(args[1]);
    				String target = p.getName().toString();
    				Set<String> homes = this.getConfig().getConfigurationSection(target + ".homes").getKeys(false);
    				String homelist = String.join(", ", homes);
    				sender.sendMessage(ChatColor.GREEN + target + "'s homes: " + ChatColor.GOLD + homelist);
    				return true;
        		}
        		else {
        			sender.sendMessage(ChatColor.RED + "Usage: /hca list <player>");
        		}
        	}
        	else {
        		sender.sendMessage("-------------------------------------------------");
        		sender.sendMessage(ChatColor.RED + "HomeCoords v2.0");
        		sender.sendMessage(ChatColor.RED + "Now with multiple homes and advanced admin features!");
        		sender.sendMessage(ChatColor.RED + "Created by " + ChatColor.GREEN + "TheRealSimShady");
        		sender.sendMessage("-------------------------------------------------");
        		sender.sendMessage(ChatColor.GOLD + "Available options:");
        		sender.sendMessage(ChatColor.AQUA + "/hca get <player> <home>");
        		sender.sendMessage(ChatColor.GRAY + "  Displays a player's coordinates.");
        		sender.sendMessage(ChatColor.AQUA + "/hca set <player> <home> <x> <z>");
        		sender.sendMessage(ChatColor.GRAY + "  Set custom coordinates for an existing player.");
        		sender.sendMessage(ChatColor.AQUA + "/hca reset <player> <home>");
        		sender.sendMessage(ChatColor.GRAY + "  Reset the coordinates for a player's home.");
        		sender.sendMessage(ChatColor.AQUA + "/hca list <player>");
        		sender.sendMessage(ChatColor.GRAY + "  List a player's saved homes.");
        		sender.sendMessage(ChatColor.AQUA + "/hca reload");
        		sender.sendMessage(ChatColor.GRAY + "  Reloads the configuration.");
        		
        		return true;
        	}
        }
        return false;
    }
}
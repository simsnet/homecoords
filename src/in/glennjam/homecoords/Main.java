package in.glennjam.homecoords;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
		getLogger().info("HomeCoords v2.5 by TheRealSimShady");
		getLogger().info("HomeCoords is enabled!");
		getLogger().info("For support, DM Simsnet#1754 on Discord");
		getLogger().info("---------------------------------------");
	}
    @Override
    public void onDisable() {
		getLogger().info("---------------------------------------");
		getLogger().info("HomeCoords v2.5 by TheRealSimShady");
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
        	// IF PLAYER HAS NAME AND DESCRIPTION SET
        	if (args.length > 1 && sender.hasPermission("homecoords.multiple") && sender instanceof Player) {
        		String shome = String.valueOf(args[0]);
        		StringBuilder desc = new StringBuilder();
				for(int i = 1; i < args.length; ++i) {
				     desc.append(args[i]).append(" ");
				}
        		String sdesc = desc.substring(0, desc.length() - 1);
                int sx = player.getLocation().getBlockX();
                int sz = player.getLocation().getBlockZ();
                this.getConfig().addDefault(self + ".homes", shome);
                this.getConfig().options().copyDefaults(true);
                this.getConfig().set(self + ".homes." + shome + ".description", sdesc);
        		this.getConfig().set(self + ".homes." + shome + ".x", sx);
        		this.getConfig().set(self + ".homes." + shome + ".z", sz);
                this.saveConfig();
                sender.sendMessage(ChatColor.GREEN + "--------- " + ChatColor.GOLD + "NEW HOME INFORMATION" + ChatColor.GREEN + " ---------");
                sender.sendMessage(ChatColor.GREEN + "Home: " + ChatColor.GOLD + shome);
                sender.sendMessage(ChatColor.GREEN + "Description: " + ChatColor.GOLD + sdesc);
                sender.sendMessage(ChatColor.GREEN + "X: " + ChatColor.GOLD + sx);
                sender.sendMessage(ChatColor.GREEN + "Z: " + ChatColor.GOLD + sz);
                sender.sendMessage(ChatColor.GREEN + "--------------------------------------");
                sender.sendMessage(ChatColor.GREEN + "Your current coordinates have been saved!");
                return true;
        	}
        	// IF PLAYER HAS NAME SET WITH NO DESCRIPTION
        	else if (args.length == 1 && sender.hasPermission("homecoords.multiple") && sender instanceof Player) {
        		String shome = String.valueOf(args[0]);
                int sx = player.getLocation().getBlockX();
                int sz = player.getLocation().getBlockZ();
                this.getConfig().addDefault(self + ".homes", shome);
                this.getConfig().options().copyDefaults(true);
                this.getConfig().set(self + ".homes." + shome + ".description", "N/A");
        		this.getConfig().set(self + ".homes." + shome + ".x", sx);
        		this.getConfig().set(self + ".homes." + shome + ".z", sz);
                this.saveConfig();
                sender.sendMessage(ChatColor.GREEN + "--------- " + ChatColor.GOLD + "NEW HOME INFORMATION" + ChatColor.GREEN + " ---------");
                sender.sendMessage(ChatColor.GREEN + "Home: " + ChatColor.GOLD + shome);
                sender.sendMessage(ChatColor.GREEN + "Description: " + ChatColor.GOLD + "N/A");
                sender.sendMessage(ChatColor.GREEN + "X: " + ChatColor.GOLD + sx);
                sender.sendMessage(ChatColor.GREEN + "Z: " + ChatColor.GOLD + sz);
                sender.sendMessage(ChatColor.GREEN + "--------------------------------------");
                sender.sendMessage(ChatColor.GREEN + "Your current coordinates have been saved!");
                return true;
        	}
        	// IF PLAYER HAS NAME OR DESCRIPTION BUT NO PERMISSIONS
        	else if (args.length > 0 && !(sender.hasPermission("homecoords.multiple")) && sender instanceof Player) {
        		sender.sendMessage(ChatColor.RED + "You are limited to 1 home only.  Please remove any arguments and try again.");
                return true;
        	}
        	// IF PLAYER HAS NO NAME OR DESCRIPTION
        	else if (args.length == 0) {
        		String shome = "home";
        		String sdesc = "Default home";
                int sx = player.getLocation().getBlockX();
                int sz = player.getLocation().getBlockZ();
                this.getConfig().addDefault(self + ".homes", shome);
                this.getConfig().options().copyDefaults(true);
                this.getConfig().set(self + ".homes." + shome + ".description", sdesc);
        		this.getConfig().set(self + ".homes." + shome + ".x", sx);
        		this.getConfig().set(self + ".homes." + shome + ".z", sz);
                this.saveConfig();
                sender.sendMessage(ChatColor.GREEN + "--------- " + ChatColor.GOLD + "NEW HOME INFORMATION" + ChatColor.GREEN + " ---------");
                sender.sendMessage(ChatColor.GREEN + "Home: " + ChatColor.GOLD + shome);
                sender.sendMessage(ChatColor.GREEN + "Description: " + ChatColor.GOLD + sdesc);
                sender.sendMessage(ChatColor.GREEN + "X: " + ChatColor.GOLD + sx);
                sender.sendMessage(ChatColor.GREEN + "Z: " + ChatColor.GOLD + sz);
                sender.sendMessage(ChatColor.GREEN + "--------------------------------------");
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
                	int cx = this.getConfig().getInt(self + ".homes.home.x");
                	int cz = this.getConfig().getInt(self + ".homes.home.z");
                	sender.sendMessage(ChatColor.GREEN + "----------- " + ChatColor.GOLD + "HOME INFORMATION" + ChatColor.GREEN + " -----------");
                    sender.sendMessage(ChatColor.GREEN + "Home: " + ChatColor.GOLD + "home");
                    sender.sendMessage(ChatColor.GREEN + "Description: " + ChatColor.GOLD + "Default home");
                    sender.sendMessage(ChatColor.GREEN + "X: " + ChatColor.GOLD + cx);
                    sender.sendMessage(ChatColor.GREEN + "Z: " + ChatColor.GOLD + cz);
                    sender.sendMessage(ChatColor.GREEN + "--------------------------------------");
                	return true;
        	}
        	else if (args.length == 1 && !this.getConfig().contains(self + ".homes." + String.valueOf(args[0]))) {
        		sender.sendMessage(ChatColor.RED + "That home does not exist!  Check your arguments and try again.");
        		return true;
        	}
        	else if (args.length == 1) {
        		String chome = String.valueOf(args[0]);
        		String cdesc = this.getConfig().getString(self + ".homes." + chome + ".description");
        		int cx = this.getConfig().getInt(self + ".homes." + chome + ".x");
            	int cz = this.getConfig().getInt(self + ".homes." + chome + ".z");
            	sender.sendMessage(ChatColor.GREEN + "----------- " + ChatColor.GOLD + "HOME INFORMATION" + ChatColor.GREEN + " -----------");
                sender.sendMessage(ChatColor.GREEN + "Home: " + ChatColor.GOLD + chome);
                sender.sendMessage(ChatColor.GREEN + "Description: " + ChatColor.GOLD + cdesc);
                sender.sendMessage(ChatColor.GREEN + "X: " + ChatColor.GOLD + cx);
                sender.sendMessage(ChatColor.GREEN + "Z: " + ChatColor.GOLD + cz);
                sender.sendMessage(ChatColor.GREEN + "--------------------------------------");
        		return true;
        	}
        	else {
        		sender.sendMessage(ChatColor.RED + "Usage: /home [home]");
        	}
        	return true;
        }
        
        if (cmd.getName().equalsIgnoreCase("hca")) {
        	if (args.length > 0) {
        		if (args[0].equalsIgnoreCase("info")) {
        			if (args.length == 3) {
        				Player p = Bukkit.getPlayer(args[1]);
                		String target = p.getName().toString();
            			String thome = String.valueOf(args[2]);
            			String tdesc = this.getConfig().getString(target + ".homes." + thome + ".description");
                    	int tx = this.getConfig().getInt(target + ".homes." + thome + ".x");
                    	int tz = this.getConfig().getInt(target + ".homes." + thome + ".z");
                    	sender.sendMessage(ChatColor.GREEN + "----------- " + ChatColor.GOLD + "HOME INFORMATION" + ChatColor.GREEN + " -----------");
                        sender.sendMessage(ChatColor.GREEN + "Home: " + ChatColor.GOLD + thome);
                        sender.sendMessage(ChatColor.GREEN + "Description: " + ChatColor.GOLD + tdesc);
                        sender.sendMessage(ChatColor.GREEN + "X: " + ChatColor.GOLD + tx);
                        sender.sendMessage(ChatColor.GREEN + "Z: " + ChatColor.GOLD + tz);
                        sender.sendMessage(ChatColor.GREEN + "--------------------------------------");
                        sender.sendMessage(ChatColor.DARK_RED + "NOTE: " + ChatColor.RED + "A non-existent home name will have a \"null\" description.");
                    	return true;
        			}
        			else if (args.length < 3) {
        				sender.sendMessage(ChatColor.RED + "Usage: /hca info <player> <home>");
        				return true;
        			}
        		}
        		if (args[0].equalsIgnoreCase("set")) {
        			// If description is set
        			if (args.length > 5) {
        				Player p = Bukkit.getPlayer(args[1]);
                		String target = p.getName().toString();
        				String thome = String.valueOf(args[2]);
        				int tx = Integer.valueOf(args[3]);
        				int tz = Integer.valueOf(args[4]);
        				StringBuilder desc = new StringBuilder();
        				for(int i = 5; i < args.length; ++i) {
        				     desc.append(args[i]).append(" ");
        				}
                		String tdesc = desc.substring(0, desc.length() - 1);
                		
        				this.getConfig().set(target + ".homes." + thome + ".description", tdesc);
                    	this.getConfig().set(target + ".homes." + thome + ".x", tx);
                    	this.getConfig().set(target + ".homes." + thome + ".z", tz);
                    	this.saveConfig();
                    	sender.sendMessage(ChatColor.GREEN + "--------- " + ChatColor.GOLD + "NEW HOME INFORMATION" + ChatColor.GREEN + " ---------");
                        sender.sendMessage(ChatColor.GREEN + "Home: " + ChatColor.GOLD + thome);
                        sender.sendMessage(ChatColor.GREEN + "Description: " + ChatColor.GOLD + tdesc);
                        sender.sendMessage(ChatColor.GREEN + "X: " + ChatColor.GOLD + tx);
                        sender.sendMessage(ChatColor.GREEN + "Z: " + ChatColor.GOLD + tz);
                        sender.sendMessage(ChatColor.GREEN + "--------------------------------------");
                        sender.sendMessage(ChatColor.GREEN + "Coordinates have been saved!");
        			}
        			else if (args.length == 5) {
        				// If only name is set
        				Player p = Bukkit.getPlayer(args[1]);
                		String target = p.getName().toString();
        				String thome = String.valueOf(args[2]);
        				int tx = Integer.valueOf(args[3]);
        				int tz = Integer.valueOf(args[4]);
        				this.getConfig().set(target + ".homes." + thome + ".description", "N/A");
                    	this.getConfig().set(target + ".homes." + thome + ".x", tx);
                    	this.getConfig().set(target + ".homes." + thome + ".z", tz);
                    	this.saveConfig();
                    	sender.sendMessage(ChatColor.GREEN + "--------- " + ChatColor.GOLD + "NEW HOME INFORMATION" + ChatColor.GREEN + " ---------");
                        sender.sendMessage(ChatColor.GREEN + "Home: " + ChatColor.GOLD + thome);
                        sender.sendMessage(ChatColor.GREEN + "Description: " + ChatColor.GOLD + "N/A");
                        sender.sendMessage(ChatColor.GREEN + "X: " + ChatColor.GOLD + tx);
                        sender.sendMessage(ChatColor.GREEN + "Z: " + ChatColor.GOLD + tz);
                        sender.sendMessage(ChatColor.GREEN + "--------------------------------------");
                        sender.sendMessage(ChatColor.GREEN + "Coordinates have been saved!");
        			}
        			else if (args.length < 5) {
        				sender.sendMessage(ChatColor.RED + "Usage: /hca set <player> <home> <x> <z> [description]");
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
                    	int tx = this.getConfig().getInt(target + ".homes." + thome + ".x");
                    	int tz = this.getConfig().getInt(target + ".homes." + thome + ".z");
                    	sender.sendMessage(ChatColor.GREEN + "--------- " + ChatColor.GOLD + "NEW HOME INFORMATION" + ChatColor.GREEN + " ---------");
                        sender.sendMessage(ChatColor.GREEN + "Home: " + ChatColor.GOLD + thome);
                        sender.sendMessage(ChatColor.GREEN + "Description: " + ChatColor.GOLD + "N/A");
                        sender.sendMessage(ChatColor.GREEN + "X: " + ChatColor.GOLD + tx);
                        sender.sendMessage(ChatColor.GREEN + "Z: " + ChatColor.GOLD + tz);
                        sender.sendMessage(ChatColor.GREEN + "--------------------------------------");
                        sender.sendMessage(ChatColor.GREEN + "Coordinates have been reset!");
        			}
        			else {
        				sender.sendMessage(ChatColor.RED + "Usage: /hca reset <player> <home>");
        			}
        			return true;
        		}
        		if (args[0].equalsIgnoreCase("list")) {
        			if (args.length > 1) {
        				Player p = Bukkit.getPlayer(args[1]);
                		String target = p.getName().toString();
        				Set<String> homes = this.getConfig().getConfigurationSection(target + ".homes").getKeys(false);
        				String homelist = String.join(", ", homes);
        				sender.sendMessage(ChatColor.GREEN + target + "'s homes: " + ChatColor.GOLD + homelist);
        				return true;
        			}
        			else {
        			sender.sendMessage(ChatColor.RED + "Usage: /hca list <player>");
        			return true;
        			}
        		}
        		
        		if (args[0].equalsIgnoreCase("reload")) {
        			this.reloadConfig();
        			sender.sendMessage(ChatColor.GREEN + "HomeCoords configuration reloaded!");
        			return true;
        		}
        		else {
            		sender.sendMessage(ChatColor.RED + "Usage: /hca <info/set/reset/list/reload>");
            		return true;
            	}
        	} else {
        		sender.sendMessage("-------------------------------------------------");
        		sender.sendMessage(ChatColor.RED + "HomeCoords v2.5");
        		sender.sendMessage(ChatColor.RED + "Now with multiple homes, descriptions, and advanced admin features!");
        		sender.sendMessage(ChatColor.RED + "Created by " + ChatColor.GREEN + "TheRealSimShady");
        		sender.sendMessage("-------------------------------------------------");
        		sender.sendMessage(ChatColor.GOLD + "Available options:");
        		sender.sendMessage(ChatColor.AQUA + "/hca info <player> <home>");
        		sender.sendMessage(ChatColor.GRAY + "  Get information about a player's home.");
        		sender.sendMessage(ChatColor.AQUA + "/hca set <player> <home> <x> <z> [description]");
        		sender.sendMessage(ChatColor.GRAY + "  Set custom coordinates for an existing player's home.");
        		sender.sendMessage(ChatColor.AQUA + "/hca reset <player> <home>");
        		sender.sendMessage(ChatColor.GRAY + "  Reset the coordinates for a player's home.");
        		sender.sendMessage(ChatColor.AQUA + "/hca list <player>");
        		sender.sendMessage(ChatColor.GRAY + "  List a player's homes.");
        		sender.sendMessage(ChatColor.AQUA + "/hca reload");
        		sender.sendMessage(ChatColor.GRAY + "  Reloads the configuration.");
        		return true;
        	}
    	}
        return false;
    }
}
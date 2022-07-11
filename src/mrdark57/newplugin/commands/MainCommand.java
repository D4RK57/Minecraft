package mrdark57.newplugin.commands;

import mrdark57.newplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {

    private Main plugin;

    public MainCommand(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            Bukkit.getConsoleSender().sendMessage(plugin.name + ChatColor.RED + " You cannot execute this command from the console.");
            return false;
        }else{
            Player player = (Player) sender;
            // /miplugin (0 args)
            // /miplugin hola (1 args)
            // /miplugin hola de nuevo (3 args)

            if (args.length > 0){
                // /miplugin version
                if (args[0].equalsIgnoreCase("version")){
                    player.sendMessage(plugin.name + ChatColor.AQUA+" Plugin version: " + ChatColor.WHITE + plugin.version);
                    return true;
                }
                // /miplugin spawn
                else if (args[0].equalsIgnoreCase("spawn")){
                    FileConfiguration config = plugin.getConfig();
                    if (config.contains("Config.Spawn.x")){
                        double x = Double.valueOf(config.getString("Config.Spawn.x"));
                        double y = Double.valueOf(config.getString("Config.Spawn.y"));
                        double z = Double.valueOf(config.getString("Config.Spawn.z"));
                        World world = plugin.getServer().getWorld(config.getString("Config.Spawn.world"));
                        Location location = new Location(world, x, y, z); // (mundo,x,y,z,yaw,pitch) yaw & pitch= rotación del jugador, yaw= , pitch=
                        player.teleport(location);
                        player.sendMessage(plugin.name + ChatColor.GREEN+" You has been teleported to the spawn!");
                        return true;
                    }
                    else {
                        player.sendMessage(plugin.name + ChatColor.RED + " The spawn not exist.");
                        return true;
                    }

                }
                else if (args[0].equalsIgnoreCase("setspawn")){
                    Location location = player.getLocation();
                    double x = location.getX();
                    double y = location.getY();
                    double z = location.getZ();
                    String world = location.getWorld().getName();

                    FileConfiguration config = plugin.getConfig();
                    config.set("Config.Spawn.x",x);
                    config.set("Config.Spawn.y",y);
                    config.set("Config.Spawn.z",z);
                    config.set("Config.Spawn.world",world);
                    plugin.saveConfig();

                    player.sendMessage(plugin.name + ChatColor.GREEN + " Spawn has been placed in (" + x + " ," + y + " ," + z + ")!");
                    return true;
                }
                // /miplugin reload
                else if (args[0].equalsIgnoreCase("reload")){
                    plugin.reloadConfig();
                    plugin.reloadMessages();
                    player.sendMessage(plugin.name + ChatColor.GREEN+ " Reload complete.");
                    return true;
                }
                else{
                    player.sendMessage(plugin.name + ChatColor.RED + " That command does not exist.");
                    return true;
                }
            }else{
                player.sendMessage(plugin.name + ChatColor.RED+ " Use /newplugin version to see the plugin version.");
                return true;
            }
        }
    }

}

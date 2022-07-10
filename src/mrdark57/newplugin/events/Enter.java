package mrdark57.newplugin.events;

import mrdark57.newplugin.NewPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Event implements Listener {

    private NewPlugin plugin;

    public Enter (NewPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void toEnter(PlayerJoinEvent event){
        Player player = event.getPlayer();
        Location location = new Location(player.getWorld(), 0, 90, 0, -90, 0); // (mundo,x,y,z,yaw,pitch) yaw & pitch= rotación del jugador, yaw= , pitch=
        player.teleport(location);

        FileConfiguration config = plugin.getConfig();
        String path = "Config.welcome-message";
        if (config.getString(path).equals("true")){
            String text = "Config.welcome-message-text";
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString(text)).replaceAll("%player%", player.getName()));
        }

    }

}

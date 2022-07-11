package mrdark57.newplugin.events;

import mrdark57.newplugin.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class Enter implements Listener {

    private Main plugin;

    public Enter (Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        FileConfiguration config = plugin.getConfig();

        if (config.contains("Config.spawn.x")){
            double x = Double.valueOf(config.getString("Config.Spawn.x"));
            double y = Double.valueOf(config.getString("Config.Spawn.y"));
            double z = Double.valueOf(config.getString("Config.Spawn.z"));
            World world = plugin.getServer().getWorld(config.getString("Config.spawn.world"));
            Location location = new Location(world, x, y, z); // (mundo,x,y,z,yaw,pitch) yaw & pitch= rotación del jugador, yaw= , pitch=
            player.teleport(location);
        }

        String path = "Config.welcome-msg"; //ruta
        if (config.getString(path).equals("true")){
            FileConfiguration messages = plugin.getMessages();

            List<String> msg = messages.getStringList("Message.welcome-msg-text");
            for (int i = 0; i < msg.size(); i++) {
                String text = msg.get(i);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', text.replaceAll("%player%", player.getName())));
            }
        }

    }

    /* -------------- Funciones avanzadas ----------------
    1. Parámetros de EventHandler:
        priority: Indica la prioridad de su oyente. Existen seis prioridades diferentes,
            en orden de ejecución: LOWEST,LOW,NORMAL[default],HIGH,HIGHEST,MONITOR.
            MONITOR solo debe usarse para lectura. Es útil para registrar complementos para
            ver los resultados de un evento y la modificación de valores puede interferir con
            esos tipos de complementos.
        ignoreCancelled: un booleano que indica si su oyente debe activarse o no si el evento
            ha sido cancelado antes de que sea el turno del oyente para manejar el evento.
            Falso por defecto.

    Se ejecuta antes que el segundo método porque tiene una prioridad mucho menor.

    @EventHandler (priority = EventPriority.LOWEST)
    public void onPlayerChat1(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
    }

    No se ejecutará a menos que otro oyente con una prioridad más baja haya cancelado el evento.

    @EventHandler (priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerChat2(AsyncPlayerChatEvent event) {
        System.out.println("This shouldn't be executing.");
    }*/

}

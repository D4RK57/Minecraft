package mrdark57.newplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class MyListener implements Listener {

    // Clase para estar a la escucha de algún evento que ocurre

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Bukkit.broadcastMessage("Welcome to the server" + event.getPlayer().getName() + "!");
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
            Falso por defecto.                              */

    // Se ejecuta antes que el segundo método porque tiene una prioridad mucho menor.
    @EventHandler (priority = EventPriority.LOWEST)
    public void onPlayerChat1(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
    }

    // no se ejecutará a menos que otro oyente con una prioridad más baja haya cancelado el evento.
    @EventHandler (priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerChat2(AsyncPlayerChatEvent event) {
        System.out.println("This shouldn't be executing.");
    }


}

package mrdark57.newplugin.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MyEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private final String playerName;
    private boolean isCancelled;

    //Necesita estos métodos porque Spigot usa la clase HandlerList para separar
    // otros EventHandlers de escuchar otros eventos.

    public MyEvent(String playerName) {
        this.playerName = playerName;
        this.isCancelled = false;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }
}

package mrdark57.newplugin;

import mrdark57.newplugin.commands.Kit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info(ChatColor.GREEN + "Enabled " + this.getName());

        // Registra el comando "kit" (establecer una instancia de su clase de comando como ejecutor)
        this.getCommand("kit").setExecutor(new Kit());
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(ChatColor.RED + "Disabled " + this.getName());
    }

}

package mrdark57.newplugin;

import mrdark57.newplugin.commands.Kit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class NewPlugin extends JavaPlugin {

    public String configRoute;
    PluginDescriptionFile plDescription = getDescription();
    public String version = plDescription.getVersion();
    public String name = ChatColor.YELLOW + "[" + plDescription.getName() + "]"; // [NombrePlugin]

    @Override
    public void onEnable() {
        Bukkit.getLogger().info(name + ChatColor.AQUA +" has been enabled (version: " + ChatColor.RED + version + ")");

        registerCommands();
        registerEvents();
        registerConfig();

        // Registra el comando "kit" (establecer una instancia de su clase de comando como ejecutor)
        this.getCommand("kit").setExecutor(new Kit());

        // Registra el listener
        getServer().getPluginManager().registerEvents(new MyListener(), this);
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(name + ChatColor.RED + " has been disabled");
    }

    //registrar comandos
    public void registerCommands(){
        this.getCommand("miplugin").setExecutor(new MainCommand(this));
    }

    //registrar eventos
    public void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new Enter(this), this);
    }

    //registrar la config
    public void registerConfig(){
        File config = new File(this.getDataFolder(),"config.yml");
        configRoute = config.getPath();
        if (!config.exists()){
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }

}

package mrdark57.newplugin;

import mrdark57.newplugin.commands.Kit;
import mrdark57.newplugin.commands.MainCommand;
import mrdark57.newplugin.events.Enter;
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
        Bukkit.getLogger().info(ChatColor.YELLOW + "----------------------------------------");
        Bukkit.getLogger().info(name + ChatColor.AQUA +" has been enabled (version: " + ChatColor.RED + version + ")");
        Bukkit.getLogger().info(ChatColor.YELLOW + "----------------------------------------");

        registerCommands();
        registerEvents();
        registerConfig();
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(ChatColor.YELLOW + "----------------------------------------");
        Bukkit.getLogger().info(name + ChatColor.RED + " has been disabled");
        Bukkit.getLogger().info(ChatColor.YELLOW + "----------------------------------------");
    }

    //registrar comandos
    public void registerCommands(){
        this.getCommand("newplugin").setExecutor(new MainCommand(this));
        this.getCommand("kit").setExecutor(new Kit(this));
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

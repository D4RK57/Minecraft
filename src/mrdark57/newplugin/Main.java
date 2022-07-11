package mrdark57.newplugin;

import mrdark57.newplugin.commands.Kit;
import mrdark57.newplugin.commands.MainCommand;
import mrdark57.newplugin.events.Enter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.imageio.IIOException;
import java.io.*;

public class Main extends JavaPlugin {

    public String configRoute;
    PluginDescriptionFile plDescription = getDescription();
    public String version = plDescription.getVersion();
    public String name = ChatColor.YELLOW + "[" + plDescription.getName() + "]"; // [NombrePlugin]

    private FileConfiguration messages = null;
    private File messagesFile = null;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "----------------------------------------");
        Bukkit.getConsoleSender().sendMessage(name + ChatColor.AQUA +" has been enabled (version: " + ChatColor.RED + version + ")");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "----------------------------------------");

        registerCommands();
        registerEvents();
        registerConfig();
        registerMessages();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "----------------------------------------");
        Bukkit.getConsoleSender().sendMessage(name + ChatColor.RED + " has been disabled");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "----------------------------------------");
    }

    //registrar comandos
    public void registerCommands() {
        this.getCommand("newplugin").setExecutor(new MainCommand(this));
        this.getCommand("kit").setExecutor(new Kit(this));
    }

    //registrar eventos
    public void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new Enter(this), this);
    }

    //registrar la config
    public void registerConfig() {
        File config = new File(this.getDataFolder(),"config.yml");
        configRoute = config.getPath();
        if (!config.exists()){
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }

    public FileConfiguration getMessages() {
        if (messages == null) {
            reloadMessages();
        }
        return messages;
    }

    // recargar mensajes
    public void reloadMessages() {
        //si no existe el archivo, lo crea
        if (messages == null) {
            messagesFile = new File(getDataFolder(),"messages.yml");
        }

        messages = YamlConfiguration.loadConfiguration(messagesFile);
        Reader defConfigStream;

        try {
            defConfigStream = new InputStreamReader(this.getResource("messages.yml"),"UTF8");
            if (defConfigStream != null) {
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                messages.setDefaults(defConfig);
            }
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }

    // Guardar mensajes
    public void saveMessages() {
        try {
            messages.save(messagesFile);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Registrar menssajes
    public void registerMessages() {
        messagesFile = new File(this.getDataFolder(),"messages.yml");
        if (!messagesFile.exists()) {
            this.getMessages().options().copyDefaults(true);
            saveMessages();
        }
    }

}

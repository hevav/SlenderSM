package dev.hevav.slendersm;

import dev.hevav.slendersm.commands.SlenderMaxCount;
import dev.hevav.slendersm.commands.SlenderToggle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class SlenderSM extends JavaPlugin {
    public static FileConfiguration config;
    private static SlenderSM instance;

    public static SlenderSM getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        config = this.getConfig();
        instance = this;

        this.getCommand("slendtog").setExecutor(new SlenderToggle());
        this.getCommand("slendmcount").setExecutor(new SlenderMaxCount());
    }

    @Override
    public void onDisable(){
        this.saveConfig();
    }
}
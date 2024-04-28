package org.lonjas.menusystem;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.util.Objects;

public final class MenuSystem extends JavaPlugin implements Listener{
    private FileConfiguration messagesConfig;

    public FileConfiguration getMessagesConfig() {
        return this.messagesConfig;
    }
    @Override
    public void onEnable() {

        File menuFolder = new File(getDataFolder(), "menu");
        if (!menuFolder.exists()) {
            menuFolder.mkdirs();
        }


        File defaultMenuFile = new File(menuFolder, "default.yml");
        if (!defaultMenuFile.exists()) {
            saveResource("menu/default.yml", false);
        }

        File messagesFile = new File(getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            saveResource("messages.yml", false);
        }
        this.messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);

        Bukkit.getPluginManager().registerEvents(this, this);

        MenuSlots menuSlots = new MenuSlots(this);
        Objects.requireNonNull(this.getCommand("menu-open")).setExecutor(menuSlots);
        Bukkit.getPluginManager().registerEvents(new MenuListener(this, menuSlots), this);

        Objects.requireNonNull(this.getCommand("menu-version")).setExecutor(new MenuVersionCommand(this));
        Objects.requireNonNull(this.getCommand("menu-delete")).setExecutor(new MenuDeleteCommand(this));
        Objects.requireNonNull(this.getCommand("menu-list")).setExecutor(new MenuListCommand(this));
        Objects.requireNonNull(this.getCommand("menu-reload")).setExecutor(new ReloadCommand(this));
        Objects.requireNonNull(this.getCommand("menu-create")).setExecutor(new CreateMenuCommand(this));


        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            getLogger().fine("We detected PlaceholderAPI, registering events.");
        } else {
            getLogger().warning("Could not find PlaceholderAPI! This plugin is required.");
        }
    }
}

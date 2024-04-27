package org.lonjas.menusystem;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

import java.io.File;
import java.util.Objects;

public final class MenuSystem extends JavaPlugin implements Listener{

    @Override
    public void onEnable() {
        // Crear la carpeta "menu" si no existe
        File menuFolder = new File(getDataFolder(), "menu");
        if (!menuFolder.exists()) {
            menuFolder.mkdirs();
        }

        // Crear el archivo "default.yml" si no existe
        File defaultMenuFile = new File(menuFolder, "default.yml");
        if (!defaultMenuFile.exists()) {
            saveResource("menu/default.yml", false);
        }

        Bukkit.getPluginManager().registerEvents(this, this);
        Objects.requireNonNull(this.getCommand("menu-reload")).setExecutor(new ReloadCommand(this));
        Bukkit.getPluginManager().registerEvents(new MenuListener(), this);
        Objects.requireNonNull(this.getCommand("menu")).setExecutor(new MenuSlots(this));
        Objects.requireNonNull(this.getCommand("createmenu")).setExecutor(new CreateMenuCommand(this));


        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            getLogger().fine("We detected PlaceholderAPI, registering events.");
        } else {
            getLogger().warning("Could not find PlaceholderAPI! This plugin is required.");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {

        String joinText = PlaceholderAPI.setPlaceholders(event.getPlayer(), ChatColor.translateAlternateColorCodes('&',"&f%player_name% &ejoined the server! They are rank %vault_prefix%"));
        event.setJoinMessage(joinText+"§r");

    }
}

package org.lonjas.menusystem;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class CreateMenuCommand implements CommandExecutor {
    private final MenuSystem plugin;

    public CreateMenuCommand(MenuSystem plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration messagesConfig = plugin.getMessagesConfig();
        if (args.length == 1) {
            String menuName = args[0];
            File menuFile = new File(plugin.getDataFolder() + "/menu", menuName + ".yml");
            if (!menuFile.exists()) {
                try {
                    // Copia el contenido de default.yml del JAR del plugin al nuevo archivo de menú
                    plugin.saveResource("menu/default.yml", false);
                    Files.copy(new File(plugin.getDataFolder(), "menu/default.yml").toPath(), menuFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes( '&' ,plugin.getMessagesConfig().getString("menu-prefix") +  plugin.getMessagesConfig().getString("menu-create")) + menuName);
                } catch (IOException e) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes( '&' ,plugin.getMessagesConfig().getString("menu-prefix") +  plugin.getMessagesConfig().getString("menu-create-error")));
                    e.printStackTrace();
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes( '&' ,plugin.getMessagesConfig().getString("menu-prefix") +  plugin.getMessagesConfig().getString("menu-already-exists")));
            }
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes( '&' ,plugin.getMessagesConfig().getString("menu-prefix") +  plugin.getMessagesConfig().getString("menu-create-usage")));
        }
        return true;
    }
}
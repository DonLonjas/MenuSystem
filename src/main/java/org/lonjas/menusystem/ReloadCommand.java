package org.lonjas.menusystem;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class ReloadCommand implements CommandExecutor {
    private final MenuSystem plugin;

    public ReloadCommand(MenuSystem plugin) {
        this.plugin = plugin;
    }


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration messagesConfig = plugin.getMessagesConfig();
        if (command.getName().equalsIgnoreCase("menu-reload")) {
            if (sender.hasPermission("menusystem.reload")) {
                plugin.reloadConfig();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getMessagesConfig().getString("menu-prefix") + plugin.getMessagesConfig().getString("menu-config-reload")));
                return true;
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getMessagesConfig().getString("menu-prefix") + plugin.getMessagesConfig().getString("menu-config-reload-error")));
                return true;
            }
        }
        return false;
    }

}

package org.lonjas.menusystem;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {
    private final MenuSystem plugin;

    public ReloadCommand(MenuSystem plugin) {
        this.plugin = plugin;
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("menu-reload")) {
            if (sender.hasPermission("menusystem.reload")) {
                plugin.reloadConfig();
                sender.sendMessage(ChatColor.GREEN + "MenuSystem plugin has been reloaded.");
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "You do not have permission to reload the plugin.");
                return true;
            }
        }
        return false;
    }

}

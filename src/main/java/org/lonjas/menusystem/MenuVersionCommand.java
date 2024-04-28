package org.lonjas.menusystem;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class MenuVersionCommand implements CommandExecutor {
    private final MenuSystem plugin;

    public MenuVersionCommand(MenuSystem plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        String version = plugin.getDescription().getVersion();
        sender.sendMessage(ChatColor.GREEN + "MenuSystem version: " + version);
        return true;
    }
}
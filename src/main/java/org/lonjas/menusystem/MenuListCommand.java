package org.lonjas.menusystem;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import java.io.File;

public class MenuListCommand implements CommandExecutor {
    private final MenuSystem plugin;

    public MenuListCommand(MenuSystem plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        File menuDirectory = new File(plugin.getDataFolder() + "/menu");
        File[] menuFiles = menuDirectory.listFiles();

        if (menuFiles != null) {
            sender.sendMessage(ChatColor.GREEN + "Available menus:");
            for (File menuFile : menuFiles) {
                String menuName = menuFile.getName().replace(".yml", "");
                sender.sendMessage(ChatColor.GREEN + "- " + menuName);
            }
        } else {
            sender.sendMessage(ChatColor.RED + "No menus available.");
        }

        return true;
    }
}
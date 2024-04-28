package org.lonjas.menusystem;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import java.io.File;

public class MenuDeleteCommand implements CommandExecutor {
    private final MenuSystem plugin;

    public MenuDeleteCommand(MenuSystem plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /menu-delete <menuName>");
            return false;
        }

        String menuName = args[0];
        File menuFile = new File(plugin.getDataFolder() + "/menu", menuName + ".yml");

        if (menuFile.exists()) {
            boolean deleted = menuFile.delete();

            if (deleted) {
                sender.sendMessage(ChatColor.GREEN + "Menu '" + menuName + "' has been deleted.");
            } else {
                sender.sendMessage(ChatColor.RED + "An error occurred while deleting the menu.");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Menu '" + menuName + "' does not exist.");
        }

        return true;
    }
}
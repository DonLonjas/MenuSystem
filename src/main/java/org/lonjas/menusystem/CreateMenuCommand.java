package org.lonjas.menusystem;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

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
        if (args.length == 1) {
            String menuName = args[0];
            File menuFile = new File(plugin.getDataFolder() + "/menu", menuName + ".yml");
            if (!menuFile.exists()) {
                try {

                    plugin.saveResource("menu/default.yml", false);
                    Files.copy(new File(plugin.getDataFolder(), "menu/default.yml").toPath(), menuFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    sender.sendMessage(ChatColor.GREEN + "Menu " + menuName + " has been created.");
                } catch (IOException e) {
                    sender.sendMessage(ChatColor.RED + "An error occurred while creating the menu.");
                    e.printStackTrace();
                }
            } else {
                sender.sendMessage(ChatColor.RED + "A menu with that name already exists.");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Usage: /createmenu <name>");
        }
        return true;
    }
}

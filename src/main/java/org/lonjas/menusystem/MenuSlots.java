package org.lonjas.menusystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class MenuSlots implements CommandExecutor {
    private final MenuSystem plugin;

    public MenuSlots(MenuSystem plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label, String[] args) {
        if(sender instanceof Player player){
            if (args.length == 1) {
                String menuName = args[0];
                File menuFile = new File(plugin.getDataFolder() + "/menu", menuName + ".yml");
                if (menuFile.exists()) {
                    FileConfiguration menuConfig = YamlConfiguration.loadConfiguration(menuFile);
                    int size = menuConfig.getInt("size");

                    Inventory inv = Bukkit.createInventory(null, size, ChatColor.translateAlternateColorCodes('&', menuConfig.getString("name")));
                    for (int i = 0; i < size; i++) {
                        String iString = String.valueOf(i + 1);
                        if (menuConfig.contains("items.item"+ iString + ".material")) {
                            String materialName = menuConfig.getString("items.item"+ iString + ".material");
                            Material itemMaterial = Material.getMaterial(materialName);
                            if (itemMaterial != null) {
                                ItemStack itemStack = new ItemStack(itemMaterial);
                                ItemMeta itemMeta = itemStack.getItemMeta();
                                assert itemMeta != null;
                                itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', menuConfig.getString("items.item"+ iString + ".name")));
                                itemMeta.setLore(List.of(ChatColor.translateAlternateColorCodes('&', Arrays.toString(menuConfig.getStringList("items.item"+ iString + ".lore").toArray(new String[0])))));
                                itemStack.setItemMeta(itemMeta);
                                inv.setItem(menuConfig.getInt("items.item"+ iString + ".slot"), itemStack);
                            }
                            else {
                                System.out.println("Material " + materialName + " does not exist.");
                            }

                        } else {
                            String frameItemString = menuConfig.getString("frame-item");
                            assert frameItemString != null;
                            Material frameItem = Material.getMaterial(frameItemString);
                            assert frameItem != null;
                            ItemStack frame = new ItemStack(frameItem);
                            inv.setItem(i, frame);
                        }
                    }

                    player.openInventory(inv);
                }else {
                    player.sendMessage(ChatColor.RED + "A menu with that name does not exist.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Usage: /menu <name>");
            }
        }
        return true;
    }
}

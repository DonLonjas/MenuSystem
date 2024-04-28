package org.lonjas.menusystem;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import java.io.File;
import java.util.List;

public class MenuListener implements Listener {

    private MenuSystem plugin;
    private MenuSlots menuSlots;

    public MenuListener(MenuSystem plugin, MenuSlots menuSlots) {
        this.plugin = plugin;
        this.menuSlots = menuSlots;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        String menuName = menuSlots.getMenuName();

        File menuFile = new File(plugin.getDataFolder() + "/menu", menuName + ".yml");
        if (menuFile.exists()) {
            e.setCancelled(true);
            FileConfiguration menuConfig = YamlConfiguration.loadConfiguration(menuFile);
            int size = menuConfig.getInt("size");
            Player player = (Player) e.getWhoClicked();
            for (int i = 0; i < size; i++) {
                String iString = String.valueOf(i);
                if (menuConfig.contains("items.item-" + iString + ".commands")) {
                    List<String> commands = menuConfig.getStringList("items.item-" + iString + ".commands");
                    if (commands != null) {
                        if (e.getRawSlot() == i) {
                            for (String command : commands) {
                                String commandToExecute = command.replace("{player}", player.getName());
                                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), commandToExecute);
                            }
                        }
                    }
                }
            }
        }

        // Si el clic ocurrió fuera del inventario, no cancelar el evento
        if (e.getRawSlot() >= e.getInventory().getSize()) {
            e.setCancelled(false);
        }
    }
}

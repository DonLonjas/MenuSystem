package org.lonjas.menusystem;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Random;

public class MenuListener implements Listener {

    private Location randomLocation(World world) {
        Random random = new Random();

        int x = random.nextInt(1000) - 1500;
        int z = random.nextInt(1000) - 1500;
        int y = world.getHighestBlockYAt(x, z);

        return new Location(world, x, y, z);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){

        if (ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(ChatColor.RED.toString() + ChatColor.BOLD + "Menu")
                && e.getCurrentItem() != null){
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();
            switch (e.getRawSlot()){
                case 0:
                    player.sendMessage(ChatColor.RED + "Closing menu...");
                    player.closeInventory();
                    break;
                case 20:
                    player.teleport(randomLocation(player.getWorld()));
                    player.sendMessage(ChatColor.GREEN + "Teleporting...");
                    player.closeInventory();
                    break;
                case 22:
                    player.closeInventory();
                    player.setHealth(0);
                    player.sendMessage(ChatColor.RED + "You have commited suicide.");
                    break;
                case 24:
                    player.closeInventory();
                    player.getInventory().clear();
                    player.sendMessage(ChatColor.RED + "You inventory has been cleared.");
                    break;
                default:
                    break;


            }

        }

    }


}

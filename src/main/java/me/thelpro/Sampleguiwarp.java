package me.thelpro;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class Sampleguiwarp extends JavaPlugin implements CommandExecutor, Listener {

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(this,this);
        getCommand("warp").setExecutor(this);

    }

    //Create item and rename method
    public ItemStack createRenameItem(Material material, String name) {

        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);

        return item;
    }

    public ItemStack blank = createRenameItem(Material.GRAY_STAINED_GLASS_PANE, "");

    //Click event

    @EventHandler
    public void clickEvent(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();

        if (e.getClickedInventory().containsAtLeast(blank, 6)) {

            if (e.getCurrentItem().getType().equals(Material.GRASS_BLOCK)) {
                player.teleport(new Location(player.getWorld(), 0, 4, 0));
                player.sendMessage("Welcome to spawn!");
            } else if (e.getCurrentItem().getType().equals(Material.RED_BED)) {
                player.teleport(new Location(player.getWorld(), 100, 4, 100));
                player.sendMessage("Home sweet home!");
            } else if (e.getCurrentItem().getType().equals(Material.GOLD_NUGGET)) {
                player.teleport(new Location(player.getWorld(), 50, 4, 50));
                player.sendMessage("Time to spend some money!");
            }

            e.setCancelled(true);
        }
    }

    //Command for opening GUI

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        Inventory gui = Bukkit.createInventory(player, 9, ChatColor.YELLOW + "Warp Menu");

        ItemStack[] items = {
                blank,
                blank,
                blank,
                createRenameItem(Material.GRASS_BLOCK, "Spawn"),
                createRenameItem(Material.GOLD_NUGGET, "Shops"),
                createRenameItem(Material.RED_BED, "Home"),
                blank,
                blank,
                blank
        };
        gui.setContents(items);

        player.openInventory(gui);

        return true;
    }
}
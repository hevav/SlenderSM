package dev.hevav.slendersm.listeners;

import dev.hevav.slendersm.SlenderSM;
import dev.hevav.slendersm.helpers.SlenderHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

public class SlenderListener implements Listener {
    @EventHandler(priority = EventPriority.NORMAL)
    public static void onEvent(PlayerInteractAtEntityEvent event){
        if (event.getPlayer().hasPermission("slendersm.beslender") && event.getRightClicked() instanceof Player){
            Player player = event.getPlayer();
            Player damager = (Player) event.getRightClicked();

            SlenderHelper.addDamaged(player, damager);
        }

        if (event.getRightClicked() instanceof ItemFrame){
            event.getRightClicked().remove();
            ++SlenderHelper.count;

            int maxCount = SlenderSM.config.getInt("maxcount");
            if (SlenderHelper.count == maxCount)
                Bukkit.getOnlinePlayers().forEach(p -> p.getInventory().addItem(new ItemStack(Material.STONE_BUTTON)));
            else
                Bukkit.broadcastMessage(String.format("%sИгрок %s собрал записку (%d/%d)", ChatColor.GOLD, event.getPlayer().getName(), SlenderHelper.count, maxCount));
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public static void onEvent(BlockBreakEvent event){
        if (!event.getPlayer().hasPermission("slendersm.beslender"))
            event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public static void onEvent(BlockPlaceEvent event){
        if (event.getBlock().getType() == Material.STONE_BUTTON)
            return;

        if (!event.getPlayer().hasPermission("slendersm.beslender"))
            event.setCancelled(true);
    }
}

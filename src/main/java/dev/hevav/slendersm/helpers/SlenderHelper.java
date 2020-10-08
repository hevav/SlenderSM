package dev.hevav.slendersm.helpers;

import dev.hevav.slendersm.SlenderSM;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SlenderHelper {
    public static int count = 0;
    private static HashMap<Player, List<Player>> damaged = new HashMap<>();

    public static void addDamaged(Player player, Player damager){
        PlayerInventory inventory = damager.getInventory();
        if (!inventory.contains(Material.COMPASS))
            inventory.addItem(new ItemStack(Material.COMPASS));

        if (!damaged.containsKey(damager))
            damaged.put(damager, new ArrayList<>()).add(player);
        else
            damaged.get(damager).add(player);
    }

    public static void proceedCompass(){
        Bukkit.getScheduler().scheduleSyncDelayedTask(
                SlenderSM.getInstance(),
                () ->
                    damaged.forEach(((player, players) ->
                        player.setCompassTarget(findNearest(player, players).getLocation()))),
                5L
        );
    }

    public static void disableCompass(){
        Bukkit.getScheduler().cancelTasks(SlenderSM.getInstance());
        damaged.clear();
    }

    private static Player findNearest(Player target, List<Player> players){
        Location targetLoc = target.getLocation();

        double dist = Double.MAX_VALUE;
        int ind = -1;

        for (int i = 0; i < players.size(); i++) {
            if (players.get(i) == target)
                continue;

            double distMarker = players.get(i).getLocation().distance(targetLoc);
            if (distMarker < dist){
                dist = distMarker;
                ind = i;
            }
        }

        return players.get(ind);
    }
}

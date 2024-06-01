package org.se0k.learnminigame;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class HungerEvent implements Listener {
    @EventHandler
    public void hunger(FoodLevelChangeEvent event) {
        Player player = (Player) event.getEntity();

        World world = player.getWorld();

        if (!world.getName().equals("miniGame")) return;

        event.setCancelled(true);

    }
}

package org.se0k.learnminigame;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.se0k.learnminigame.json.PlayerData;

import static org.se0k.learnminigame.json.JsonUtil.serialize;

public class PlayerEvent implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        PlayerData playerData = new PlayerData(player.getName(), player.getUniqueId(), 0);
        serialize(playerData);

    }

    @EventHandler
    public void hunger(FoodLevelChangeEvent event) {
        Player player = (Player) event.getEntity();

        World world = player.getWorld();

        if (!world.getName().equals("miniGame")) return;

        event.setCancelled(true);

    }

    @EventHandler
    public void playerDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();

        World world = player.getWorld();

        if (!world.getName().equals("miniGame")) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        World world = player.getWorld();

        if (!world.getName().equals("miniGame")) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void blockSet(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        World world = player.getWorld();

        if (!world.getName().equals("miniGame")) return;

        event.setCancelled(true);
    }


}

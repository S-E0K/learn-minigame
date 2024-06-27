package org.se0k.learnminigame;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.se0k.learnminigame.game.Game;
import org.se0k.learnminigame.game.SetGame;

import static org.se0k.learnminigame.CommandEvent.playerStage;
import static org.se0k.learnminigame.StatusEnum.gameCheck;

public class PlayerEvent implements Listener {

    @EventHandler
    public void playerDeathEnd(PlayerDeathEvent event) {
        Player player = event.getPlayer();

        gameCheck = StatusEnum.GameCheck.GAME_END;
        SetGame setGame = new Game();
        setGame.gameEnd(player);
        player.sendMessage(playerStage.get(player.getUniqueId()) - 1 + "스테이지 클리어");
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

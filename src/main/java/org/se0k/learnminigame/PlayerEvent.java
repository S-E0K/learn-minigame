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
import org.se0k.learnminigame.monster.MonsterDifficulty;
import org.se0k.learnminigame.monster.MonsterSpawn;

import static org.se0k.learnminigame.CommandEvent.playerStage;
import static org.se0k.learnminigame.StatusEnum.gameCheck;
import static org.se0k.learnminigame.game.Game.stage;

public class PlayerEvent implements Listener {

    @EventHandler
    public void playerDeathEnd(PlayerDeathEvent event) {
        if (gameCheck != StatusEnum.GameCheck.GAME_START) return;
        Player player = event.getPlayer();

        gameCheck = StatusEnum.GameCheck.GAME_END;
        SetGame setGame = new Game();
        setGame.gameEnd(player);
        player.sendMessage("현재 스테이지: " + stage + " 스테이지에서 죽으셨습니다");
        player.sendMessage("최고 클리어 스테이지: " + (playerStage.get(player.getUniqueId()) - 1) + "스테이지");

        MonsterDifficulty difficulty = new MonsterSpawn();
        difficulty.clear();

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

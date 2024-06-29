package org.se0k.learnminigame;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.se0k.learnminigame.game.Game;
import org.se0k.learnminigame.game.SetGame;
import org.se0k.learnminigame.monster.MonsterDifficulty;
import org.se0k.learnminigame.monster.MonsterSpawn;
import org.se0k.learnminigame.playerData.JsonUtil;

import static org.se0k.learnminigame.CommandEvent.*;
import static org.se0k.learnminigame.StatusEnum.gameCheck;
import static org.se0k.learnminigame.StatusEnum.stageLoc;
import static org.se0k.learnminigame.game.Game.stage;

public class PlayerEvent implements Listener {

    JsonUtil jsonUtil = new JsonUtil();

    @EventHandler
    public void playerDeathEnd(PlayerDeathEvent event) {
        if (gameCheck != StatusEnum.GameCheck.GAME_START) return;
        stageLoc = StatusEnum.StageLoc.STAGE_OUT;
        Player player = event.getPlayer();
        event.setKeepInventory(true);
        player.getInventory().clear();

        gameCheck = StatusEnum.GameCheck.GAME_END;
        SetGame setGame = new Game();
        setGame.gameEnd(player);
        player.sendMessage(difficulty + "난이도의 " + stage + " 스테이지에서 죽으셨습니다");
        if (!playerStageNormal.isEmpty()) player.sendMessage("노말 난이도 최고 클리어 스테이지: " + playerStageNormal.get(player.getName()));
        if (!playerStageHard.isEmpty()) player.sendMessage("하드 난이도 최고 클리어 스테이지: " + playerStageHard.get(player.getName()));
        jsonUtil.dataSave(player);

        Location location = new Location(world, 0.5, world.getHighestBlockYAt(0, 0) + 2, 7.5);
        player.teleport(location);

        MonsterDifficulty spawn = new MonsterSpawn();
        spawn.clear();

    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        jsonUtil.dataLoadNormal(player);
        jsonUtil.dataLoadHard(player);
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

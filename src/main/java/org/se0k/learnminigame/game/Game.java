package org.se0k.learnminigame.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.se0k.learnminigame.Learn_miniGame;

import java.util.HashMap;
import java.util.UUID;

public class Game implements SetGame{

    HashMap<UUID, Integer> stageData = new HashMap<>();
    static int stage = 0;
    int gameCheck = 0;

    @Override
    public void gameStart(Player player) {
        UUID playerUUID = player.getUniqueId();
        gameCheck = 1;
        if (!stageData.containsKey(playerUUID)) stageData.put(playerUUID, 0);
        gameStage(player);
    }

    @Override
    public void gameEnd(Player player) {
        gameCheck = 0;
        UUID playerUUID = player.getUniqueId();
        stageData.replace(playerUUID, stage);
    }

    @Override
    public void gameStage(Player player) {
        while(gameCheck == 1) {
            Bukkit.getScheduler().scheduleSyncRepeatingTask(Learn_miniGame.instance, () -> {
                player.sendMessage("스테이지 증가");
                stage += 1;
            }, 0, 20);
        }

    }
}

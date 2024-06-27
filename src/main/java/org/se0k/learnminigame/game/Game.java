package org.se0k.learnminigame.game;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.se0k.learnminigame.StatusEnum;
import org.se0k.learnminigame.monster.MonsterDifficulty;
import org.se0k.learnminigame.monster.MonsterSpawn;

import java.util.UUID;


import static org.se0k.learnminigame.CommandEvent.playerStage;
import static org.se0k.learnminigame.Learn_miniGame.plugin;
import static org.se0k.learnminigame.StatusEnum.*;

public class Game implements SetGame {

    public static int stage = 1;
//    public StatusEnum.GameCheck gameCheck;
    public static int countDown = 0;

    @Override
    public void gameStart(Player player) {
        if (gameCheck == StatusEnum.GameCheck.GAME_END) return;
//        if (gameCheck != 1) return;
        UUID playerUUID = player.getUniqueId();
        if (!playerStage.containsKey(playerUUID)) playerStage.put(playerUUID, 0);

        new BukkitRunnable() {
            @Override
            public void run() {

                if (countDown != 0) {
                    gameCheck = GameCheck.GAME_END;
                    player.sendMessage("딜레이중");
                    countDown -= 1;
                }

                if (countDown == 0) {
                    gameCheck = GameCheck.GAME_START;
                    player.sendMessage("몬스터 소환");

                    MonsterDifficulty monsterDifficulty = new MonsterSpawn();
                    monsterDifficulty.spawn(player);
                    this.cancel();
                    return;
                }

            }
        }.runTaskTimer(plugin, 0, 20L);

    }

    @Override
    public void gameEnd(Player player) {
        gameCheck = GameCheck.GAME_END;
        UUID playerUUID = player.getUniqueId();

        if (stage > playerStage.get(playerUUID)) playerStage.replace(playerUUID, stage);
    }

    @Override
    public void gameStage(Player player) {
        player.sendMessage(String.valueOf(playerStage.get(player.getUniqueId())));
    }


}

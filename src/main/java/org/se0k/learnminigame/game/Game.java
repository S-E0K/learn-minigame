package org.se0k.learnminigame.game;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.se0k.learnminigame.StatusEnum;
import org.se0k.learnminigame.monster.MonsterDifficulty;
import org.se0k.learnminigame.monster.MonsterSpawn;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.se0k.learnminigame.CommandEvent.playerStage;
import static org.se0k.learnminigame.Learn_miniGame.plugin;
import static org.se0k.learnminigame.StatusEnum.*;

public class Game implements SetGame {

    public static Map<String, Integer> stageJson = new HashMap<>();

    public static int stage = 1;
    public static int countDown = 0;

    @Override
    public void gameStart(Player player) {
        if (gameCheck == StatusEnum.GameCheck.GAME_END) return;
        String name = player.getName();
        if (!playerStage.containsKey(name)) playerStage.put(name, 0);

        new BukkitRunnable() {
            @Override
            public void run() {

                if (countDown != 0) {
                    if (gameCheck != GameCheck.GAME_BREAK) gameCheck = GameCheck.GAME_END;
                    player.sendMessage("딜레이중");
                    countDown -= 1;
                }

                if (countDown == 0) {
                    if (gameCheck == GameCheck.GAME_BREAK) {
                        this.cancel();
                        return;
                    }

                    gameCheck = GameCheck.GAME_START;
                    player.sendMessage("몬스터 소환");
                    MonsterDifficulty monsterDifficulty = new MonsterSpawn();
                    monsterDifficulty.spawn(player);
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 20L);

    }

    @Override
    public void gameEnd(Player player) {
        if (gameCheck != GameCheck.GAME_BREAK) gameCheck = GameCheck.GAME_END;
        String name = player.getName();

        if (stage - 1 > playerStage.get(name)) playerStage.replace(name, stage - 1);
    }

    @Override
    public void gameStage(Player player) {
        player.sendMessage(playerStage.get(player.getName()) + "스테이지 까지 클리어 하셨습니다");
    }

}

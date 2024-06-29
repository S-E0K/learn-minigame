package org.se0k.learnminigame.game;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.se0k.learnminigame.StatusEnum;
import org.se0k.learnminigame.monster.MonsterDifficulty;
import org.se0k.learnminigame.monster.MonsterSpawn;

import java.util.HashMap;
import java.util.Map;

import static org.se0k.learnminigame.CommandEvent.*;
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

        switch (difficulty) {
            case "normal" -> {
                if (!playerStageNormal.containsKey(name)) playerStageNormal.put(name, 0);
            }
            case "hard" -> {
                if (!playerStageHard.containsKey(name)) playerStageHard.put(name, 0);
            }
        }

        new BukkitRunnable() {
            @Override
            public void run() {

                if (countDown != 0) {
                    if (gameCheck != GameCheck.GAME_BREAK) gameCheck = GameCheck.GAME_END;
                    countDown -= 1;
                }

                if (countDown == 0) {
                    if (gameCheck == GameCheck.GAME_BREAK) {
                        this.cancel();
                        return;
                    }

                    gameCheck = GameCheck.GAME_START;
                    player.sendMessage("몬스터 소환");
                    spawnStats = SpawnStats.NOT_END;
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

        switch (difficulty) {
            case "normal" -> {
                if (stage - 1 > playerStageNormal.get(name)) playerStageNormal.replace(name, stage - 1);
            }
            case "hard" -> {
                if (stage - 1 > playerStageHard.get(name)) playerStageHard.replace(name, stage - 1);
            }
        }

    }

    @Override
    public void gameStage(Player player) {
        if (!playerStageNormal.isEmpty()) player.sendMessage("노말 난이도: " + playerStageNormal.get(player.getName()));
        else player.sendMessage("노말 난이도 클리어 기록이 없습니다");
        if (!playerStageHard.isEmpty()) player.sendMessage("하드 난이도: " + playerStageHard.get(player.getName()));
        else player.sendMessage("하드 난이도 클리어 기록이 없습니다");
    }

}

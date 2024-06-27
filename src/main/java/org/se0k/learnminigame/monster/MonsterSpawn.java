package org.se0k.learnminigame.monster;

import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.se0k.learnminigame.game.Game;
import org.se0k.learnminigame.game.SetGame;

import static org.se0k.learnminigame.CommandEvent.difficulty;
import static org.se0k.learnminigame.Learn_miniGame.plugin;
import static org.se0k.learnminigame.game.Game.*;

import static org.se0k.learnminigame.StatusEnum.*;

public class MonsterSpawn implements MonsterDifficulty{

    int spawnCount = 0;

    public void spawn(Player player) {

        if (gameCheck == GameCheck.GAME_END) return;

        SetGame setGame = new Game();


        switch (difficulty) {
            case "normal" -> {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (gameCheck == GameCheck.GAME_END) {
                            this.cancel();
                            return;
                        }
                        if (spawnCount == monsterCount()) {
                            spawnCount = 0;
                            this.cancel();
                            return;
                        }
                        normal(player);
                        spawnCount += 1;
                    }
                }.runTaskTimer(plugin, 0L, 60L);
            }
            case "hard" -> {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (gameCheck == GameCheck.GAME_END) {
                            this.cancel();
                            return;
                        }
                        if (spawnCount == 3) {
                            spawnCount = 0;
                            this.cancel();
                            return;
                        }
                        hard(player);
                        spawnCount += 1;
                    }
                }.runTaskTimer(plugin, 0L, 40L);
            }
        }
    }

    @Override
    public void normal(Player player) {
        SetGame setGame = new Game();
        MythicMob mob = MythicBukkit.inst().getMobManager().getMythicMob(stageCheck()).orElse(null);
        Location spawnLocation = player.getLocation();
        spawnLocation.set(1, -59, 30);
        if (mob != null) {
            ActiveMob heart = mob.spawn(BukkitAdapter.adapt(spawnLocation), 1);
            Entity entity = heart.getEntity().getBukkitEntity();
        }
    }

    @Override
    public void hard(Player player) {
        MythicMob mob = MythicBukkit.inst().getMobManager().getMythicMob("SpadeSoldier").orElse(null);
        Location spawnLocation = player.getLocation();
        spawnLocation.set(1, -59, 20);
        if (mob != null) {
            ActiveMob spade = mob.spawn(BukkitAdapter.adapt(spawnLocation), 1);
            Entity entity = spade.getEntity().getBukkitEntity();
        }
    }

    public String stageCheck() {
        switch (stage) {
            case 1, 2, 3, 10 -> {
                return "HeartSolider";
            }
            case 4, 5, 6 -> {
                return "HeartSolider2";
            }
            case 7, 8, 9 -> {
                return "HeartSolider3";
            }
        }
        return "HeartSolider";
    }

    public static int monsterCount() {
        switch (stage) {
            case 1, 2, 3, 10 -> {
                return 2;
            }
            case 4, 5, 6 -> {
                return 3;
            }
            case 7, 8, 9 -> {
                return 4;
            }
        }
        return 2;
    }

}
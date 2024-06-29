package org.se0k.learnminigame.monster;

import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.se0k.learnminigame.CommandEvent.difficulty;
import static org.se0k.learnminigame.Learn_miniGame.plugin;
import static org.se0k.learnminigame.game.Game.*;

import static org.se0k.learnminigame.StatusEnum.*;

public class MonsterSpawn implements MonsterDifficulty{

    public static Map<UUID, Entity> spawnMonster = new HashMap<>();


    public void spawn(Player player) {

        if (gameCheck == GameCheck.GAME_END) return;

        switch (difficulty) {
            case "normal" -> {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (gameCheck == GameCheck.GAME_END) {
                            player.sendMessage("여긴가");
                            this.cancel();
                            return;
                        }

                        if (spawnStats == SpawnStats.END) {
                            player.sendMessage("아니면 여긴가");
                            this.cancel();
                            return;
                        }
                        normal(player);
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
                        if (spawnStats == SpawnStats.END) {
                            this.cancel();
                            return;
                        }
                        hard(player);
                    }
                }.runTaskTimer(plugin, 0L, 40L);
            }
        }
    }

    @Override
    public void normal(Player player) {
        MythicMob mob = MythicBukkit.inst().getMobManager().getMythicMob(normalStageMonster()).orElse(null);
        Location spawnLocation = player.getLocation();
        spawnLocation.set(1, -59, 30);
        if (mob != null) {
            ActiveMob heart = mob.spawn(BukkitAdapter.adapt(spawnLocation), 1);
            Entity entity = heart.getEntity().getBukkitEntity();
            spawnMonster.put(heart.getUniqueId(), entity);
            if (spawnMonster.size() == monsterCount()) {
                spawnStats = SpawnStats.END;
            }
        }
    }

    @Override
    public void hard(Player player) {
        MythicMob mob = MythicBukkit.inst().getMobManager().getMythicMob(hardStageMonster()).orElse(null);
        Location spawnLocation = player.getLocation();
        spawnLocation.set(1, -59, 20);
        if (mob != null) {
            ActiveMob spade = mob.spawn(BukkitAdapter.adapt(spawnLocation), 1);
            Entity entity = spade.getEntity().getBukkitEntity();
            spawnMonster.put(spade.getUniqueId(), entity);
            if (spawnMonster.size() == monsterCount() + 1) {
                spawnStats = SpawnStats.END;
            }
        }
    }

    @Override
    public void clear() {
        spawnMonster.values().forEach(Entity::remove);
        spawnMonster.clear();
    }

    public String normalStageMonster() {
        switch (stage) {
            case 1, 2, 3 -> {
                return "HeartSolider";
            }
            case 4, 5, 6 -> {
                return "HeartSolider2";
            }
            case 7, 8, 9 -> {
                return "HeartSolider3";
            }
        }
        return "HeartSolider3";
    }

    public String hardStageMonster() {
        switch (stage) {
            case 1, 2, 3 -> {
                return "SpadeSoldier";
            }
            case 4, 5, 6 -> {
                return "SpadeSoldier2";
            }
            case 7, 8, 9 -> {
                return "SpadeSoldier3";
            }
        }
        return "SpadeSoldier3";
    }

    public static int monsterCount() {
        switch (stage) {
            case 1, 2, 3 -> {
                return 2;
            }
            case 4, 5, 6 -> {
                return 3;
            }
            case 7, 8, 9 -> {
                return 4;
            }
        }
        return 5;
    }

}
package org.se0k.learnminigame.monster;

import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static org.se0k.learnminigame.CommandEvent.difficulty;
import static org.se0k.learnminigame.Learn_miniGame.plugin;
import static org.se0k.learnminigame.game.Game.*;

public class MonsterSpawn implements MonsterDifficulty{

    int spawnCount = 0;

    public void spawn(Player player) {

        if (gameCheck != 1) return;

        switch (difficulty) {
            case "normal" -> {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (gameCheck != 1) {
                            this.cancel();
                            return;
                        }
                        if (spawnCount == 2) {
                            countDown = 5;
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
                        if (gameCheck != 1) {
                            this.cancel();
                            return;
                        }
                        if (spawnCount == 3) {
                            countDown = 5;
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
        MythicMob mob = MythicBukkit.inst().getMobManager().getMythicMob("HeartSolider").orElse(null);
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
}
package org.se0k.learnminigame.monster;

import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.se0k.learnminigame.game.Game;
import org.se0k.learnminigame.game.SetGame;
import org.se0k.learnminigame.potion.GivePotion;
import org.se0k.learnminigame.potion.SetPotion;

import static org.se0k.learnminigame.CommandEvent.difficulty;
import static org.se0k.learnminigame.game.Game.*;
import static org.se0k.learnminigame.StatusEnum.*;
import static org.se0k.learnminigame.monster.MonsterSpawn.*;

public class MonsterCheck implements Listener {

    int deathCount = 0;
    MonsterSpawn monsterSpawn = new MonsterSpawn();

    @EventHandler
    public void monsterCheck(MythicMobDeathEvent event) {

        if (gameCheck == GameCheck.GAME_END) return;

        ActiveMob mob = event.getMob();

        String monsterName = mob.getDisplayName();
        Player player = (Player) event.getKiller();
        SetPotion setPotion = new GivePotion();
        SetGame setGame = new Game();

        switch (difficulty) {
            case "normal" -> {
                if (monsterName.equals("1레벨 하트병사") || monsterName.equals("2레벨 하트병사") || monsterName.equals("3레벨 하트병사")) {
                    deathCount += 1;

                    player.sendMessage(monsterName + " 처치");
                }
                if (spawnStats == SpawnStats.END && monsterCount() == deathCount) {

                    monsterSpawn.clear();
                    if (stage == 3) {
                        setPotion.healItem(player);
                    }
                    if (stage == 6) {
                        setPotion.buffItem(player);
                    }
                    deathCount = 0;
                    countDown = 10;
                    if (gameCheck == GameCheck.GAME_BREAK) return;
                    player.sendMessage("노말 " + stage + "스테이지 클리어");
                    stage += 1;
                    spawnStats = SpawnStats.NOT_END;
                    setGame.gameStart(player);
                }
            }
            case "hard" -> {
                if (monsterName.equals("1레벨 스페이드병사") || monsterName.equals("2레벨 스페이드병사") || monsterName.equals("3레벨 스페이드병사")) {
                    deathCount += 1;

                    player.sendMessage(monsterName + " 처치");
                }

                if (spawnStats == SpawnStats.END && monsterCount() + 1 == deathCount) {

                    monsterSpawn.clear();
                    if (stage == 3) {
                        setPotion.healItem(player);
                    }
                    if (stage == 6) {
                        setPotion.buffItem(player);
                    }
                    deathCount = 0;
                    countDown = 10;
                    if (gameCheck == GameCheck.GAME_BREAK) return;
                    player.sendMessage("하드 " + stage + "스테이지");
                    stage += 1;
                    spawnStats = SpawnStats.NOT_END;
                    setGame.gameStart(player);
                }
            }
        }
    }

}

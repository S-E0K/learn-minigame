package org.se0k.learnminigame.monster;

import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.se0k.learnminigame.game.Game;
import org.se0k.learnminigame.game.SetGame;
import org.se0k.learnminigame.potion.GivePotion;
import org.se0k.learnminigame.potion.SetPotion;

import java.util.UUID;

import static org.se0k.learnminigame.CommandEvent.difficulty;
import static org.se0k.learnminigame.game.Game.*;
import static org.se0k.learnminigame.StatusEnum.*;
import static org.se0k.learnminigame.monster.MonsterSpawn.monsterCount;

public class MonsterCheck implements Listener {

    int killCheck = 0;

    @EventHandler
    public void monsterCheck(MythicMobDeathEvent event) {

        if (gameCheck == GameCheck.GAME_END) return;

        Player player = (Player) event.getKiller();
        UUID uuid = player.getUniqueId();
        SetPotion setPotion = new GivePotion();
        SetGame setGame = new Game();

        switch (difficulty) {
            case "normal" -> {
                if (event.getMob().getDisplayName().equals("1레벨 하트병사") || event.getMob().getDisplayName().equals("2레벨 하트병사") || event.getMob().getDisplayName().equals("3레벨 하트병사")) {
                    killCheck += 1;
                    player.sendMessage(event.getMob().getDisplayName() + " 처치");
                }

                if (killCheck == monsterCount()) {

                    if (stage == 3) {
                        setPotion.healItem(player);
                    }
                    if (stage == 6) {
                        setPotion.buffItem(player);
                    }

                    killCheck = 0;
                    countDown = 5;
                    setGame.gameStart(player);
                    player.sendMessage(stage + "스테이지 클리어");
                    stage += 1;
                }
            }
            case "hard" -> {
                if (event.getMob().getDisplayName().equals("스페이드병사")) {
                    killCheck += 1;
                    player.sendMessage("스페이드병사 처치");
                }

                if (killCheck == 3) {

                    if (stage == 3) {
                        setPotion.healItem(player);
                    }
                    if (stage == 6) {
                        setPotion.buffItem(player);
                    }

                    killCheck = 0;
                    countDown = 5;
                    setGame.gameStart(player);
                    player.sendMessage(stage + "스테이지");
                    stage += 1;
                }
            }
        }
    }

}
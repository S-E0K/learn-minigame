package org.se0k.learnminigame.game;

import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

import static org.bukkit.plugin.java.JavaPlugin.getProvidingPlugin;
import static org.se0k.learnminigame.CommandEvent.difficulty;
import static org.se0k.learnminigame.Learn_miniGame.plugin;
import static org.se0k.learnminigame.game.Game.*;

public class MonsterCheck implements Listener {

    int killCheck = 0;

    @EventHandler
    public void monsterCheck(MythicMobDeathEvent event) {

        if (gameCheck != 1) return;

        Player player = (Player) event.getKiller();
        UUID uuid = player.getUniqueId();

        switch (difficulty) {
            case "normal" -> {
                if (event.getMob().getDisplayName().equals("하트병사")) {
                    killCheck += 1;
                    player.sendMessage("하트병사 처치");
                }

                if (killCheck == 2) {
                    player.sendMessage("다음 스테이지");
                    stage += 1;

                    killCheck = 0;
                    countDown = 5;
                    SetGame setGame = new Game();
                    setGame.gameStart(player);
                    player.sendMessage(stage + "스테이지");
                }
            }
            case "hard" -> {
                if (event.getMob().getDisplayName().equals("스페이드병사")) {
                    killCheck += 1;
                    player.sendMessage("스페이드병사 처치");
                }

                if (killCheck == 3) {
                    player.sendMessage("다음 스테이지");
                    stage += 1;

                    killCheck = 0;
                    countDown = 5;
                    SetGame setGame = new Game();
                    setGame.gameStart(player);
                    player.sendMessage(stage + "스테이지");
                }
            }
        }


//        if (event.getMob().getDisplayName().equals("하트병사")) {
//            killCheck += 1;
//            player.sendMessage("하트병사 처치");
//        }
//
//        if (killCheck == 2) {
//            player.sendMessage("다음 스테이지");
//            stage += 1;
//
//            killCheck = 0;
//            countDown = 5;
//            SetGame setGame = new Game();
//            setGame.gameStart(player);
//            player.sendMessage(stage + "스테이지");
//        }

    }

}

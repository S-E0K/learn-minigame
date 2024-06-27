package org.se0k.learnminigame.game;

import io.r2dbc.spi.Parameter;
import org.bukkit.entity.Player;

public interface SetGame {
    void gameStart(Player player);
    void gameEnd(Player player);
    void gameStage(Player player);
}

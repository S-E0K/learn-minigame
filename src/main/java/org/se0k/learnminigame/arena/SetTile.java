package org.se0k.learnminigame.arena;

import org.bukkit.entity.Player;

public interface SetTile {
    void setTile(String difficulty, Player player);
    int checkDifficulty(String difficulty);
}

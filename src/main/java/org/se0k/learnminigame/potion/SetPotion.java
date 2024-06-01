package org.se0k.learnminigame.potion;

import org.bukkit.entity.Player;

public interface SetPotion {
    void healItem(Player player, String difficulty);
    void buffItem(Player player, String difficulty);
}

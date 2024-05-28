package org.se0k.learnminigame.potion;

import org.bukkit.entity.Player;

public interface SetPotion {
    void heal(Player player, String difficulty);
    void buff(Player player, String difficulty);
}

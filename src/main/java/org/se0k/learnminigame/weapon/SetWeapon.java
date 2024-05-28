package org.se0k.learnminigame.weapon;

import org.bukkit.entity.Player;

public interface SetWeapon {
    void giveSword(Player player, String difficulty);

    void giveBow(Player player, String difficulty);

    void giveShield(Player player, String difficulty);
}

package org.se0k.learnminigame.monster;

import org.bukkit.entity.Player;

public interface MonsterDifficulty {
    void spawn(Player player);
    void normal(Player player);
    void hard(Player player);

}

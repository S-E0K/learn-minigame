package org.se0k.learnminigame.arena;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public interface SetTile {
    void setTile(Player player);
    int checkDifficulty();
    void deleteTile(Location location);
}

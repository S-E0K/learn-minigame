package org.se0k.learnminigame.arena;

import net.kyori.adventure.text.ComponentLike;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class ArenaSetTile implements SetTile {
//    int check;
    @Override
    public void setTile(String difficulty, Player player) {
        player.setBodyYaw(0);
        int tile = checkDifficulty(difficulty);
        int x = tile / 2;

        World world = player.getWorld();

        Location loc = new Location(world, 0.5, world.getHighestBlockYAt(0, 0) + 1, 0.5);

        player.teleport(loc);


        Location startLoc = loc.clone().add(- x, 0, 5);
//        if (check == 1) {
//            player.sendMessage("지움");
//            Location deleteLoc = loc.clone().add(- 20, 0, 5);
//            player.sendMessage((ComponentLike) deleteLoc);
//            for(int i = 0; i < 40; i++) {
//                for(int j = 0; j < 40; j++) {
//                    deleteLoc.getBlock().setType(Material.AIR);
//                    deleteLoc.add(1, 0, 0);
//                }
//                deleteLoc.setX(- 20);
//                deleteLoc.add(0, 0, 1);
//            }
//        }

        Location saveLoc = loc.clone().add(0, 1, 7);

        for(int i = 0; i < tile; i++) {
            for(int j = 0; j < tile; j++) {
                startLoc.getBlock().setType(Material.IRON_BLOCK);
                startLoc.add(1, 0, 0);
            }
            startLoc.setX(-x);
            startLoc.add(0, 0, 1);
        }
//        check = 1;
        player.teleport(saveLoc);
    }

    @Override
    public int checkDifficulty(String difficulty) {

        switch (difficulty) {
            case "easy" -> {
                return 40;
            }
            case "normal" -> {
                return 30;
            }
            case "hard" -> {
                return 20;
            }
        }
        return 30;
    }
}

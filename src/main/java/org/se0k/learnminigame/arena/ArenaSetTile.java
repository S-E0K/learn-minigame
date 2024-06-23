package org.se0k.learnminigame.arena;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.se0k.learnminigame.potion.GivePotion;
import org.se0k.learnminigame.potion.SetPotion;
import org.se0k.learnminigame.weapon.GiveWeapon;
import org.se0k.learnminigame.weapon.SetWeapon;

import static org.se0k.learnminigame.CommandEvent.difficulty;

public class ArenaSetTile implements SetTile {

    @Override
    public void setTile(Player player) {

        player.setBodyYaw(0);
        int tile = checkDifficulty();
        int x = tile / 2;

        World world = player.getWorld();
        Location loc = new Location(world, 0.5, world.getHighestBlockYAt(0, 0) + 1, 0.5);

        player.teleport(loc);

        Location startLoc = loc.clone().add(-x, 0, 5);

        deleteTile(loc);

        Location playerStartLoc = loc.clone().add(0, 1, 7);

        for (int i = 0; i < tile; i++) {
            for (int j = 0; j < tile; j++) {
                startLoc.getBlock().setType(Material.IRON_BLOCK);
                startLoc.add(1, 0, 0);
            }
            startLoc.setX(startLoc.getX() - tile);
            startLoc.add(0, 0, 1);
        }
        player.teleport(playerStartLoc);

        WorldBorder worldBorder = world.getWorldBorder();

        Location center = playerStartLoc.clone();

        center.setZ(playerStartLoc.getZ() + x - 2);

        worldBorder.setCenter(center);
        worldBorder.setSize(tile);

        player.getInventory().clear();

        SetWeapon setWeapon = new GiveWeapon();
        setWeapon.giveSword(player);
        setWeapon.giveShield(player);
        setWeapon.giveBow(player);

        SetPotion setPotion = new GivePotion();
        setPotion.healItem(player);
        setPotion.buffItem(player);
    }

    @Override
    public int checkDifficulty() {
        switch (difficulty) {
            case "hard" -> {
                return 21;
            }
            default -> {
                return 31;
            }
        }
    }

    @Override
    public void deleteTile(Location location) {

        Location deleteLoc = location.clone().add(-15, 0, 5);

        for (int i = 0; i < 31; i++) {
            for (int j = 0; j < 31; j++) {
                deleteLoc.getBlock().setType(Material.AIR);
                deleteLoc.add(1, 0, 0);
            }
            deleteLoc.setX(deleteLoc.getX() - 31);
            deleteLoc.add(0, 0, 1);
        }
    }
}

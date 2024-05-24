package org.se0k.learnminigame;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.se0k.learnminigame.arena.ArenaSetTile;
import org.se0k.learnminigame.arena.SetTile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class CommandEvent extends Command {
    public CommandEvent(String str) {
        super(str);
    }
    final HashMap<UUID, Location> playerLoc = new HashMap<>();

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabels, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return false;

        UUID playerUUID = player.getUniqueId();

        SetTile setTile = new ArenaSetTile();

        if (args.length == 0) return false;

        switch (args[0]) {
            case "in" -> {
                World world = miniGameWorld("miniGame");
                Location location = new Location(world, 0.5, world.getHighestBlockYAt(0, 0) + 1, 0.5);
                if (playerLoc.get(playerUUID) == null) playerLoc.put(playerUUID, player.getLocation());
                player.teleport(location);
            }

            case "out" -> {
                if (playerLoc.get(playerUUID) != null) player.teleport(playerLoc.get(playerUUID));
                playerLoc.remove(playerUUID);
            }

            case "set" -> {
                if (args.length == 1) return false;
                switch (args[1]) {
                    case "easy" -> {
                        setTile.setTile("easy", player);
                        player.sendMessage("easy 모드");
                    }
                    case "normal" -> {
                        setTile.setTile("normal", player);
                        player.sendMessage("normal 모드");
                    }
                    case "hard" -> {
                        setTile.setTile("hard", player);
                        player.sendMessage("hard 모드");
                    }
                }
            }
//            case "start" -> {
//
//            }
//            case "score" -> {
//
//            }

        }



        return false;
    }


    public World miniGameWorld(String name) {
        WorldCreator worldCreator = new WorldCreator(name);
        worldCreator.type(WorldType.FLAT);
        worldCreator.generateStructures(false);
        return worldCreator.createWorld();
    }


    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) return Arrays.asList("in", "out" ,"set", "start", "score");

        if (args[0].equals("set") && args.length == 2) return Arrays.asList("easy", "normal", "hard");

        return null;
    }
}

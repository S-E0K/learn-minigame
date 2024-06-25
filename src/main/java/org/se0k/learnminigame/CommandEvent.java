package org.se0k.learnminigame;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.se0k.learnminigame.arena.ArenaSetTile;
import org.se0k.learnminigame.arena.SetTile;
import org.se0k.learnminigame.game.Game;
import org.se0k.learnminigame.game.SetGame;

import java.util.*;

import static org.se0k.learnminigame.game.Game.gameCheck;


public class CommandEvent extends Command {
    public CommandEvent(String str) {
        super(str);
    }

    final HashMap<UUID, Location> playerLoc = new HashMap<>();
    public static String difficulty = "normal";

    public final static HashMap<UUID, Integer> playerStage = new HashMap<>();

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabels, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return false;


        UUID playerUUID = player.getUniqueId();

        SetTile setTile = new ArenaSetTile();

        World world = miniGameWorld("miniGame");
        world.setDifficulty(Difficulty.NORMAL);

        if (args.length == 0) return false;

        switch (args[0]) {
            case "in" -> {
                player.setSaturation(20);
                Location location = new Location(world, 0.5, world.getHighestBlockYAt(0, 0) + 1, 0.5);
                if (playerLoc.get(playerUUID) == null) playerLoc.put(playerUUID, player.getLocation());
                player.teleport(location);
            }

            case "out" -> {
                if (playerLoc.get(playerUUID) != null) player.teleport(playerLoc.get(playerUUID));
                playerLoc.remove(playerUUID);
            }

            case "start" -> {
                player.sendMessage("게임 시작");
                gameCheck = 1;
                SetGame setGame = new Game();
                setGame.gameStart(player);
            }

            case "end" -> {
                player.sendMessage("미니게임 수동 종료");
                gameCheck = 0;
                SetGame setGame = new Game();
                setGame.gameEnd(player);
            }

            case "stage" -> {
                SetGame setGame = new Game();
                setGame.gameStage(player);
            }

            case "set" -> {

                if (!player.getWorld().equals(world)) {
                    player.sendMessage("미니게임 월드가 아닙니다");
                    return false;
                }

                if (args.length == 1) return false;
                switch (args[1]) {
                    case "normal" -> {
                        difficulty = "normal";
                        setTile.setTile(player);
                        player.sendMessage("normal 모드");

                    }
                    case "hard" -> {
                        difficulty = "hard";
                        setTile.setTile(player);
                        player.sendMessage("hard 모드");

                    }
                }
            }
            default -> {
                player.sendMessage("잘못 입력함");
            }
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
        if (args.length == 1) return Arrays.asList("in", "out", "set", "start", "end", "stage", "start");

        if (args[0].equals("set") && args.length == 2) return Arrays.asList("normal", "hard");

        return null;
    }
}

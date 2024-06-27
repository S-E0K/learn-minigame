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
import org.se0k.learnminigame.weapon.GiveWeapon;
import org.se0k.learnminigame.weapon.SetWeapon;

import java.util.*;

import static org.se0k.learnminigame.StatusEnum.*;
//import static org.se0k.learnminigame.game.Game.gameCheck;


public class CommandEvent extends Command {
    public CommandEvent(String str) {
        super(str);
    }

    final HashMap<UUID, Location> playerLoc = new HashMap<>();
    public static String difficulty = "normal";

    public final static HashMap<UUID, Integer> playerStage = new HashMap<>();

    public static World world = miniGameWorld("miniGame");

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabels, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return false;


        UUID playerUUID = player.getUniqueId();

        SetTile setTile = new ArenaSetTile();

//        World world = miniGameWorld("miniGame");
        world.setDifficulty(Difficulty.NORMAL);

        if (args.length == 0) return false;

        switch (args[0]) {
            case "in" -> {
                if (stageLoc == StageLoc.STAGE_IN) return false;
                stageLoc = StageLoc.STAGE_IN;
                player.setSaturation(20);
                Location location = new Location(world, 0.5, world.getHighestBlockYAt(0, 0) + 2, 7.5);
                if (playerLoc.get(playerUUID) == null) playerLoc.put(playerUUID, player.getLocation());
                player.teleport(location);
            }

            case "out" -> {
                if (stageLoc == StageLoc.STAGE_OUT) return false;
                stageLoc = StageLoc.STAGE_OUT;
                if (playerLoc.get(playerUUID) != null) player.teleport(playerLoc.get(playerUUID));
                playerLoc.remove(playerUUID);
            }

            case "start" -> {
                SetWeapon setWeapon = new GiveWeapon();
                setWeapon.giveSword(player);
                setWeapon.giveShield(player);
                setWeapon.giveBow(player);

                player.sendMessage("게임 시작");
                gameCheck = GameCheck.GAME_START;
                SetGame setGame = new Game();
                setGame.gameStart(player);
            }

            case "end" -> {
                player.sendMessage("미니게임 수동 종료");
                gameCheck = GameCheck.GAME_END;
                SetGame setGame = new Game();
                setGame.gameEnd(player);
            }

            case "stage" -> {
                SetGame setGame = new Game();
                setGame.gameStage(player);
            }

            case "set" -> {

                if (stageLoc == StageLoc.STAGE_OUT) {
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


    public static World miniGameWorld(String name) {
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

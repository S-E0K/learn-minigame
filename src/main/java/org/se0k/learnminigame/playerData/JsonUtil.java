package org.se0k.learnminigame.playerData;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.bukkit.entity.Player;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.se0k.learnminigame.CommandEvent.playerStage;

public class JsonUtil {
    private final Gson gson = new Gson();

    public void dataSave(Player player) {
        Path path = Path.of("playerData/" + player.getName() + ".json");
        if (Files.notExists(path)) {
            try {
                Files.createDirectories(path.getParent());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path.toFile()))) {
            gson.toJson(playerStage, bufferedWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void dataLoad(Player player) {
        Path path = Path.of("playerData/" + player.getName() + ".json");
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            Type type = new TypeToken<Map<String, Integer>>() {}.getType();
            Map<String, Integer> data = gson.fromJson(reader, type);
            if (data != null) {
                playerStage.putAll(data);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
package org.se0k.learnminigame.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class JsonUtil {
    private static Gson gson = new GsonBuilder().create();



    public static void serialize(@NotNull PlayerData playerData) {
        String json = gson.toJson(playerData);
    }




}

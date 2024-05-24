package org.se0k.learnminigame;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class JsonUtil {
    private static Gson gson = new GsonBuilder().create();



    public static void serialize(@NotNull Player player) {
        String json = gson.toJson(player);
    }




}

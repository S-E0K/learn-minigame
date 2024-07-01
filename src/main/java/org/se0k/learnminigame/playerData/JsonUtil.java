package org.se0k.learnminigame.playerData;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.bukkit.entity.Player;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.se0k.learnminigame.CommandEvent.*;

public class JsonUtil {
    private final Gson gson = new Gson();

    Path path;

    public void dataSave(Player player) {
        switch (difficulty) {
            case "normal" -> {
                path = Path.of("playerData/" + player.getName() + "/normal.json"); // 파일 경로
                if (Files.notExists(path)) { // 파일이 없다면
                    try {
                        Files.createDirectories(path.getParent()); // 파일 생성
                    } catch (IOException e) { // 있다면
                        throw new RuntimeException(e); // 잡기
                    }
                }
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path.toFile()))) { // BufferWriter를 이용해 Json 쓰기
                    gson.toJson(playerStageNormal, bufferedWriter); // 쓰기
                } catch (IOException e) { // 못쓰면
                    throw new RuntimeException(e); // 잡기
                }
            }

            case "hard" -> {
                path = Path.of("playerData/" + player.getName() + "/hard.json"); // 파일 경로
                if (Files.notExists(path)) { // 파일이 없다면
                    try {
                        Files.createDirectories(path.getParent()); // 파일 생성
                    } catch (IOException e) { // 있다면
                        throw new RuntimeException(e); // 잡기
                    }
                }
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path.toFile()))) { // BufferWriter를 이용해 Json 쓰기
                    gson.toJson(playerStageHard, bufferedWriter); // 쓰기
                } catch (IOException e) { // 못쓰면
                    throw new RuntimeException(e); // 잡기
                }
            }
        }
//        Path path = Path.of("playerData/" + player.getName() + ".json"); // 파일 경로
//        if (Files.notExists(path)) { // 파일이 없다면
//            try {
//                Files.createDirectories(path.getParent()); // 파일 생성
//            } catch (IOException e) { // 있다면
//                throw new RuntimeException(e); // 잡기
//            }
//        }
//        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path.toFile()))) { // BufferWriter를 이용해 Json 쓰기
//            gson.toJson(playerStageNormal, bufferedWriter); // 쓰기
//        } catch (IOException e) { // 못쓰면
//            throw new RuntimeException(e); // 잡기
//        }
    }

    public void dataLoadNormal(Player player) {
        path = Path.of("playerData/" + player.getName() + "/normal.json");
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {  // 버퍼리더를 이용해 읽기
            Type type = new TypeToken<Map<String, Integer>>() {}.getType(); // 읽을 타입 변환
            Map<String, Integer> data = gson.fromJson(reader, type); // 읽어서 맵에 저장
            if (data != null) { // 읽었으면
                playerStageNormal.putAll(data); // 집어넣기
            }
        } catch (IOException e) { // 못읽으면
            player.sendMessage("노말 난이도의 기록이 없습니다");
            throw new RuntimeException(e); // 잡기
        }
    }
    public void dataLoadHard(Player player) {
        path = Path.of("playerData/" + player.getName() + "/hard.json");
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            Type type = new TypeToken<Map<String, Integer>>() {}.getType();
            Map<String, Integer> data = gson.fromJson(reader, type);
            if (data != null) {
                playerStageHard.putAll(data);
            }
        } catch (IOException e) {
            player.sendMessage("하드 난이도의 기록이 없습니다");
            throw new RuntimeException(e);
        }
    }
}
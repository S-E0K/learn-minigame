package org.se0k.learnminigame.json;

import java.util.UUID;

public class PlayerData {
    private String name;
    private UUID uuid;
    private int stage;

    public PlayerData(String name, UUID uuid, int stage) {
        this.name = name;
        this.uuid = uuid;
        this.stage = stage;
    }

    public String getName() {
        return name;
    }
    public UUID getUUID() {
        return uuid;
    }
    public int getStage() {
        return stage;
    }
}

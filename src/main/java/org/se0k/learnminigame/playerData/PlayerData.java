package org.se0k.learnminigame.playerData;

import java.util.UUID;

public class PlayerData {
    private final String name;
    private final UUID uuid;
    private final int stage;

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

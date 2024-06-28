package org.se0k.learnminigame;

public class StatusEnum {

    public static GameCheck gameCheck;

    public static StageLoc stageLoc;

    public static SpawnStats spawnStats;

    public enum GameCheck {
        GAME_START,
        GAME_END,
        GAME_BREAK;
    }

    public enum StageLoc {
        STAGE_IN,
        STAGE_OUT;
    }

    public enum SpawnStats {
        NOT_END,
        END;
    }

}

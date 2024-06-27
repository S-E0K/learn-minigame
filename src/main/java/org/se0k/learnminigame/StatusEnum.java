package org.se0k.learnminigame;

public class StatusEnum {

    public static GameCheck gameCheck;

    public static StageLoc stageLoc;

    public enum GameCheck {
        GAME_START,
        GAME_END,
        GAME_BREAK;
    }

    public enum StageLoc {
        STAGE_IN,
        STAGE_OUT;
    }

}

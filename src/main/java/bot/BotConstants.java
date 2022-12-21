package bot;

import model.Difficulty;

import java.util.HashMap;

public interface BotConstants {
    String USERNAME = "@transaction9918bot";
    String BOT_TOKEN = "5651368205:AAF292oK7yYHjuJfd3WHEdhwZ2FYe9bMTNA";

    String EASY = "easy";
    String MEDIUM = "medium";
    String HARD = "hard";
    String ALL = "all";


    String START = "/start";
    String TOPIC = "topic";
    String PROBLEM = "problem";
    String PREPARATION = "preparation";
    String SELECT_TOPIC = "SELECT_TOPIC";

    String SOLVED = " SOLVED";
    String NOT_RESOLVED = " NOT RESOLVED";

    int OBJECTID = 1;
    int DIFFICULTY = 2;
    String SEPARATOR = "/";
    int PAGE_NUMBER = 3;

    String NEXT = "⏭";
    String PREV = "⏮";

    HashMap<Long, String> USER_STATUS = new HashMap<>();


    default boolean isTopic(final String callBackData) {
        return callBackData.startsWith(TOPIC);
    }

    default boolean isProblem(final String callBackData) {
        return callBackData.startsWith(PROBLEM);
    }
    default boolean isPrevOrNext(final String callBackData) {
        return callBackData.startsWith(PREV) || callBackData.startsWith(NEXT);
    }
}

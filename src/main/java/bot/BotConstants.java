package bot;

import common.Pair;
import model.Difficulty;

import java.util.HashMap;

public interface BotConstants {
    String USERNAME = "@hackerrank_project_bot";
    String BOT_TOKEN = "5961284561:AAHYEz4bnzTV5K3gcWhcGYd6EVziwNZIFk4";

    String EASY = "easy";
    String MEDIUM = "medium";
    String HARD = "hard";
    String ALL = "all";


    String START = "/start";
    String TOPIC = "topic";
    String TOPIC_ID = "TopicId";
    String PROBLEM = "problem";
    String BACK = " back ";

    String PREPARATION = "preparation";
    String SELECT_TOPIC = "SELECT_TOPIC";

    String CONTINUE = "Continue preparation";

    int OBJECTID = 1;
    int DIFFICULTY = 2;
    String SEPARATOR = "/";
    int PAGE_NUMBER = 3;

    String NEXT = "⏭";
    String PREV = "⏮";
    String SOLVED = "Solved";
    String UNSOLVED = "Unsolved";

    String TEXT = "TEXT";
    String IMAGE = "IMAGE";

    HashMap<Long, String> USER_STATUS = new HashMap<>();
    HashMap<Long, String> ADMIN_SEND_QUESTION_CONTENT = new HashMap<>();
    String ADMIN_SEND_QUESTION = "ADMIN_SEND_QUESTION";

    default boolean isTopic(final String callBackData ) {
        return callBackData.startsWith(TOPIC);
    }

    default boolean isProblem(final String callBackData) {
        return callBackData.startsWith(PROBLEM);
    }
    default boolean isPrevOrNext(final String callBackData) {
        return callBackData.startsWith(PREV) || callBackData.startsWith(NEXT);
    }

    default boolean isTopicId(String callBackData) {
        return callBackData.startsWith(TOPIC_ID);
    }
}
